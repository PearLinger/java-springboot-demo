package com.banmenit.libcore.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 配置url大小写不敏感, 经测试需要实现WebMvcConfigurer, 继承WebMvcConfigurationSupport不生效
 *
 * @author tangsq
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 接口地址忽略大小写
     *
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        AntPathMatcher matcher = new AntPathMatcher();
        matcher.setCaseSensitive(false);
        configurer.setPathMatcher(matcher);
    }
}
