package com.banmenit.libcore.common.enums;


import com.banmenit.libcore.common.api.IErrorCode;

/**
 * @Description TODO
 * @Author lizrd
 * @Date 2022/11/28 11:05
 */
public enum GlobalExceptionCodeEnum implements IErrorCode {
    /**
     * 参数校验失败
     */
    PARAM_ILLEGAL_EXCEPTION(1001, "参数校验失败！"),

    ;

    private final Integer code;

    private final String message;


    GlobalExceptionCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }


    @Override
    public String getMessage() {
        return message;
    }

}
