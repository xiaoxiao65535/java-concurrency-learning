package com.xiao.concurrency.example.publish;

import com.xiao.concurrency.annotations.NotRecommend;
import com.xiao.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 〈对象逃逸〉<br>
 *
 * @author jianjun.xiao
 * @create 2018/5/16 15:14
 * @since 1.0.0
 */
@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape() {
        new InnerClass();
    }

    private class InnerClass {

        public InnerClass() {
            // 在Escape对象还未初始化完成之前，得到了Escape的引用，不安全
            log.info("{}", Escape.this.thisCanBeEscape);
        }

    }

    public static void main(String[] args) {
        new Escape();
    }

}

