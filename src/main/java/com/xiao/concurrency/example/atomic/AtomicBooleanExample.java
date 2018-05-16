package com.xiao.concurrency.example.atomic;

import com.xiao.concurrency.annotations.Recommend;
import com.xiao.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 〈〉<br>
 * 某段代码只执行一次
 *
 * @author jianjun.xiao
 * @create 2018/5/16 10:13
 * @since 1.0.0
 */
@Slf4j
@ThreadSafe
@Recommend
public class AtomicBooleanExample {

    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    public static AtomicInteger count = new AtomicInteger(0);

    public static AtomicBoolean isHappend = new AtomicBoolean(false);

    public static void test() {
        if (isHappend.compareAndSet(false, true)) {
            count.incrementAndGet();
            log.info("execute");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("e:{}", e);
                }
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        executorService.shutdown();
        log.info("execute times: {},isHappend:{}", count.get(), isHappend.get());
    }

}
