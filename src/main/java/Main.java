
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import domain.Dispatcher;
import domain.Elevator;
import domain.Floor;
import domain.User;
import strategy.*;
import util.Env;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import static util.Env.ELEVATOR_NUM;
import static util.Env.FLOOR_NUM;
import static util.Env.USER_NUM;



public class Main {

    //private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws InterruptedException {
        printLogo();
        int epoch = 0;
        while (true) {
            //LOGGER.info("===================================={}====================================", epoch++);
            Env.LATCH = new CountDownLatch(ELEVATOR_NUM);
            //Env.TOTAL_ELEVATOR_MOVE_DISTANCE.set(0);
            //Env.TOTAL_USER_WAIT_TIME.set(0);
            //epoch(args[0], args[1]);
            epoch("RandomDispatch", "SameDirectionNearestFirst");
            Env.LATCH.await();
            //Env.show();
        }
    }

    private static void printLogo() {
        //LOGGER.info("\n"
//            + "        \"\"#                           m\n"
//            + "  mmm     #     mmm   m   m   mmm   mm#mm   mmm    m mm\n"
//            + " #\"  #    #    #\"  #  \"m m\"  \"   #    #    #\" \"#   #\"  \"\n"
//            + " #\"\"\"\"    #    #\"\"\"\"   #m#   m\"\"\"#    #    #   #   #\n"
//            + " \"#mm\"    \"mm  \"#mm\"    #    \"mm\"#    \"mm  \"#m#\"   #");
        System.out.println("printLogo");
    }

    private static void epoch(String dispatchStrategyStr, String priorityStrategyStr) throws InterruptedException {
        //generate all floor
        List<Floor> floorList = new ArrayList<>(FLOOR_NUM);
        for (int i = 0; i < FLOOR_NUM; i++) {
            Floor floor = new Floor(i + 1);
            floorList.add(floor);
            if (i > 0) {
                floorList.get(i - 1).next(floor);
            }
        }

        //make priority strategy
        PriorityCalculationStrategy priorityStrategy = selectPriorityStrategy(priorityStrategyStr);

        //generate all elevator
        List<Elevator> elevatorList = new ArrayList<>(ELEVATOR_NUM);
        for (int i = 0; i < ELEVATOR_NUM; i++) {
            elevatorList.add(new Elevator(i, floorList.get(0), priorityStrategy));
        }

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



        //sleep(1000);
        randomSimulate(floorList);

    }

    private static PriorityCalculationStrategy selectPriorityStrategy(String priorityStrategyStr) {
        PriorityCalculationStrategy priorityCalculationStrategy;
        switch (priorityStrategyStr) {
            case "SameDirectionNearestFirst":
                priorityCalculationStrategy = new SameDirectionNearestFirstPriorityStrategy();
                //LOGGER.debug("priorityCalculationStrategy = SameDirectionNearestFirst");
                System.out.println("priorityCalculationStrategy = SameDirectionNearestFirst");
                break;
            default:
                priorityCalculationStrategy = new SameDirectionNearestFirstPriorityStrategy();
                //LOGGER.debug("priorityCalculationStrategy = SameDirectionNearestFirst");
                System.out.println("priorityCalculationStrategy = SameDirectionNearestFirst");
                break;
        }
        return priorityCalculationStrategy;
    }

    private static DispatchStrategy selectDispatchStrategy(String dispatchStrategyStr) {
        DispatchStrategy dispatchStrategy;
        switch (dispatchStrategyStr) {
            case "RandomDispatch":
                dispatchStrategy = new RandomDispatchStrategy();
                //LOGGER.debug("dispatchStrategy = RandomDispatch");
                System.out.println("dispatchStrategy = RandomDispatch");
                break;
            case "PriorityFirstDispatch":
                dispatchStrategy = new PriorityFirstDispatchStrategy();
                //LOGGER.debug("dispatchStrategy = PriorityFirstDispatch");
                System.out.println("dispatchStrategy = PriorityFirstDispatch");
                break;
            default:
                dispatchStrategy = new RandomDispatchStrategy();
                //LOGGER.debug("dispatchStrategy = RandomDispatch");
                System.out.println("dispatchStrategy = RandomDispatch");
                break;
        }
        return dispatchStrategy;
    }

    private static void randomSimulate(List<Floor> floorList) throws InterruptedException {
        //generate all user
        Random random = new Random();
        for (int i = 0; i < USER_NUM; i++) {
            Env.elapsed();
            //站在什么楼层
            int randomSrcFloorNo = random.nextInt(FLOOR_NUM);
            Floor srcFloor = floorList.get(randomSrcFloorNo);
            //想去什么楼层
            Floor targetFloor = floorList.get(differentFloorNo(randomSrcFloorNo));
            User user = new User("lucy" + i, targetFloor);
            //LOGGER.debug("{} come to src_floorNo={}", user, srcFloor.getFloorNo());
            System.out.println(user + " from " + srcFloor.getFloorNo());
            //srcFloor.locate(targetFloor).opposite()，结果Direction一定是对的，但是这里也支持传错的，也符合实际
            srcFloor.add(user, srcFloor.locate(targetFloor).opposite());
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
            result = random.nextInt(FLOOR_NUM);
        } while (result == randomSrcFloorNo);
        return result;
    }
}
