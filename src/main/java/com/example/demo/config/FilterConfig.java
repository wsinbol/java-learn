package com.example.demo.config;

import com.example.demo.filter.CrosFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.annotation.Resource;

@Configuration
public class FilterConfig {

    @Resource
    private CrosFilter crosFilter;

    @Bean
    public FilterRegistrationBean crosFilterConfig(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(crosFilter);
        registration.addUrlPatterns("/**");
        registration.setName("crosFilter");
        // 设置优先级
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }
}
