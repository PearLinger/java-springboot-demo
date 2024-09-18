package com.banmenit.libcore.common.exception;

import com.banmenit.libcore.common.api.IErrorCode;
import com.banmenit.libcore.common.api.ResultCode;

/**
 * 业务异常
 *
 * @author: 杨义
 * @date: 2022年11月24日14:03:33
 **/
public class BizException extends ApiException {

    private BizException(IErrorCode errorCode) {
        super(ResultCode.VALIDATE_FAILED.getCode(), errorCode.getCode(), errorCode.toString(), errorCode.getMessage());
    }

    /**
     * 参数异常固定为400
     *
     * @param status
     * @param message
     */
    private BizException(String status, String message) {
        super(ResultCode.VALIDATE_FAILED.getCode(), ResultCode.VALIDATE_FAILED.getCode(), status, message);
    }

    /**
     * 自定义如：401,403等
     *
     * @param httpStatus
     * @param code
     * @param status
     * @param message
     */
    private BizException(Integer httpStatus, Integer code, String status, String message) {
        super(httpStatus, code, status, message);
    }

    public static BizException buildException(IErrorCode resultCode) {
        return new BizException(resultCode);
    }

    public static BizException buildException(String status, String message) {
        return new BizException(status, message);
    }

    public static BizException buildException(Integer httpStatus, Integer code, String status, String message) {
        return new BizException(httpStatus, code, status, message);
    }
}
