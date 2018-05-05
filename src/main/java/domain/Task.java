package domain;

import domain.enumeration.Direction;
import domain.enumeration.TaskStatus;

public class Task {
    private int id;

    private int createFloor;

    private Direction direction;

    private TaskStatus taskStatus;

    //private int priority;

    public Task(int id, int createFloor, Direction direction) {
        this.id = id;
        this.createFloor = createFloor;
        this.direction = direction;
        setTaskStatus(TaskStatus.RUNNABLE);
    }

    void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}
