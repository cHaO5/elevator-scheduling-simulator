package strategy;

import domain.Elevator;
import domain.Task;
import domain.enumeration.Direction;
import domain.enumeration.ElevatorStatus;

import static util.Resource.FLOOR_NUM;

public class SameDirectionNearestFirstPriorityStrategy {

    private static final int MAX_PRIORITY = 2 * FLOOR_NUM;

    public int calcPriority(Elevator elevator, Task task) {
        int x = task.getSrcFloor().getFloorNo();
        int y = elevator.getCurrFloor().getFloorNo();
        int priority;
        //elevator.Status can not be changed when this code block is running
        elevator.getStatusLock().readLock().lock();
        boolean isSameDirection = isSameDirection(elevator, task);
        switch (elevator.getStatus()) {
            case RUNNING_UP:
                priority = calcPriorityOnRunningUp(x, y, isSameDirection);
                break;
            case RUNNING_DOWN:
                priority = calcPriorityOnRunningDown(x, y, isSameDirection);
                break;
            case STOP:
                priority = Math.abs(x - y);
                break;
            default:
                throw new IllegalArgumentException();
        }
        elevator.getStatusLock().readLock().unlock();
        return priority;
    }

    private int calcPriorityOnRunningDown(int x, int y, boolean isSameDirection) {
        int priority;
        boolean isOnTheWay = y >= x;
        if (isOnTheWay && isSameDirection) {
            priority = y - x;
        } else if (!isOnTheWay && isSameDirection) {
            priority = MAX_PRIORITY - x + y;
        } else {
            priority = x + y;
        }
        return priority;
    }

    private int calcPriorityOnRunningUp(int x, int y, boolean isSameDirection) {
        boolean isOnTheWay = x <= y;
        int priority;
        if (isOnTheWay && isSameDirection) {
            priority = x - y;
        } else if (!isOnTheWay && isSameDirection) {
            priority = MAX_PRIORITY - y + x;
        } else {
            priority = MAX_PRIORITY - x - y;
        }
        return priority;
    }

    public boolean isSameDirection(Elevator elevator, Task task) {
        return (elevator.getStatus().equals(ElevatorStatus.RUNNING_UP) && task.getDirection().equals(Direction.UP))
                || (elevator.getStatus().equals(ElevatorStatus.RUNNING_DOWN) && task.getDirection().equals(Direction.DOWN))
                || task.getDirection().equals(Direction.NONE)
                || elevator.getStatus().equals(ElevatorStatus.STOP);
    }
}
