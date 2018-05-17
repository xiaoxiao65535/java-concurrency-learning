package com.xiao.concurrency.web.filter;

import com.xiao.concurrency.example.threadLocal.RequestThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author jianjun.xiao
 * @create 2018/5/17 11:09
 */
@Slf4j
public class HttpFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("\nHttpFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info("HttpFilter doFilter 线程id： {} ，请求： {}", Thread.currentThread().getId(), request.getRequestURI());
        RequestThreadLocal.set(request);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("HttpFilter destroy 线程id： {} ，请求： {}", Thread.currentThread().getId(), RequestThreadLocal.get().getRequestURI());
    }
}
