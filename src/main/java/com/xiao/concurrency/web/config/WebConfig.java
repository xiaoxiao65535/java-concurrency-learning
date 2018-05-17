package com.xiao.concurrency.web.config;

import com.xiao.concurrency.web.filter.HttpFilter;
import com.xiao.concurrency.web.interceptor.HttpInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * web配置
 *
 * @author jianjun.xiao
 * @create 2018/5/17 14:34
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * 配置过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean register = new FilterRegistrationBean();
        register.setFilter(new HttpFilter());
        register.addUrlPatterns("/thread/*");
        return register;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpInterceptor())
                .addPathPatterns("/thread/*");
    }
}
