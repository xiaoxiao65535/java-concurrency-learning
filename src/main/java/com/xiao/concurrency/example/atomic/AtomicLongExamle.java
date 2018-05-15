package com.xiao.concurrency.example.atomic;

import com.xiao.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 〈〉<br>
 *
 * @author jianjun.xiao
 * @create 2018/5/15 17:20
 * @since 1.0.0
 */
@Slf4j
@ThreadSafe
public class AtomicLongExamle {

    // 线程数
    public static int threadTotal = 200;
    // 并发数
    public static int clientTatal = 5000;
    // 计数器
    public static AtomicLong count = new AtomicLong(0L);

    public static void add() {
        count.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 创建信号量
        Semaphore semaphore = new Semaphore(threadTotal);
        // 闭锁
        CountDownLatch countDownLatch = new CountDownLatch(clientTatal);
        // 循环调用
        for (int i = 0; i < clientTatal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception:{}", e);
                }
                countDownLatch.countDown();
            });
        }
        // 关闭计数器
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}", count.get());
    }

}
