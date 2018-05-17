package com.xiao.concurrency.web.controller;

import com.xiao.concurrency.example.threadLocal.RequestThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 线程封闭性测试
 *
 * @author jianjun.xiao
 * @create 2018/5/17 14:24
 */
@RestController
@Slf4j
public class ThreadLocalController {

    @GetMapping("/thread/get")
    public void get() {
        log.info("线程id： {} ，请求： {}",
                Thread.currentThread().getId(),
                RequestThreadLocal.get().getRequestURI());
    }

}
