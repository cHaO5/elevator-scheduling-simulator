package domain;

import strategy.PriorityCalculationStrategy;
import util.Conf;
import domain.enumeration.Direction;
import domain.enumeration.ElevatorStatus;
import domain.enumeration.TaskStatus;
import util.Util;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public class Elevator implements Runnable {
    private int id;

    private Floor currFloor;

    //承载量
    //private int load;

    private ElevatorStatus status;

    private Task currTask;

    private Dispatcher dispatcher;

    private Set<Person> currLoad = new HashSet<>(Conf.MAX_LOAD);

    private BlockingQueue<Task> taskQueue = new PriorityBlockingQueue<>();
    /**
     * 任务优先级计算策略
     */
    private PriorityCalculationStrategy priorityCalculationStrategy;

    //================many lock to protect elevator status===================
    /**
     * 用于锁楼层（在读取楼层并做任务优先的决策时，不能改楼层数据）
     */
    private final ReadWriteLock currFloorLock = new ReentrantReadWriteLock();
    /**
     * dispatcher里的线程池要"连续读"currTask这个属性，而电梯线程可以"同时写"currTask这个属性，存在并发错误的可能，且不会引发任何异常，需要加锁
     */
    private final ReadWriteLock currTaskLock = new ReentrantReadWriteLock();
    /**
     * currLoad属性可能同时在电梯线程里写，在dispatchStrategy-select时读，所以要加锁保护
     */
    private final ReadWriteLock currLoadLock = new ReentrantReadWriteLock();
    /**
     * status属性可能同时在电梯线程里写，在dispatcher-tryReceive时读，所以要加锁保护
     */
    private final ReadWriteLock statusLock = new ReentrantReadWriteLock();

    public Elevator(int id, Floor initFloor) {
        this.id = id;
        this.currFloor = initFloor;
        setStatus(ElevatorStatus.STOP);
    }
    public int getId() {
        return id;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public ElevatorStatus getStatus() {
        return status;
    }

    private void setStatus(ElevatorStatus status) {
        getStatusLock().writeLock().lock();
        this.status = status;
        getStatusLock().writeLock().unlock();
    }

    public Floor getCurrFloor() {
        return currFloor;
    }

    private void setCurrFloor(Floor currFloor) {
        currFloorLock.writeLock().lock();
        this.currFloor = currFloor;
        currFloorLock.writeLock().unlock();
    }

    private void setCurrTask(Task currTask) {
        currTaskLock.writeLock().lock();
        this.currTask = currTask;
        currTaskLock.writeLock().unlock();
    }

    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public Set<Person> getCurrLoad() {
        return currLoad;
    }

    public ReadWriteLock getStatusLock() {
        return statusLock;
    }

    public ReadWriteLock getCurrLoadLock() {
        return currLoadLock;
    }

    public void receive(Task task) {
        currFloorLock.readLock().lock();
        if (task != null && !taskQueue.contains(task)) {
            doReceive(task);
            currTaskLock.readLock().lock();
            currTaskLock.readLock().unlock();
        }
        currFloorLock.readLock().unlock();
    }

    void doReceive(Task task) {
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int tryReceive(Task task) {
        return priorityCalculationStrategy.calcPriority(this, task);
    }


    @Override
    public void run() {
        while (true) {
            Task task = null;
            try {
                task = taskQueue.poll(10 * Conf.ELAPSED_TIME, Conf.TIME_UNIT);
                if (task == null) {
                    throw new InterruptedException();
                }
                execTask(task);
            } catch (InterruptedException e) {
                dispatcher.quit(this);
                break;
//            } catch (TaskCancelledException e) {
//
//            } catch (CannotExecTaskException | UserInFloorTaskGrabbedException e) {
//                dispatcher.redispatch(task);
//            } catch (UserInElevatorTaskGrabbedException e) {
//                receive(task);
//            } catch (Throwable e) {

            } finally {
                onStop();
            }
        }
    }

    private void onStop() {
        setCurrTask(null);
        setStatus(ElevatorStatus.STOP);
    }

    private void execTask(Task task)
//            throws TaskCancelledExcaption, CannotExecTaskExcaption, UerInElevatorTaskGrabbedException,
//            InterruptedException, UserInFloorTaskGrabbedException {
    {
        if (task == null) {
            return;
        }
        setCurrTask(task);
        if (task.getStatus().equals(TaskStatus.CANCELLED)) {
            //throw new TaskCancelledException();
        }
        move(task);
        setStatus(task.getDirection().equals(Direction.DOWN) ? ElevatorStatus.RUNNING_DOWN : ElevatorStatus.RUNNING_UP);
        unload();
        load(task.getDirection());
    }

    private boolean canReduceLoad(Floor floor) {
        return currLoad.stream().anyMatch(person -> person.getTargetFloor().equals(floor));
    }

    private void load(Direction direction) {
        Set<Person> reduceSet =  currFloor.reduce(direction, Conf.MAX_LOAD = currLoad.size());
        if (!reduceSet.isEmpty()) {
            getCurrLoadLock().writeLock().lock();
            currLoad.addAll(reduceSet);
            getCurrLoadLock().writeLock().unlock();

            reduceSet.forEach(person -> {
                person.enterElevator(this);
                person.selectElevator(person.getTargetFloor());
            });
            currFloor.done(direction);
        }
    }

    private void unload() {
        Set<Person> unloadSet = currLoad.stream()
                .filter(person -> person.getTargetFloor().equals(currFloor))
                .collect(Collectors.toSet());
        if (unloadSet.size() > 0) {
            getCurrLoadLock().writeLock().lock();
            currLoad.remove(unloadSet);
            getCurrLoadLock().writeLock().unlock();
        }
    }

    private void move(Task task)
//        throws TaskCancelledException, UserInElevatorTaskGrabbedException, InterruptedException,
//        UserInFloorTaskGrabbedException {
    {
        task.setStatus(TaskStatus.RUNNING);

        Direction relativeDirection = task.getSrcFloor().locate(currFloor);
        setStatus(Direction.UP.equals(relativeDirection) ? ElevatorStatus.RUNNING_UP : ElevatorStatus.RUNNING_DOWN);

        int distance = Math.abs(Util.calcDistance(currFloor, task.getSrcFloor()));
        for (int i = 0; i < distance; i++) {
            if (task.getStatus().equals(TaskStatus.CANCELLED)) {
                //throw new TaskCancelledException();
            }

            if (task.getStatus().equals(TaskStatus.RUNNABLE)) {
                if (task.getDirection().equals(Direction.NONE)) {
                    //throw new UserInElevatorTaskGrabbedException();
                } else {
                    //throw new UserInFloorTaskGrabbedException();
                }
            }
        }
        setCurrFloor(currFloor.next(relativeDirection));

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Elevator elevator = (Elevator) o;

        return id == elevator.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
