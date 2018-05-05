import domain.Dispatcher;
import domain.Elevator;
import domain.Floor;
import domain.Person;
import strategy.*;
import util.Conf;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        while (true) {
            List<Floor> floorList = new ArrayList<>(Conf.FLOOR_NUM);
            for (int i = 0; i < Conf.FLOOR_NUM; i++) {
                Floor floor = new Floor(i + 1);
                floorList.add(floor);
                if (i > 0) {
                    floorList.get(i - 1).next(floor);
                }
            }

            List<Elevator> elevatorList = new ArrayList<>(Conf.ELEVATOR_NUM);
            for (int i = 0; i < Conf.ELEVATOR_NUM; i++) {
                elevatorList.add(new Elevator(i, floorList.get(0)));
            }

            String dispatchStrategyStr = "SameDirectionNearestFirst";

            //make dispatch strategy
            DispatchStrategy dispatchStrategy = selectDispatchStrategy(dispatchStrategyStr);

            //generate dispatcher
            Dispatcher dispatcher = new Dispatcher(elevatorList, dispatchStrategy);

            //elevator set dispatcher
            elevatorList.forEach(elevator -> elevator.setDispatcher(dispatcher));

            //floor set dispatcher
            floorList.forEach(floor -> floor.setDispatcher(dispatcher));

            //elevator run
            elevatorList.forEach(elevator -> new Thread(elevator, "elevator-thread-" + elevator.getId()).start());

            //simulation
            //        simulation1u(floorList);
            //        simulationNu(floorList);
            randomSimulate(floorList);
        }

    }


    private static PriorityCalculationStrategy selectPriorityStrategy(String priorityStrategyStr) {
        PriorityCalculationStrategy priorityCalculationStrategy;
        switch (priorityStrategyStr) {
            case "SameDirectionNearestFirst":
                priorityCalculationStrategy = new SameDirectionNearestFirstPriorityStrategy();
                break;
            default:
                priorityCalculationStrategy = new SameDirectionNearestFirstPriorityStrategy();
                break;
        }
        return priorityCalculationStrategy;
    }

    private static DispatchStrategy selectDispatchStrategy(String dispatchStrategyStr) {
        DispatchStrategy dispatchStrategy;
        switch (dispatchStrategyStr) {
            case "RandomDispatch":
                dispatchStrategy = new RandomDispatchStrategy();
                break;
            case "PriorityFirstDispatch":
                dispatchStrategy = new PriorityFirstDispatchStrategy();
                break;
            default:
                dispatchStrategy = new RandomDispatchStrategy();
                break;
        }
        return dispatchStrategy;
    }

    private static void randomSimulate(List<Floor> floorList) {
        //generate all user
        Random random = new Random();
        for (int i = 0; i < Conf.USER_NUM; i++) {
            Conf.elapsed();
            //站在什么楼层
            int randomSrcFloorNo = random.nextInt(Conf.FLOOR_NUM);
            Floor srcFloor = floorList.get(randomSrcFloorNo);
            //想去什么楼层
            Floor targetFloor = floorList.get(differentFloorNo(randomSrcFloorNo));
            Person person = new Person(i, targetFloor);
            //srcFloor.locate(targetFloor).opposite()，结果Direction一定是对的，但是这里也支持传错的，也符合实际
            srcFloor.add(person, srcFloor.locate(targetFloor).opposite());
            //srcFloor.add(user, Direction.DOWN);
        }
    }

    /**
     * 返回一下不一样的楼层
     *
     * @param randomSrcFloorNo
     * @return
     */
    private static int differentFloorNo(int randomSrcFloorNo) {
        Random random = new Random();
        int result;
        do {
            result = random.nextInt(Conf.FLOOR_NUM);
        } while (result == randomSrcFloorNo);
        return result;
    }
}
