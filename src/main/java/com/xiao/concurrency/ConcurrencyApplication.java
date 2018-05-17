package com.xiao.concurrency;

import com.xiao.concurrency.web.filter.HttpFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈〉<br>
 *
 * @author jianjun.xiao
 * @create 2018/5/15 14:45
 * @since 1.0.0
 */
@SpringBootApplication
public class ConcurrencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConcurrencyApplication.class, args);
    }

}
