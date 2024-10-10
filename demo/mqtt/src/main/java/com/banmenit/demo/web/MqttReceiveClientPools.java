package com.banmenit.demo.web;

import cn.hutool.core.util.ObjectUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 功能说明： 创建MQTT连接池使用
 *
 * @ com.ubtechinc.pools
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2020/7/23@13:43
 * <p>
 * Copyright (C)2012-@2020 深圳优必选科技 All rights reserved.
 */
@Slf4j
public class MqttReceiveClientPools {

    /**
     * 创建连接的线程池实现；
     */
    public static ThreadPoolExecutor executorPools = new ThreadPoolExecutor(PropertyUtils.corePoolSize, PropertyUtils.maxPoolSize,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    /**
     * 创建的线程池 包含有8倍于client的线程数；
     */
    public static List<ThreadPoolExecutor> poolList = new ArrayList<>();
    /**
     * MQTT Client 连接池
     */
    private static LinkedBlockingQueue<MqttAsyncClient> clients = new LinkedBlockingQueue<>();

    /**
     * 创建MQTT Client客户端，创建core个客户端
     */
    public static void startCreateMqttClientReceiveQueue() {
        log.info("[client pool] Create Mqtt Client Pools , size : {}", PropertyUtils.corePoolSize);
        int clientNum = PropertyUtils.corePoolSize;
        for (int i = 0; i < 1; i++) {
            String suffix = UUID.randomUUID().toString();
            executorPools.execute(() -> {
                try {
                    MqttReceiveClientPools.createMqttReceiveClient(PropertyUtils.clientPrefix + "_" + suffix);
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            });
        }
        for(int i=0;i < PropertyUtils.corePoolSize;i++){
        	/**
        	 * ！_！  此处设设置多个线程池处理
        	 */
        	ThreadPoolExecutor executorPoolsTask = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        	poolList.add(executorPoolsTask);
        }
        log.info("[send pool] Create Mqtt Client Pools Size : {}", poolList.size());
    }

    /**
     * 将连接池中的客户端，主动调用断开连接
     **/
    public static void disconnectClientPools() {
        log.info("[Close client] close the client, on time :{}", new Date().toString());
        while (clients.size() > 0) {
            MqttAsyncClient client = clients.poll();
            if (!ObjectUtil.isEmpty(client) && client.isConnected()) {
                try {
                    // Call the method of disconnect(), for disconnect from MQTT Server;
                    client.disconnect();
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 创建MQTT客户端， 通过ThreadPoolExecutor 进行处理；
     * (1) IM Server订阅qos 0， 回执qos 0， 发布qos 1
     * -- 订阅相应主题，并接收数据， 通过Qos = 0 的方式接收数据；
     *
     * @param clientId
     * @return
     * @throws MqttException
     */
    private static void createMqttReceiveClient(String clientId) throws MqttException {
        MqttAsyncClient client = new MqttAsyncClient(PropertyUtils.url, clientId, null);
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(false);
        connOpts.setUserName(PropertyUtils.mqttUserName);
        connOpts.setPassword(PropertyUtils.mqttPassword.toCharArray());
        connOpts.setMaxInflight(Constant.MQTT_MAX_INFLIGHT);
        connOpts.setAutomaticReconnect(true);
        client.connect(connOpts).waitForCompletion();
        log.info("【客户端连接】client Id : {} is connected", clientId);
        client.subscribe(Constant.IM_SHARE_TOPIC, Constant.IM_MQTT_QOS_2);
        // Set the callback function, include reconnect subscribe topic and message handler;
        setMqttCallback(client);
        clients.add(client);
    }

    /**
     * 用于client端设置Callback回调函数
     *
     * @param client
     */
    private static void setMqttCallback(MqttAsyncClient client) {
        client.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {
                try {
                    client.subscribe(Constant.IM_SHARE_TOPIC, Constant.IM_MQTT_QOS_2);
                } catch (MqttException e) {
                    log.info("reconnect complete and subscribe exception :{}", e.getMessage());
                }
            }

            @SneakyThrows
            @Override
            public void connectionLost(Throwable cause) {
                log.info("Connection is lost, cause is :{}", cause.getCause());
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }

}
