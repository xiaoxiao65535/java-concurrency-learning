package com.xiao.concurrency.example.singleton;

import com.xiao.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 〈饿汉单例模式〉<br>
 * 单例实例在类装载的时候
 * 问题：1. 如果初始化处理逻辑过多，可能会有性能问题
 * 2. 如果实例化之后没有使用，造成资源浪费
 *
 * @author jianjun.xiao
 * @create 2018/5/16 15:46
 * @since 1.0.0
 */
@Slf4j
@ThreadSafe
public class EagerSingleton {

    // 私有构造方法
    private EagerSingleton() {
    }

    private static EagerSingleton instance = new EagerSingleton();

    public static EagerSingleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                log.info("{}", EagerSingleton.getInstance().hashCode());
            });
        }
        executorService.shutdown();
    }

}
