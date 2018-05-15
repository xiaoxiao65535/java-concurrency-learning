package com.xiao.concurrency.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识类是【线程安全】的类或者是写法
 */
@Target(ElementType.TYPE) // 注解作用的目标
@Retention(RetentionPolicy.SOURCE) // 在编译的时候忽略
public @interface ThreadSafe {

    String value() default "线程安全";

}
