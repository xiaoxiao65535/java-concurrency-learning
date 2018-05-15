package com.xiao.concurrency.example.atomic;

import com.xiao.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.LongAdder;

/**
 * 〈〉<br>
 *
 * @author jianjun.xiao
 * @create 2018/5/15 17:20
 * @since 1.0.0
 */
@Slf4j
@ThreadSafe
public class LongAdderExample {

    public static int threadTotal = 200;

    public static int clientTotal = 5000;

    public static LongAdder count = new LongAdder();

    public static void add() {
        count.increment();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("e:{}", e);
                }
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}", count);
    }

}
