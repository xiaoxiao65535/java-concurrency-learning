package com.xiao.concurrency.example.immutable;

import com.xiao.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 不可变对象
 *
 * @author jianjun.xiao
 * @create 2018/5/17 10:41
 */
@Slf4j
@NotThreadSafe
public class ImmutableFinalExample {

    private static final int i = 1;
    private static final String STR = "555";
    private static final Map<Integer, Integer> map = new HashMap<>();

    static {
        map.put(1, 2);
        map.put(2, 3);
        map.put(4, 5);
        map.put(6, 7);
    }

    public static void main(String[] args) {
        map.put(1, 3);
        // 线程安全问题
        log.info("{}", map.get(1));
    }

    public void set(final int a) {
        // a = 1;
    }

}
