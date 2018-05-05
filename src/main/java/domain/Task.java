package domain;

import domain.enumeration.Direction;
import domain.enumeration.TaskStatus;

import java.util.Random;

public class Task {
    private int id;

    private Floor targetFloor;

    private Direction direction;

    private Floor srcFloor;

    private TaskStatus status;

    public Task(int id, Floor targetFloor, Direction direction) {
        this.id = id;
        this.targetFloor = targetFloor;
        this.direction = direction;
    }

    public int getId() {
        return id;
    }

    public Floor getSrcFloor() {
        return srcFloor;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Direction getDirection() {
        return direction;
    }

    public Floor getTargetFloor() {
        return targetFloor;
    }

    public static Task generate(Floor targetFloor, Direction direction) {
        return new Task(new Random().nextInt(10000), targetFloor, direction);
    }

    void cancel() {
        setStatus(TaskStatus.CANCELLED);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Task task = (Task) o;
        //NONE方向的任务和所有方向的任务都equal
        return (srcFloor != null ? srcFloor.equals(task.srcFloor) : task.srcFloor == null)
                && (direction.equals(task.direction) || direction.equals(Direction.NONE) || task.direction.equals(
                Direction.NONE));
    }

    @Override
    public int hashCode() {
        int result = srcFloor != null ? srcFloor.hashCode() : 0;
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;

    }

}
