package com.xiao.concurrency.example.singleton;

import com.xiao.concurrency.annotations.Recommend;
import com.xiao.concurrency.annotations.ThreadSafe;

/**
 * 〈枚举实现的单例模式〉<br>
 * 最安全的
 *
 * @author jianjun.xiao
 * @create 2018/5/16 16:39
 * @since 1.0.0
 */
@ThreadSafe
@Recommend
public class SingletonEnum {

    private SingletonEnum() {

    }

    public static SingletonEnum getInstance() {
        return Singleton.SINGLETON.getInstance();
    }

    private enum Singleton {
        SINGLETON;

        private SingletonEnum instance;

        // JVM保证这个方法绝对只调用一次
        Singleton() {
            instance = new SingletonEnum();
        }

        public SingletonEnum getInstance() {
            return instance;
        }
    }

}
