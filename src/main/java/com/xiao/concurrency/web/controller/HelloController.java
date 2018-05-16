package com.xiao.concurrency.web.controller;

import com.xiao.concurrency.web.compment.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈〉<br>
 *
 * @author jianjun.xiao
 * @create 2018/5/16 14:41
 * @since 1.0.0
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping(value = "/hello")
    public String hello() {
        return helloService.toString();
    }

}
