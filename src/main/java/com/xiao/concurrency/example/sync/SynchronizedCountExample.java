
package com.xiao.concurrency.example.sync;

import com.xiao.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 〈并发测试〉<br>
 *
 * @author jianjun.xiao
 * @create 2018/5/15 16:08
 * @since 1.0.0
 */
@Slf4j
@ThreadSafe
public class SynchronizedCountExample {

    // 并发数
    public static int clientTotal = 10000;
    // 线程数
    public static int threadTatal = 200;

    public static int count = 0;

    public static synchronized void add() {
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建线程池(此种方式不推荐)
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 定义信号量
        final Semaphore semaphore = new Semaphore(threadTatal);
        // 闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire(); // 判断是否允许被执行
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception:{}", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        // 关闭线程池
        executorService.shutdown();
        log.debug("count:{}", count);
    }
}

