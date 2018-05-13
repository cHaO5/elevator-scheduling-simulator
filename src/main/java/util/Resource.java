package util;

import domain.enumeration.Direction;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static domain.enumeration.Direction.NONE;
import static util.Env.ELEVATOR_NUM;

public class Resource {
//    private static List<Integer> elevator = new ArrayList<>();
//
//    private static List<Integer> upButton = new ArrayList<>();
//
//    private static List<Integer> downButton = new ArrayList<>();
    //private static int[] elevatorPreLoc = new int[5];
    private static int[] elevator = new int[] {1, 5, 9, 16, 13};
    private static int[] upButton = new int[21];
    private static int[] downButton = new int[21];


    //Floor display
    public static int userFloorNo = 1;
    //public static SimpleIntegerProperty userfloor = new SimpleIntegerProperty(1);

    public static Direction userDirection = NONE;

    public static boolean floorPressed = false;

    //Elevator display
    public static Direction elevatorDirection = NONE;

    public static int elevatorFloorNo = 0;

    public static boolean elevatorPressed = false;

    public static int userTarget = 0;

    //public static Queue<Integer> userTarget;

    private static int currElevator;

    public static boolean recover = false;

    //防止一直卡在if（floorpressed）那里，只输入一次
    public static boolean input = false;

    public static boolean started = false;

    public static boolean finished = false;




//    public static ReadWriteLock elevatorLock = new ReentrantReadWriteLock();

//    public Resource() {
//        for (int i = 0; i < 5; i++) {
//            //elevator.add(0);
//            elevator[i] = 1;
//            System.out.println("111111");
//        }
//
//        for (int i = 1; i <= 20; i++) {
////            upButton.add(0);
////            downButton.add(0);
//            upButton[i] = 0;
//            downButton[i] = 0;
//        }
//
//        floorNo = "--";
//        elevatorFloorNo = "--";
//        elevatorDirection = 0;
//    }

//    public static void setElevator(int elevatorNo, int floor) {
//        elevator.set(elevatorNo, floor);
//        reflashElevator(floor);
//    }

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

//    public static void setElevator(int cur, int next) {
//        elevator[cur] = 0;
//        elevator[next] = 1;
//    }

    public static void setElevator(int elevatorNo, int floor) {
        //elevatorLock.writeLock().lock();
        elevator[elevatorNo] = floor;
        //elevatorLock.writeLock().unlock();
    }

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

//    public static int getElevator(int i) { return elevator[i]; }

    public static int getCurrElevator() { return currElevator; }

    public static int getElevator(int elevatorNo) {
        return elevator[elevatorNo];
    }


    private static int[] floorDisplay = new int[] {1, 0};

    public static int getFloorDisplay(int i) {
        return floorDisplay[i];
    }

    public static void setFloorDisplay(int i, int floor) {
        Resource.floorDisplay[i] = floor;
    }
}
