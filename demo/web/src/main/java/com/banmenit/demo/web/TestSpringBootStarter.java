package com.banmenit.demo.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Descretion
 * @Author yi.yang
 * @Date 2024/10/8 17:05
 */
@SpringBootApplication(scanBasePackages = "com.banmenit")
@EnableAspectJAutoProxy
public class TestSpringBootStarter {
    public static void main(String[] args) {
        SpringApplication.run(TestSpringBootStarter.class, args);
    }
}
