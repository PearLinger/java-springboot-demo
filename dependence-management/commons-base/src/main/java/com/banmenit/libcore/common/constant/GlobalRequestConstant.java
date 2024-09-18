package com.banmenit.libcore.common.constant;

/**
 * 全局请求信息相关key常量
 *
 * @author tangsq
 * @date 2022/12/28
 */
public interface GlobalRequestConstant {

    /**
     * token key
     */
    String AUTHORIZATION = "Authorization";
    /**
     * token key
     */
    String ACCESS_TOKEN = "access_token";

    /**
     * 租户
     */
    String TENANT = "tenant";

    /**
     * 当前人（用户id、appid）、来自jwt, 不为空, 全局唯一
     */
    String SUB = "sub";

    /**
     * 是否服务, 来自jwt
     */
    String SERVER = "server";

    /**
     * 区分用户类型，app那边要知道是员工，还是客户, 来自jwt
     */
    String FLAG = "flag";

    /**
     * 用户名 name + @ + tenant + .com例如: lenon@q1.com
     */
    String NAME = "name";

    /**
     * 实例Id, 来自url参数, 可空
     */
    String INSTANCE = "instance";

    /**
     * 项目, 来自url, 可空
     */
    String PROJECT = "project";
    /**
     * 项目, 来自OpenApi Jwt, 可空
     */
    String PROJECT_MAP = "projectMap";

    /**
     * 分布式链路追踪的traceid, 可以为null, 来自请求头 TraceId
     */
    String TRACE_ID = "traceId";

    /**
     * 时区, 可以为null, 来自请求头Timezone
     */
    String TIMEZONE = "timezone";

    /**
     * 存储全局请求参数key
     */
    String JWT_INFO = "jwtInfo";

    /**
     * ip地址，来自请求头 X-Real-IP
     */
    String X_REAL_IP = "X-Real-IP";
}
