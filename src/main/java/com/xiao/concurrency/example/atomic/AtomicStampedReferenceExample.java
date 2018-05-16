package com.xiao.concurrency.example.atomic;

import com.xiao.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 〈〉<br>
 * 用法与其他Atomic包下一样，该处不给出示例
 *
 * @author jianjun.xiao
 * @create 2018/5/16 10:04
 * @since 1.0.0
 */
@Slf4j
@ThreadSafe
public class AtomicStampedReferenceExample {

    private static AtomicStampedReference<Integer> count
            = new AtomicStampedReference<>(0, 1);

    public static void main(String[] args) {

    }

}
