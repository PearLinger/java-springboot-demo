package com.banmenit.libcore.common.exception;

import lombok.Getter;

/**
 * 结果信息Builder
 *
 * @author tangsq
 * @date 2023/03/14
 */
@Getter
public abstract class ResultMsgBuilder<T> {

    private String status;

    private String message;


    public ResultMsgBuilder<T> status(String status) {
        this.status = status;
        return this;
    }

    public ResultMsgBuilder<T> message(String message) {
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
