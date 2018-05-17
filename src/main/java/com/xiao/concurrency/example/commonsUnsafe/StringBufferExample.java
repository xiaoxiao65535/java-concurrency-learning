package com.xiao.concurrency.example.commonsUnsafe;

import com.xiao.concurrency.annotations.NotThreadSafe;
import com.xiao.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author jianjun.xiao
 * @create 2018/5/17 15:17
 */
@Slf4j
@ThreadSafe
public class StringBufferExample {

    // 并发数
    public static int clientTotal = 5000;
    // 线程数
    public static int threadTatal = 200;

    public static StringBuffer sb = new StringBuffer();

    @NotThreadSafe
    public static void update() {
        sb.append("1");
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
                    update();
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
        log.debug("count:{}", sb.length());
    }

}
