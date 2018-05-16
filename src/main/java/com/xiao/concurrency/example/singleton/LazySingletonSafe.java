package com.xiao.concurrency.example.singleton;

import com.xiao.concurrency.annotations.NotRecommend;
import com.xiao.concurrency.annotations.ThreadSafe;
import javafx.scene.chart.StackedAreaChart;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 〈线程安全的懒汉模式〉<br>
 * 通过synchronized保证安全性，有性能开销
 *
 * @author jianjun.xiao
 * @create 2018/5/16 16:05
 * @since 1.0.0
 */
@Slf4j
@ThreadSafe
@NotRecommend
public class LazySingletonSafe {

    private LazySingletonSafe() {

    }

    private static LazySingletonSafe instance;

    public static synchronized LazySingletonSafe getInstance() {
        if (instance == null) {
            // 多线程下有线程问题
            instance = new LazySingletonSafe();
        }
        return instance;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                log.info("{}", LazySingletonSafe.getInstance().hashCode());
            });
        }
        executorService.shutdown();
    }

}
