package com.banmenit.demo.mqtt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

/**
 * @Descretion
 * @Author yi.yang
 * @Date 2024/10/8 17:05
 */
@SpringBootApplication
public class TestSpringBootStarter {
    public static void main(String[] args) {
        SpringApplication.run(TestSpringBootStarter.class, args);
        MqttReceiveClientPools.startCreateMqttClientReceiveQueue();
    }
}
