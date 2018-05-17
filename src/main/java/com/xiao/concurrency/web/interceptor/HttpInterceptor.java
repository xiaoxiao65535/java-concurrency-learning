package com.xiao.concurrency.web.interceptor;

import com.xiao.concurrency.example.threadLocal.RequestThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Http拦截器
 *
 * @author jianjun.xiao
 * @create 2018/5/17 14:42
 */
@Slf4j
public class HttpInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        log.info("HttpInterceptor preHandle 线程id： {} ，请求： {}", Thread.currentThread().getId(), RequestThreadLocal.get().getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("HttpInterceptor postHandle 线程id： {} ，请求： {}", Thread.currentThread().getId(), RequestThreadLocal.get().getRequestURI());
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("HttpInterceptor afterCompletion 线程id： {} ，请求： {}", Thread.currentThread().getId(), RequestThreadLocal.get().getRequestURI());
        // RequestThreadLocal.remove();
    }
}
