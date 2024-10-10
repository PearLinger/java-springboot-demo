package com.banmenit.demo.web;

/**
 * 功能说明：
 *
 * @ com.ubtechinc.config
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2020/7/20@19:44
 * <p>
 * Copyright (C)2012-@2020 深圳优必选科技 All rights reserved.
 */
public class Constant {

    // MQTT Constant
    public static final int MQTT_CLIENT_ALLOW = 1;

    public static final int MQTT_CLIENT_SUB_TPOIC_ACCESS = 1;

    public static final int MQTT_CLIENT_PUB_TPOIC_ACCESS = 2;

    public static final int MQTT_USERNAME_PASSWORD_LEN = 8;

    public static final String MQTT_DAY_MMDD = "MMdd";

    public static final String MQTT_COLUMN_USER_ID = "user_id";
    public static final String MQTT_COLUMN_USERNAME = "username";
    public static final String MQTT_COLUMN_CLIENTID = "clientid";

    public static final String NOTIFICATION_MAKE_FRIEND_KEY = "notification::%s-%s:%s";

    public static final String NOTIFICATION_OFFLINE_MESSAGE_KEY = "notification::offline:message:%s";

    public static final String MQTT_NODE = "mqtt:node:";

    public static final String MQTT_CLIENT = "mqtt:client:";

    public static final String MQTT_USERID_TOPIC = "mqtt:topic:";

    public static final String MQTT_USERID = "mqtt:userid:";

    public static final String MQTT_USERID_TOPIC_INFO = "mqtt:topic:info";

    public static final String MQTT_CLIENT_IP_ADDRESS = "ipaddress";

    // MQTT Client Const
    public static final String MQTT_TRANS_CLIENT_PERFIX_STR = "trans@";
    
    public static final String MQTT_CLIENT_PERFIX_STR = "im@";

    public static final String MQTT_CLIENT_FD_PERFIX_STR = "fd@";

    public static final String MQTT_CLIENT_USER_ID_ONLINE = "1";

    // Authorization
    public static final String AUTHORIZATION_UBT_APPID = "X-UBT-AppId";

    public static final String AUTHORIZATION_UBT_DEVICE_ID = "X-UBT-DeviceId";

    public static final String USER_AUTHORIZATION = "authorization";

    public static final String MQTT_API_AUTHORIZATION = "Authorization";

    public static final int RUNNABLE_EXECUTE_DELAY_TIME = 3000;

    public static final int RUNNABLE_EXECUTE_DELAY_TIME_DOUBLE = 10000;

    public static final int RUNNABLE_EXECUTE_NO_DELAY_TIME = 0;

    // common define
    public static final String MQTT_RECEIVE_TOPIC = "mqtt_receivedTopic";

    public static final String TOPIC_IM_MSG_SEND = "im/msg/send/";

    // ACL Inforation
    public static final int MQTT_ACL_ALLOW = 1;

    public static final int MQTT_ACL_NOT_ALLOW = 0;

    public static final int MQTT_ACT_SUB_ALLOW = 1;

    public static final int MQTT_ACT_PUB_ALLOW = 2;

    public static final int MQTT_ACT_SUBPUB_ALLOW = 3;

    public static final String MQTT_ACT_SUB_TOPIC = "im/msg/receive/%s";

    public static final String MQTT_ACT_PUB_TOPIC = "im/msg/send/%s";

    public static final String MQTT_FD_ACT_SUB_TOPIC = "fd/app/msg/receive/%s";

    public static final String MQTT_FD_ACT_PUB_TOPIC = "fd/app/msg/send/%s";

    // MQTT TABLE COLUMN

    public static final String MQTT_USER_TABLE_COLUMN_USERNAME = "username";

    public static final String MQTT_ACL_TABLE_COLUMN_USERNAME = "username";

    public static final String SRC_CLAZZ_NAME = "srcClazz";

    public static final String DEST_CLAZZ_NAME = "destClazz";

    public static final String REDIS_TOPIC_TOKEN_KEY = "user/token/key";

    public static final String REDIS_TOPIC_ONLINE_STATUS = "device/online/status";

    // Contract Message
    public static final String MESSAGE_APPLY_FOR_MAKE_FRIEND = "apply for make friend with you";

    public static final String MESSAGE_DELETE_FRIENDSHIP = "delete friendship with you";

    public static final String MESSAGE_DISMISS_GROUP = "dismiss the group";

    public static final String MESSAGE_QUITE_GROUP = "quit the group";

    public static final String MESSAGE_TRANSFER_GROUP_LEADER = "transfer the group leader";

    public static final String MESSAGE_ADD_GROUP_MEMBER = "add group numbers";

    public static final String MESSAGE_REMOVE_GROUP_MEMBER = "remove group numbers";

    public static final String MESSAGE_BATCH_NOTIFY = "notify batch users";

    public static final String X_UBT_APPID = "600040010";

    public static final String X_UBT_APPKEY = "0921ba8c888b4556a1f1da3b34d89514";

    public static final String X_UBT_DEVICEID = "IM-SERVER-NODE-001";

