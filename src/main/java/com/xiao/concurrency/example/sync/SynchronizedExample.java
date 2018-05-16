package com.xiao.concurrency.example.sync;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 〈〉<br>
 *
 * @author jianjun.xiao
 * @create 2018/5/16 10:44
 * @since 1.0.0
 */
@Slf4j
public class SynchronizedExample {

    /**
     * 修饰代码块：大括号括起来的代码，作用于【调用对象】
     */
    public void block(int j) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("block {} -> {}", j, i);
            }
        }
    }

    /**
     * 修饰方法: 整个方法，作用域【调用对象】
     */
    public synchronized void method(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("method {} -> {}", j, i);
        }
    }

    /**
     * 修饰静态方法：整个静态方法，作用于【所有对象】
     */
    public static synchronized void methodStatic(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("methodStatic {} -> {}", j, i);
        }
    }

    /**
     * 修饰类：括号括起来的部分，作用于【所有对象】
     */
    public void clazz(int j) {
        synchronized (SynchronizedExample.class) {
            for (int i = 0; i < 10; i++) {
                log.info("classStatic {} -> {}", j, i);
            }
        }
    }

    // 相同对象调用同步代码块测试
    public void blockSameObjTest() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        SynchronizedExample example = new SynchronizedExample();
        executorService.execute(() -> {
            example.block(1);
        });

        executorService.execute(() -> {
            example.block(2);
        });
    }

    // 不同对象调用同步代码块测试
    public void blockDifObjTest() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        SynchronizedExample example1 = new SynchronizedExample();
        SynchronizedExample example2 = new SynchronizedExample();
        executorService.execute(() -> {
            example1.block(1);
        });

        executorService.execute(() -> {
            example2.block(2);
        });
    }

    // 相同对象调用同步方法测试
    public void methodSameObjTest() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        SynchronizedExample example = new SynchronizedExample();
        executorService.execute(() -> {
            example.method(1);
        });

        executorService.execute(() -> {
            example.method(2);
        });
    }

    // 不同对象调用同步方法测试
    public void methodDifObjTest() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        SynchronizedExample example1 = new SynchronizedExample();
        SynchronizedExample example2 = new SynchronizedExample();
        executorService.execute(() -> {
            example1.method(1);
        });

        executorService.execute(() -> {
            example2.method(2);
        });
    }

    // 相同对象调用同步方法测试
    public void methodStaticSameObjTest() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            methodStatic(1);
        });

        executorService.execute(() -> {
            methodStatic(2);
        });
    }

    public void methodStaticDifObjTest() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        SynchronizedExample example1 = new SynchronizedExample();
        SynchronizedExample example2 = new SynchronizedExample();
        executorService.execute(() -> {
            methodStatic(1);
        });

        executorService.execute(() -> {
            methodStatic(2);
        });
    }

    public void classSameObjTest() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        SynchronizedExample example = new SynchronizedExample();
        executorService.execute(() -> {
            example.clazz(1);
        });

        executorService.execute(() -> {
            example.clazz(2);
        });
    }

    public void classDifObjTest() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        SynchronizedExample example = new SynchronizedExample();
        SynchronizedExample example2 = new SynchronizedExample();
        executorService.execute(() -> {
            example.clazz(1);
        });

        executorService.execute(() -> {
            example2.clazz(2);
        });
    }

    public static void main(String[] args) {
        SynchronizedExample example = new SynchronizedExample();
        // example.blockSameObjTest();
        // example.blockDifObjTest();
        // example.methodSameObjTest();
        // example.methodDifObjTest();
        // example.methodStaticSameObjTest();
        // example.methodStaticDifObjTest();
        // example.classSameObjTest();
        example.classDifObjTest();
    }

}
