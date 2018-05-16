package com.xiao.concurrency.example.publish;

import com.xiao.concurrency.annotations.NotThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 〈对象发布〉<br>
 *
 * @author jianjun.xiao
 * @create 2018/5/16 15:08
 * @since 1.0.0
 */
@Slf4j
@NotThreadSafe
public class UnsafePublish {

    @Getter
    private String[] states = {"a", "b", "c"};

    public static void main(String[] args) {
        UnsafePublish publish = new UnsafePublish();
        log.info("{}", Arrays.toString(publish.getStates()));

        // 多线程条件下该对象的私有域被直接修改
        publish.getStates()[0] = "d";
        log.info("{}", Arrays.toString(publish.getStates()));
    }

}
