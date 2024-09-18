package com.banmenit.libcore.common.enums;

/**
 * @author yangyi
 * @version v 1.0
 * @date 2022/11/18 14:10
 */
public enum DeleteFlagEnum {
    /**
     * 未删除
     */
    NOT_DELETED(0, "未删除"),

    /**
     * 已删除
     */
    DELETED(1, "已删除"),
    ;

    private final int code;

    private final String value;


    DeleteFlagEnum(int code,String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }


}
