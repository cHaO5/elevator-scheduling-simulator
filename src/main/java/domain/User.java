package domain;

import domain.enumeration.Direction;


public class User {
    private boolean vip = false;
    private String name;
    //当前正在乘坐的电梯
    private Elevator elevator;

    //目标楼层
    private Floor targetFloor;

    public User(String name, Floor targetFloor) {
        this.name = name;
        this.targetFloor = targetFloor;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public boolean getVip() {
        return vip;
    }


    //用户选择楼层
    void select(Floor targetFloor) {
        Task task = Task.elevatorTask(targetFloor, Direction.NONE);
        System.out.println(this + " select " + targetFloor + " create " + task);
        elevator.receive(task);
    }

    void enterElevator(Elevator elevator) {
        //进入电梯
        this.elevator = elevator;
    }

    Floor getTargetFloor() {
        return targetFloor;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", targetFloor=" + targetFloor.getFloorNo() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;
        return name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }


}