    public static final String IM_DATA_COLLECT_CATEGORY = "im-server-message";

    public static final String IM_DATA_COLLECT_EVENTID = "message-timestamp";

    public static final String SIGN_PART_SEPARATOR = " ";

    public static final String SIGN_VERSION_NUM = "v2";

    public static final String IM_DATA_CREATE_TIME = "create_time";

    public static final String IM_DATA_RECEIVE_TIME = "receive_time";

    public static final String IM_DATA_COLLECT_MESSAGE = "collect:data:message:%s-%s";

    public static final String IM_DATA_MESSAGE__CID_TO = "message:cid:to:%s-%s-%s";

    public static final int IM_DATA_COLLECT_MESSAGE_EXPIRE_TIME = 3600;

    public static final int IM_DATA_MESSAGE__CID_TO_EXPIRE_TIME = 60;

    public static final int IM_MQTT_QOS_0 = 0;

    public static final int IM_MQTT_QOS_1 = 1;

    public static final int IM_MQTT_QOS_2 = 2;

    public static final int MQTT_CLIENT_OFFLINE = 0;

    public static final int MQTT_CLIENT_ONLINE = 1;

    public static final int THREADPOOL_CORE_POOL_SIZE = 10;

    public static final int THREADPOOL_MAXI_NUM_POOL_SIZE = 10;

    public static final int THREADPOOL_OPTIMIZATION_CORE_POOL_SIZE = 8;

    public static final int THREADPOOL_OPTIMIZATION_MAXI_NUM_POOL_SIZE = 12;

    public static final long THREADPOOL_KEEP_ALIVE_TIME = 600;

    public static final String IM_SHARE_TOPIC = "/testtopic1/#";

    public static final int MQTT_MAX_INFLIGHT = 1000;

    /**
     * 处理状态(0. 未处理1.已处理 2.忽略 3.过期),
     */
    public static final int IM_MESSAGE_STATUS_TODO = 0;
    public static final int IM_MESSAGE_STATUS_DONE = 1;
    public static final int IM_MESSAGE_STATUS_IGNORE = 2;
    public static final int IM_MESSAGE_STATUS_TIME_OUT = 3;

    /**
     * 消息类型(0.私聊 1.群聊 2.通知")
     */
    public static final int IM_MESSAGE_TYPE_PRIVATE = 0;
    public static final int IM_MESSAGE_TYPE_GROUP = 1;
    public static final int IM_MESSAGE_TYPE_NOTICE = 2;

    /**
     * 用户冻结状态(0.未冻结 1.冻结")
     */
    public static final int IM_USER_FROZEN_STATUS_NO = 0;
    public static final int IM_USER_FROZEN_STATUS_YES = 1;

    /**
     * 强制加好友(1 表示强制加好友；0 表示常规加好友方式")
     */
    public static final int IM_USER_FRIEND_ADD_FORCE_NO = 0;
    public static final int IM_USER_FRIEND_ADD_FORCE_YES = 1;

    /**
     * 批量通知最大人数限制
     */
    public static final int IM_BATCH_NOTIFY_LIMITED_NUMBER = 500;

    /**
     * 群组中最大成员数量(默认为500)
     */
    public static final int IM_GROUP_MEMBER_LIMITED_NUMBER = 10;

    /**
     * 群组中最大成员数量（默认为500）
     */
    public static final int IM_GROUP_CREATED_LIMITED_NUMBER = 10;

    /**
     * 设备类型(1.机器  2.APP)
     */
    public static final int IM_DEVICE_TYPE_ROBOT = 1;
    /**
     * 设备类型(1.机器  2.APP)
     */
    public static final int IM_DEVICE_TYPE_APP = 2;
    
    /**
     * 官方账号user id
     */
    public static final String IM_OFFICIAL_USER_ID = "10000";

    public static final String MEESAGE_TYPE_NOTIFICATION = "NOTIFICATION";
    public static final String MEESAGE_TYPE_PRIVATE = "PRIVATE";
    public static final String MEESAGE_TYPE_GROUP = "GROUP";

    /**
     * 设置key的有效期为7天；
     */
    public static final int CACHE_KEY_EXPIRE_SECONDS = 7 * 86400;

    /**
     * 设置每个账号的最大好友上线(500)；
     */
    public static final int IM_USER_FRIEND_MAX_NUM = 10;

    /**
     * 会话信息（统计每个账号的会话Gid信息到缓存中）
     */
    public static final String USER_CONVERSATIONS_INFO = "conversations:info:%s";

    /**
     * 会话类型例如：  0-4-6 代表 私聊  用户4给用户6的消息；
     * 0 : 私聊  1: 群聊  2：通知
     */
    public static final String USER_CONVERSATIONS_TYPE_FROM_TO = "%s-%s-%s";

    /**
     * startGid:endGid:total 例如：301771581910405100:301772188000110340:10
     * total 该会话的离线消息量
     */
    public static final String USER_CONVERSATIONS_FROM_TO_GID = "%s:%s:%s";

    public static final String CLIENT_ID_KICKOFF_USERID_TYPE = "users::im:kickoff:%s_%s";

}
