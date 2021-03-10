package cn.xzxy.lewy.gc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试 full GC
 * 需要在启动时，加上参数 -XX:+PrintGCDetails
 */
public class FullGCProblem {

    private static class CardInfo {
        BigDecimal price = new BigDecimal("0.0");
        String name = "Lewy";
        int age = 32;
        Date birthday = new Date();

        private void withdraw() {

        }
    }

    private static ScheduledThreadPoolExecutor executor =
            new ScheduledThreadPoolExecutor(50, new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void main(String[] args) throws Exception {
        executor.setMaximumPoolSize(50);

        for (; ; ) {
            modelFit();
            Thread.sleep(100);
        }
    }

    private static void modelFit() {
        List<CardInfo> taskList = getAllCardInfo();
        taskList.forEach(info -> {
            executor.scheduleWithFixedDelay(info::withdraw, 2, 3, TimeUnit.SECONDS);
        });

    }

    private static List<CardInfo> getAllCardInfo() {
        List<CardInfo> taskList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            CardInfo cardInfo = new CardInfo();
            taskList.add(cardInfo);
        }

        return taskList;
    }
}
