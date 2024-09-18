package com.banmenit.libcore.common.enums;

/**
 * 用户标签枚举
 * @author wangqiang
 * @date
 */
public enum UserTagEnum {

    MAIN_ORG("MAIN_ORG", "主组织"),
    MAIN_POS("MAIN_POS", "主岗位"),
    USER_BIND_POS("USER_BIND_POS", "用户绑定岗位"),

    ;

    private String key;
    private String description;

    UserTagEnum(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }
}
