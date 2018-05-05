package domain;

import strategy.DispatchStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Dispatcher {
    /**
     * 可以调度的电梯列表
     */
    private List<Elevator> elevatorList = new ArrayList<>();
    /**
     * 任务分配策略
     */
    private DispatchStrategy dispatchStrategy;
    /**
     * 保证 elevatorList 的读写互斥
     */
    private ReadWriteLock elevatorListLock = new ReentrantReadWriteLock();
    /**
     * 用于异步完成dispatch task
     */
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

//    public Dispatcher(List<Elevator> elevatorList, DispatchStrategy dispatchStrategy) {
//        this.elevatorList = elevatorList;
//        this.dispatchStrategy = dispatchStrategy;
//    }

    public Dispatcher(List<Elevator> elevatorList, DispatchStrategy dispatchStrategy) {
        this.elevatorList = elevatorList;
        this.dispatchStrategy = dispatchStrategy;
    }

    public void dispatch(Task task) {
        if (task == null) {
            return;
        }
        if (executorService != null && !executorService.isShutdown()) {
            executorService.submit(() -> {
                Elevator elevator;
                while ((elevator = dispatchStrategy.select(elevatorList, task)) == null) {

                }
                elevator.receive(task);
            });
        }
    }

    void cancel(Task task) {
        task.cancel();
    }

    void redispatch(Task task) {
        dispatch(task);
    }

    void quit(Elevator elevator) {
        elevatorListLock.writeLock().lock();
        elevatorList.removeIf(e -> e.equals(elevator));
        elevatorListLock.writeLock().unlock();
        if (elevatorList.isEmpty()) {
            executorService.shutdown();
        }
    }

    public ReadWriteLock getElevatorListLock() {
        return elevatorListLock;
    }
}
