package com.banmenit.libcore.web.config;

import com.banmenit.libcore.web.filter.BanmenitCorsFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * banmenit 跨域配置
 *
 * @author tangsq
 */
@ConditionalOnProperty(prefix = "cors-origins", name = "enable", havingValue = "true")
@Configuration
public class BanmenitCorsFilterConfig {
    @Bean
    public BanmenitCorsFilter banmenitCorsFilter() {
        return new BanmenitCorsFilter();
    }

    @Bean("banmenitCorsFilterBean")
    public FilterRegistrationBean requestCachingFilterRegistration(BanmenitCorsFilter banmenitCorsFilter){
        FilterRegistrationBean bean = new FilterRegistrationBean(banmenitCorsFilter);
        //保证最高优先级
        bean.setOrder(0);
        return bean;
    }
}
