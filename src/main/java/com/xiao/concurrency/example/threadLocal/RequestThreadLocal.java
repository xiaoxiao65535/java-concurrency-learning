package com.xiao.concurrency.example.threadLocal;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求线程
 *
 * @author jianjun.xiao
 * @create 2018/5/17 14:19
 */
public class RequestThreadLocal {

    private final static ThreadLocal<HttpServletRequest> REQUEST_THREAD_LOCAL = new ThreadLocal<>();

    public static void set(HttpServletRequest request) {
        REQUEST_THREAD_LOCAL.set(request);
    }

    public static HttpServletRequest get() {
        return REQUEST_THREAD_LOCAL.get();
    }

    public static void remove() {
        REQUEST_THREAD_LOCAL.remove();
    }

}
