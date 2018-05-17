package com.xiao.concurrency.example.immutable;

import com.xiao.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Collections.unmodifiableXXX 处理之后对象的值不能修改
 *
 * @author jianjun.xiao
 * @create 2018/5/17 10:56
 */
@Slf4j
@ThreadSafe
public class ImmutableCollectionsExample {

    private static Map<Integer, Integer> map = new HashMap<>();

    static {
        map.put(1, 2);
        map.put(1, 4);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(1, 3);
        log.info("{}", map.get(1));
    }

}
