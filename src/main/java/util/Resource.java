package util;

import domain.enumeration.Direction;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static domain.enumeration.Direction.NONE;

public class Resource {
    private static int[] elevator = new int[] {1, 1, 1, 1, 1};
    private static int[] upButton = new int[21];
    private static int[] downButton = new int[21];


    //Floor display
    public static int userFloorNo = 1;

    public static Direction userDirection = NONE;

    public static boolean floorPressed = false;

    //Elevator display
    public static Direction elevatorDirection = NONE;

    public static boolean elevatorPressed = false;

    public static int userTarget = 0;

    private static int currElevator;

    public static boolean recover = false;

    //防止一直卡在if（floorpressed）那里，只输入一次
    public static boolean input = false;

    public static boolean errorInput = false;

    public static boolean finished = false;

    public static void setUpButton(int floor, int turnOn) {
        System.out.println(floor + " " + getUpButton(floor));
        upButton[floor] = turnOn;
        System.out.println(floor + " " + getUpButton(floor));
    }

    public static void setDownButton(int floor, int turnOn) {
        System.out.println(floor + " " + getDownButton(floor));
        downButton[floor] = turnOn;
        System.out.println(floor + " " + getDownButton(floor));
    }

    public static void setElevator(int elevatorNo, int floor) {
        elevator[elevatorNo] = floor;
    }

    public static int getUpButton(int floor) {
        return upButton[floor];
    }

    public static int getDownButton(int floor) {
        return downButton[floor];
    }

    public static int getElevator(int elevatorNo) {
        return elevator[elevatorNo];
    }

    //总楼层数
    public static final int FLOOR_NUM = 20;
    //最大负载人数
    public static final int MAX_LOAD = 10;
    //电梯数
    public static final int ELEVATOR_NUM = 5;
    //总用户数
    public static final int USER_NUM = 1000;
    //时间单位
    public static final TimeUnit TIME_UNIT = TimeUnit.MILLISECONDS;
    //所有操作需要流逝的时间长度
    public static final int ELAPSED_TIME = 100;

    public static CountDownLatch LATCH = new CountDownLatch(ELEVATOR_NUM);

    public static void elapsed() {
        try {
            TIME_UNIT.sleep(ELAPSED_TIME);
        } catch (InterruptedException e) {
            System.out.println("elapsed timeout");
        }
    }
}
