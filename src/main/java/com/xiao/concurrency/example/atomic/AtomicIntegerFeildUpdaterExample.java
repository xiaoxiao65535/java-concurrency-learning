package com.xiao.concurrency.example.atomic;

import com.xiao.concurrency.annotations.NotRecommend;
import com.xiao.concurrency.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 〈〉<br>
 * AtomicIntegerFieldUpdater更新原子性更新某个对象的某个字段，
 * 该字段需要使用volatile关键词修饰
 *
 * @author jianjun.xiao
 * @create 2018/5/16 9:52
 * @since 1.0.0
 */
@Slf4j
@ThreadSafe
@NotRecommend
public class AtomicIntegerFeildUpdaterExample {

    // 要修改的字段必须要使用volatile修饰
    @Getter
    private volatile int count = 100;

    private static AtomicIntegerFieldUpdater<AtomicIntegerFeildUpdaterExample> updater
            = AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFeildUpdaterExample.class, "count");

    public static void main(String[] args) {
        AtomicIntegerFeildUpdaterExample obj = new AtomicIntegerFeildUpdaterExample();

        if (updater.compareAndSet(obj, 100, 200)) {
            log.info("1 update 100 to 200 success,{}", obj.getCount());
        }

        if (updater.compareAndSet(obj, 100, 200)) {
            log.info("2 update 100 to 200 success,{}", obj.getCount());
        } else {
            log.info("2 update failed,{}", obj.getCount());
        }
    }

}
