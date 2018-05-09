package util;

import java.util.ArrayList;
import java.util.List;

import static util.Env.ELEVATOR_NUM;

public class Resource {
//    private static List<Integer> elevator = new ArrayList<>();
//
//    private static List<Integer> upButton = new ArrayList<>();
//
//    private static List<Integer> downButton = new ArrayList<>();
    //private static int[] elevator = new int[6];
    private static int[] upButton = new int[21];
    private static int[] downButton = new int[21];

    private static int currElevator;

    private static String floorNo;

    private static String elevatorFloorNo;

    private static int elevatorDirection;

    public Resource() {
//        for (int i = 1; i < 6; i++) {
//            //elevator.add(0);
//            elevator[i] = 0;
//        }

        for (int i = 1; i <= 20; i++) {
//            upButton.add(0);
//            downButton.add(0);
            upButton[i] = 0;
            downButton[i] = 0;
        }

        floorNo = "--";
        elevatorFloorNo = "--";
        elevatorDirection = 0;
    }

//    public static void setElevator(int elevatorNo, int floor) {
//        elevator.set(elevatorNo, floor);
//        reflashElevator(floor);
//    }

    public static void setUpButton(int floor, int turnOn) {
        System.out.println(getUpButton(floor));
        upButton[floor] = turnOn;
        System.out.println(getUpButton(floor));
    }

    public static void setDownButton(int floor, int turnOn) {
        System.out.println(getDownButton(floor));
        downButton[floor] = turnOn;
        System.out.println(getDownButton(floor));
    }

//    public static void setElevator(int cur, int next) {
//        elevator[cur] = 0;
//        elevator[next] = 1;
//    }

    public static void setCurrElevator(int floor) {
        currElevator = floor;
    }

    public static void setFloorNo(String floorNo) {
        floorNo = floorNo;
    }

    public static void setElevatorFloorNo(String elevatorFloorNo) {
        elevatorFloorNo = elevatorFloorNo;
    }

    public static void setElevatorDirection(int elevatorDirection) {
        elevatorDirection = elevatorDirection;
    }

    public static int getUpButton(int floor) {
        return upButton[floor];
    }

    public static int getDownButton(int floor) {
        return downButton[floor];
    }

//    public static int getElevator(int floor) { return elevator[floor]; }

    public static int getCurrElevator() { return currElevator; }
}
