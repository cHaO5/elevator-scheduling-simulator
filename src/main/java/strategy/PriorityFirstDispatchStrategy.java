package strategy;



import domain.Elevator;
import domain.Task;
import util.Conf;

import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * 能给出最好的执行优先级的电梯可获得任务
 *
 */
public class PriorityFirstDispatchStrategy implements DispatchStrategy {

    @Override
    public Elevator select(List<Elevator> elevatorList, Task task) {
        if (elevatorList == null) {
            return null;
        }
        //get read lock and lock the elevatorList
        ReadWriteLock elevatorListLock = null;
        if (!elevatorList.isEmpty()) {
            elevatorListLock = elevatorList.get(0).getDispatcher().getElevatorListLock();
            elevatorListLock.readLock().lock();
        }
        //select the elevator which has the best priority
        int priority = Integer.MAX_VALUE;
        Elevator best = null;
        for (Elevator elevator : elevatorList) {
            //the code below can not tolerate elevator's currload being changed when running, so protect it
            elevator.getCurrLoadLock().readLock().lock();
            if (elevator.getCurrLoad().size() == Conf.MAX_LOAD) {
                continue;
            }
            int possiblePriority = elevator.tryReceive(task);
            if (priority > possiblePriority) {
                priority = possiblePriority;
                best = elevator;
            }
            elevator.getCurrLoadLock().readLock().unlock();
        }
        if (elevatorListLock != null) {
            elevatorListLock.readLock().unlock();
        }
        return best;
    }

}
