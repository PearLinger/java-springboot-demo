package com.banmenit.libcore.web.api;

import com.banmenit.libcore.common.api.IErrorCode;
import lombok.Data;

/**
 * 需求：参数校验这种抛出的异常
 * 复用现有code
 * 自定义错误描述的ResultCode
 *
 * @author tangsq
 */
@Data
public class CustomMsgResultCode implements IErrorCode {

    private Integer code;

    private String message;

    public CustomMsgResultCode(IErrorCode errorCode, String customMessage) {
        this.code = errorCode.getCode();
        this.message = customMessage;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }


    @Override
    public String getMessage() {
        return this.message;
    }
}
