package domain;

import domain.enumeration.ElevatorStatus;

public class Elevator {
    private int id;

    private int currentFloor;

    //承载量
    //private int load;

    private ElevatorStatus status;

    private Task currentTask;

    private Dispatch dispatch;
}
