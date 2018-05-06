package strategy;



import domain.Elevator;
import domain.Task;

import java.util.List;
import java.util.Random;

/**
 * 随机分配电梯策略
 *
 */
public class RandomDispatchStrategy implements DispatchStrategy {
    private Random random = new Random();

    @Override
    public Elevator select(List<Elevator> elevatorList, Task task) {
        return elevatorList.get(random.nextInt(elevatorList.size()));
    }

}
