package domain;

public class Person {
    private int id;

    private int targetFloor;

    private int startFloor;

    private Elevator elevator;

    public Person(int id, int targetFloor, int startFloor, Elevator elevator) {
        this.id = id;
        this.targetFloor = targetFloor;
        this.startFloor = startFloor;
        this.elevator = elevator;
    }

    public int getStartFloor() {
        return startFloor;
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    public Elevator getElevator() {
        return elevator;
    }
}
