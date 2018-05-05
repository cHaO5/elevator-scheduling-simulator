package domain;

import domain.enumeration.Direction;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Floor {
    private int id;

    private Floor preFloor;

    private Floor nextFloor;

    /**
     * 当前楼层往上走的等候人群
     */
    private Set<Person> waitingUpUserSet = new HashSet<>(100);
    /**
     * 当前楼层往下走的等候人群
     */
    private Set<Person> waitingDownUserSet = new HashSet<>(100);
    /**
     * 向上等候人群读写锁，杜绝并发异常，因为waitingUpUserSet可能会同时在main线程里写，在电梯线程里读
     */
    private ReadWriteLock upUserSetLock = new ReentrantReadWriteLock();
    /**
     * 向下等候人群读写锁，杜绝并发异常
     */
    private ReadWriteLock downUserSetLock = new ReentrantReadWriteLock();
    /**
     * 已经有人表达说要去的方向集合
     */
    private Map<Direction, Task> waitingDirectionMap = new HashMap<>(Direction.values().length);
    /**
     * waitingDirectionMap可能会同时在main线程里读，在电梯线程里写
     */
    private ReadWriteLock waitingDirectionMapLock = new ReentrantReadWriteLock();
    /**
     * 整个电梯的调度
     */
    private Dispatcher dispatcher;

    public Floor(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public Floor next(Direction direction) {
        return Direction.UP.equals(direction) ? nextFloor : preFloor;
    }

    public void next(Floor nextFloor) {
        this.nextFloor = nextFloor;
        nextFloor.previous(this);
    }

    private void previous(Floor preFloor) {
        this.preFloor = preFloor;
    }

    public void add(Person person, Direction direction) {
        if (direction.equals(Direction.UP)) {
            upUserSetLock.writeLock().lock();
            waitingUpUserSet.add(person);
            upUserSetLock.writeLock().unlock();
        } else if(direction.equals(Direction.DOWN)) {
            downUserSetLock.writeLock().lock();
            waitingDownUserSet.add(person);
            downUserSetLock.writeLock().unlock();
        }
        addDirectionTask(direction);
    }

    private void addDirectionTask(Direction direction) {
        waitingDirectionMapLock.readLock().lock();
        if (!waitingDirectionMap.containsKey(direction)) {
            Task task = Task.generate(this, direction);
            waitingDirectionMap.put(direction, task);
            dispatcher.dispatch(task);
        }
        waitingDirectionMapLock.readLock().unlock();
    }

    Set<Person> reduce(Direction direction, int num) {
        Set<Person> reduceSet = new HashSet<>(num);
        Set<Person> waitingUserSet = null;
        ReadWriteLock userSetLock = null;
        if (direction.equals(Direction.UP)) {
            waitingUserSet = waitingUpUserSet;
            userSetLock = upUserSetLock;
        } else if (direction.equals(Direction.DOWN)) {
            waitingUserSet = waitingDownUserSet;
            userSetLock = downUserSetLock;
        }
        return reduceSet;
    }

    void cancel(Direction direction) {
        if (waitingDirectionMap.containsKey(direction)) {
            dispatcher.cancel(waitingDirectionMap.get(direction));
            waitingDirectionMapLock.writeLock().lock();
            waitingDirectionMap.remove(direction);
            waitingDirectionMapLock.writeLock().unlock();
        }
    }

    void done(Direction direction) {
        if (waitingDirectionMap.containsKey(direction)) {
            waitingDirectionMapLock.writeLock().lock();
            waitingDirectionMap.remove(direction);
            waitingDirectionMapLock.writeLock().unlock();
        }
    }

    public Direction locate(Floor targetFloor) {
        return id > targetFloor.getId() ? Direction.UP : Direction.DOWN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Floor floor = (Floor) o;

        return id == floor.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

}
