package com.banmenit.libcore.web.config;

import com.banmenit.libcore.web.filter.TraceFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lichen
 * @since 2023-12-21 20:22:17
 **/
@Configuration
public class TraceFilterConfig {

    @Bean
    public TraceFilter traceFilter() {
        return new TraceFilter();
    }

    @Bean
    public FilterRegistrationBean traceFilterRegistrationBean(TraceFilter traceFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(traceFilter);
        registration.addUrlPatterns("/*");
        registration.setName("traceFilter");
        //设置优先级别
        registration.setOrder(1);
        return registration;
    }
}
