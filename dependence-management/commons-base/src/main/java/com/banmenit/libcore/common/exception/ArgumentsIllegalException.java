package com.banmenit.libcore.common.exception;

import com.banmenit.libcore.common.api.IErrorCode;
import com.banmenit.libcore.common.api.ResultCode;

/**
 * 参数异常
 *
 * @author: yangyi
 * @date: 2022年11月18日17:53:14
 **/

public class ArgumentsIllegalException extends ApiException {


    private ArgumentsIllegalException(IErrorCode errorCode) {
        super(errorCode);
    }

    /**
     * 参数异常固定为400
     *
     * @param status
     * @param message
     */
    private ArgumentsIllegalException(String status, String message) {
        super(ResultCode.VALIDATE_FAILED.getCode(), ResultCode.VALIDATE_FAILED.getCode(), status, message);
    }

    public static ArgumentsIllegalException buildException(String status, String message) {
        return new ArgumentsIllegalException(status, message);
    }

    public static ArgumentsIllegalException buildException(IErrorCode resultCode) {
        return new ArgumentsIllegalException(resultCode);
    }

}
