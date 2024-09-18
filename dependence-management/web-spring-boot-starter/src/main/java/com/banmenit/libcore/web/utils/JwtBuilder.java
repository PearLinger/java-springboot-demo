package com.banmenit.libcore.web.utils;

import cn.hutool.core.collection.CollUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.banmenit.libcore.common.utils.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;
import java.util.UUID;

/**
 * jwt工具类
 *
 * @author tangsq
 * @date 2023/03/13
 */
public class JwtBuilder {

    /**
     * 默认的头不会校验
     */
    private static final String DEFAULT_HEADER = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.";

    /**
     * 默认的签名
     */
    private static final String DEFAULT_SIGNATURE = ".XiZxdMANszoEorPnSwXO7asXplbKKX-oYoHKqUGUZqE";


    /**
     * 租户
     */
    private String tenant;

    /**
     * 用户id
     */
    private String sub;

    /**
     * 姓名
     */
    private String name;

    /**
     * 是否是服务
     */
    private Boolean server;


    /**
     * 区分用户类型，app那边要知道是员工，还是客户
     */
    private Integer flag;

    public String getTenant() {
        return tenant;
    }

    public String getSub() {
        return sub;
    }

    public String getName() {
        return name;
    }

    public Boolean getServer() {
        return server;
    }

    public Integer getFlag() {
        return flag;
    }

    public JwtBuilder tenant(String tenant) {
        this.tenant = tenant;
        return this;
    }

    public JwtBuilder sub(String sub) {
        this.sub = sub;
        return this;
    }

    public JwtBuilder name(String name) {
        this.name = name;
        return this;
    }

    public JwtBuilder server(Boolean server) {
        this.server = server;
        return this;
    }

    public JwtBuilder flag(Integer flag) {
        this.flag = flag;
        return this;
    }

    /**
     * 构造一个jwt, 载荷内容, 目前包括用户名, 当前人（用户id、appid）, 是否是服务, 用户名, flag
     *
     * @return {@link String}
     */
    public String build() {
        String jti = UUID.randomUUID().toString();
        Map<String,Object> map = JsonUtils.fromJson(JsonUtils.toJsonNonNull(this),new TypeReference<Map<String,Object>>() {
        });
        JWTCreator.Builder builder = JWT.create().withJWTId(jti);
        map.forEach((key,value) -> {
            if (value instanceof String) {
                builder.withClaim(key,(String)value);
            }
            if (value instanceof Boolean) {
                builder.withClaim(key,(Boolean)value);
            }
            if (value instanceof Integer) {
                builder.withClaim(key,(Integer)value);
            }
        });
        String token = builder.sign(Algorithm.HMAC256("banmenit"));
        return token;
    }

    public static void main(String[] args) {
        System.out.println(JwtBuilder.builder().tenant("sdds").name("sds").flag(1).server(true).sub("sdsdds").build());
    }
    /**
     * 返回builder
     *
     * @return {@link JwtBuilder}
     */
    public static JwtBuilder builder() {
        return new JwtBuilder();
    }
}

