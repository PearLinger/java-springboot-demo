package com.banmenit.libcore.common.exception;

import lombok.Getter;

/**
 * @author tangsq
 */
@Getter
public abstract class FullResultMsgBuilder<T> {

    private Integer httpStatus;

    private Integer code;

    private String status;

    private String message;

    public FullResultMsgBuilder<T> httpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public FullResultMsgBuilder<T> code(Integer code) {
        this.code = code;
        return this;
    }

    public FullResultMsgBuilder<T> status(String status) {
        this.status = status;
        return this;
    }

    public FullResultMsgBuilder<T> message(String message) {
        this.message = message;
        return this;
    }

    /**
     * 构造出返回对象
     *
     * @return {@link T}
     */
    abstract T build();
}
