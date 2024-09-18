package com.banmenit.libcore.common.enums;

public enum TaskStatusEnum {
    /**
     * 进行中
     */
    ING(0, "进行中"),

    /**
     * 已完成
     */
    SUCCESS(1, "已完成"),

    /**
     * 全部失败
     */
    ALL_FAIL(2, "全部失败"),

    /**
     * 部分失败
     */
    PART_OF_THE_FAILURE(3, "部分失败"),
    ;

    private final int code;

    private final String value;


    TaskStatusEnum(int code,String value) {
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
