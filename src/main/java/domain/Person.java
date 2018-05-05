package domain;

import domain.enumeration.Direction;

public class Person {
    private int id;

    private Floor targetFloor;

    private Elevator elevator;

    public Person(int id, Floor targetFloor) {
        this.id = id;
        this.targetFloor = targetFloor;
    }

    void selectElevator(Floor targetFloor) {
        Task task = Task.generate(targetFloor, Direction.NONE);
        elevator.receive(task);
    }

    void enterElevator(Elevator elevator) {
        this.elevator = elevator;
    }

//    public int getStartFloor() {
//        return startFloor;
//    }

    public Floor getTargetFloor() {
        return targetFloor;
    }

    public Elevator getElevator() {
        return elevator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Person person = (Person) o;
        return id == person.id;
    }
}
