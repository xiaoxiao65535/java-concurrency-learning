package com.xiao.concurrency.example.singleton;

import com.xiao.concurrency.annotations.Recommend;
import com.xiao.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 懒汉模式 -> 双重同步锁单例模式
 *
 * @author jianjun.xiao
 * @create 2018/5/16 16:12
 * @since 1.0.0
 */
@Slf4j
@ThreadSafe
@Recommend
public class Singleton {

    private Singleton() {

    }

    // new 操作 CPU如何操作
    // 1. memory = allocate() 分配内存空间
    // 2. ctorInstance() 初始化对象
    // 3. instance = memory 设置instance指向内存


    // JVM和CPU优化，发生了指令重排
    // 1. memory = allocate() 分配内存空间
    // 3. instance = memory 设置instance指向内存
    // 2. ctorInstance() 初始化对象

    // volatile禁止指令重排，没有加上该关键字不保证线程安全
    private static volatile Singleton instance;

    public static Singleton getInstance() {
        if (instance == null) { // 双重检测机制  线程A检测得到有实例，直接返回实例引用
            synchronized (Singleton.class) { // 锁
                if (instance == null) {
                    instance = new Singleton(); // 线程B -> 3因为指令重排 还没有初始化对象
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                log.info("{}", Singleton.getInstance().hashCode());
            });
        }
        executorService.shutdown();
    }

}
