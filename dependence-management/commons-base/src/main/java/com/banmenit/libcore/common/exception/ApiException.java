package com.banmenit.libcore.common.exception;

import com.banmenit.libcore.common.api.IErrorCode;
import com.banmenit.libcore.common.api.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义API异常
 *
 * @author tangsq
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApiException extends RuntimeException {

    private Integer httpStatus;

    private Integer code;

    private String status;

    private String message;


    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.httpStatus = ResultCode.FAILED.getCode();
        this.code = errorCode.getCode();
        this.status = errorCode.toString();
        this.message = errorCode.getMessage();
    }

    public ApiException(Integer httpStatus, Integer code, String status, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.status = status;
        this.message = message;
    }

    /**
     * 默认使用httpStatus = 500以及code = 500
     *
     * @param status
     * @param message
     */
    public ApiException(String status, String message) {
        this.httpStatus = ResultCode.FAILED.getCode();
        this.code = ResultCode.FAILED.getCode();
        this.status = status;
        this.message = message;
    }
}
