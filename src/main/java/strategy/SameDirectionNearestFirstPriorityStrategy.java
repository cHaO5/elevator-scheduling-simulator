package strategy;

import domain.Elevator;
import domain.Task;
import util.Calc;
import util.Env;

/**
 * 同相距离最近优先策略
 *
 */
public class SameDirectionNearestFirstPriorityStrategy implements PriorityCalculationStrategy {

    private static final int MAX_PRIORITY = 2 * Env.FLOOR_NUM;

    /**
     * x = 任务所处楼层号
     * y = 电梯所处楼层号
     * e = 总楼层数
     * 电梯向上走时的计算逻辑：
     * 1、同向、顺路 -> p = x - y
     * 2、同向、不顺路 -> p = 2 * e - y - x
     * 3、不同向 -> p = 2 * e - x + y
     * <p>
     * 电梯向下走时的计算逻辑：
     * 1、同向、顺路 -> p = y - x
     * 2、同向、不顺路 -> p = 2 * e - x + y
     * 3、不同向 -> p = x + y
     *
     * @param elevator 参与计算的电梯
     * @param task     参与计算的任务
     * @return 任务优先级
     */
    @Override
    public int calcPriority(Elevator elevator, Task task) {
        int x = task.getSrcFloor().getFloorNo();
        int y = elevator.getCurrFloor().getFloorNo();
        int priority;
        //elevator.Status can not be changed when this code block is running
        elevator.getStatusLock().readLock().lock();
        boolean isSameDirection = Calc.isSameDirection(elevator, task);
        switch (elevator.getStatus()) {
            case RUNNING_UP:
                priority = calcPriorityOnRunningUp(x, y, isSameDirection);
                break;
            case RUNNING_DOWN:
                priority = calcPriorityOnRunningDown(x, y, isSameDirection);
                break;
            case STOP:
                //                priority = Direction.UP.equals(task.getSrcFloor().locate(elevator.getCurrFloor()))
                //                        ? calcPriorityOnRunningUp(x, y, isSameDirection)
                //                        : calcPriorityOnRunningDown(x, y, isSameDirection);
                priority = Math.abs(x - y);
                break;
            default:
                throw new IllegalArgumentException();
        }
        elevator.getStatusLock().readLock().unlock();
        //priority已经是2倍楼层总数了，优先级要循环
        //        if (priority == MAX_PRIORITY) {
        //            priority = 0;
        //        }
        return priority;
    }

    private int calcPriorityOnRunningDown(int x, int y, boolean isSameDirection) {
        int priority;
        boolean isOnTheWay = y >= x;
        if (isOnTheWay && isSameDirection) {
            priority = y - x;
        } else if (!isOnTheWay && isSameDirection) {
            priority = MAX_PRIORITY - x + y;
            //priority = x + y;
        } else {
            priority = x + y;
            //priority = MAX_PRIORITY + x + y;
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
            //priority = x + y;
        } else {
            priority = MAX_PRIORITY - x - y;
            //priority = MAX_PRIORITY + x + y;
        }
        return priority;
    }
}
