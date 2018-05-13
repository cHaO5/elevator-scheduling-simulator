package util;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.jfoenix.controls.JFXButton;
import domain.Floor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;


/**
 * 系统运行依赖的环境参数
 *
 */
public class Env {
    //============电梯运行的环境参数============
    /**
     * 总楼层数
     */
    public static final int FLOOR_NUM = 20;
    /**
     * 最大负载人数
     */
    public static final int MAX_LOAD = 1000;
    /**
     * 电梯数
     */
    public static final int ELEVATOR_NUM = 5;
    /**
     * 总用户数
     */
    public static final int USER_NUM = 100;
    /**
     * 时间单位
     */
    public static final TimeUnit TIME_UNIT = TimeUnit.MILLISECONDS;
    /**
     * 所有操作需要流逝的时间长度
     */
    public static final int ELAPSED_TIME = 100;

    //============其它============

    public static CountDownLatch LATCH = new CountDownLatch(ELEVATOR_NUM);
    //private static final Logger LOGGER = LoggerFactory.getLogger(Env.class);

//    public static void show() {
//        LOGGER.info("average wait time {}", TOTAL_USER_WAIT_TIME.get() / (double) USER_NUM);
//        LOGGER.info("average elevator move distance {}", TOTAL_ELEVATOR_MOVE_DISTANCE.get() / (double) ELEVATOR_NUM);
//    }

    public static void elapsed() {
        try {
            TIME_UNIT.sleep(ELAPSED_TIME);
        } catch (InterruptedException e) {
            //LOGGER.error("elapsed timeout");
            System.out.println("elapsed timeout");
        }
    }

     //public Map<JFXButton, Integer> floorMap = {floor01};
}
