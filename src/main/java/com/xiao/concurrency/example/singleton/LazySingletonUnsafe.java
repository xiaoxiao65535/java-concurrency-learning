package com.xiao.concurrency.example.singleton;

import com.xiao.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 〈懒汉单例模式〉<br>
 * 单例实例在第一次使用的时候创建
 *
 * @author jianjun.xiao
 * @create 2018/5/16 15:52
 * @since 1.0.0
 */
@Slf4j
@NotThreadSafe
public class LazySingletonUnsafe {

    private LazySingletonUnsafe() {

    }

    private static LazySingletonUnsafe instance;


    public static LazySingletonUnsafe getInstance() {
        if (instance == null) {
            // 多线程下有线程问题
            instance = new LazySingletonUnsafe();
        }
        return instance;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                log.info("{}", LazySingletonUnsafe.getInstance().hashCode());
            });
        }
        executorService.shutdown();
    }

}
