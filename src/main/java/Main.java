import domain.Dispatcher;
import domain.Elevator;
import domain.Floor;
import domain.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import strategy.*;
import util.Env;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.sleep;
import static util.Env.ELEVATOR_NUM;
import static util.Env.FLOOR_NUM;
import static util.Env.USER_NUM;
import static util.Resource.*;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ui/view.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        stage.setTitle("Elevator Schedule Simulator");

        new Thread(() -> {
            try {
                backend();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void backend() throws InterruptedException {
        while (true) {
            Env.LATCH = new CountDownLatch(ELEVATOR_NUM);
            begin();
            Env.LATCH.await();
        }

    }

    private static void begin() throws InterruptedException {
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
        PriorityCalculationStrategy priorityStrategy = new SameDirectionNearestFirstPriorityStrategy();

        //generate all elevator
        List<Elevator> elevatorList = new ArrayList<>(ELEVATOR_NUM);
        for (int i = 0; i < ELEVATOR_NUM; i++) {
            elevatorList.add(new Elevator(i, floorList.get(0), priorityStrategy));
        }


        //make dispatch strategy
        //DispatchStrategy dispatchStrategy = new RandomDispatchStrategy();
        DispatchStrategy dispatchStrategy = new PriorityFirstDispatchStrategy();

        //generate dispatcher
        Dispatcher dispatcher = new Dispatcher(elevatorList, dispatchStrategy);

        //elevator set dispatcher
        elevatorList.forEach(elevator -> elevator.setDispatcher(dispatcher));

        //floor set dispatcher
        floorList.forEach(floor -> floor.setDispatcher(dispatcher));

        //elevator run
        elevatorList.forEach(elevator -> new Thread(elevator, "elevator-thread-" + elevator.getId()).start());

        randomSimulate(floorList);

    }

    private static void randomSimulate(List<Floor> floorList) throws InterruptedException {
        //generate all user
        Random random = new Random();

        for (int i = 0; i < USER_NUM; i++) {
            Env.elapsed();

            //站在什么楼层
            if (input && floorPressed) {
                input = false;
                System.out.println("vip has already pressed the button!!!!!!!");

                //站在什么楼层
                Floor srcFloor = floorList.get(userFloorNo - 1);
                //想去什么楼层
                sleep(3000);
                if (userTarget != 0) {
                    errorInput = false;
                    System.out.println("vip is going to" + userTarget);
                    Floor targetFloor = floorList.get(userTarget - 1);
                    User user = new User("vip" , targetFloor);
                    user.setVip(true);
                    //LOGGER.debug("{} come to src_floorNo={}", user, srcFloor.getFloorNo());
                    System.out.println("vvvvvvvvip" + user + " from " + srcFloor.getFloorNo());
                    //srcFloor.locate(targetFloor).opposite()，结果Direction一定是对的，但是这里也支持传错的，也符合实际
                    srcFloor.add(user, srcFloor.locate(targetFloor).opposite());
                    //srcFloor.add(user, Direction.DOWN);
                    //sleep(1000);
                } else {
                    floorPressed = false;
                    recover = true;
                    errorInput = true;
                }
            } else {
                int randomSrcFloorNo = random.nextInt(FLOOR_NUM);
                Floor srcFloor = floorList.get(randomSrcFloorNo);
                //想去什么楼层
                Floor targetFloor = floorList.get(differentFloorNo(randomSrcFloorNo));
                User user = new User("lucy" + i, targetFloor);
                //LOGGER.debug("{} come to src_floorNo={}", user, srcFloor.getFloorNo());
                System.out.println(user + " from " + srcFloor.getFloorNo());
                //srcFloor.locate(targetFloor).opposite()，结果Direction一定是对的，但是这里也支持传错的，也符合实际
                srcFloor.add(user, srcFloor.locate(targetFloor).opposite());
            }
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
