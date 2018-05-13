package domain;

import strategy.PriorityFirstDispatchStrategy;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.lang.Thread.sleep;
import static util.Resource.LATCH;
import static util.Resource.elapsed;

public class Dispatcher {

    //可以调度的电梯列表
    private List<Elevator> elevatorList;

    //任务分配策略
    private PriorityFirstDispatchStrategy dispatchStrategy;

    //保证 elevatorList 的读写互斥
    private ReadWriteLock elevatorListLock = new ReentrantReadWriteLock();

    //用于异步完成dispatch task
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public Dispatcher(List<Elevator> elevatorList, PriorityFirstDispatchStrategy dispatchStrategy) {
        this.elevatorList = elevatorList;
        this.dispatchStrategy = dispatchStrategy;
    }


    //给一个任务分配电梯
    void dispatch(Task task) {
        if (task == null) {
            return;
        }
        if (executorService != null && !executorService.isShutdown()) {
            executorService.submit(() -> {
                Elevator elevator;
                //如果选不出来电梯，就一直重试
                while ((elevator = dispatchStrategy.select(elevatorList, task)) == null) {
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    elapsed();
                }
                elevator.receive(task);
            });
        }
    }

    void cancel(Task task) {
        task.cancel();
    }


    //电梯发起的任务重分配
    void redispatch(Task task) {
        System.out.println("Redispatch task " + task);
        dispatch(task);
    }


    //一个电梯无任务退出
    void quit(Elevator elevator) {
        elevatorListLock.writeLock().lock();
        elevatorList.removeIf(e -> e.equals(elevator));
        elevatorListLock.writeLock().unlock();
        //无电梯可调度时要shutdown线程池
        if (elevatorList.isEmpty()) {
            executorService.shutdown();
        }
        LATCH.countDown();
    }

    public ReadWriteLock getElevatorListLock() {
        return elevatorListLock;
    }
}
