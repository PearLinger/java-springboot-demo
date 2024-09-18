package com.banmenit.libcore.common.api;

/**
 * 常用API返回对象,目前code和httpstatus绑定不能随意添加
 *
 * @author tangsq
 */
public enum ResultCode implements IErrorCode {
    /**
     * 200
     */
    SUCCESS(200, "操作成功"),
    /**
     * 500
     */
    FAILED(500, "操作失败"),
    /**
     * 400
     */
    VALIDATE_FAILED(400, "客户端错误"),
    /**
     * 401
     */
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    /**
     * 403
     */
    FORBIDDEN(403, "没有相关权限"),
    /**
     * 404
     */
    NOT_FOUND(404, "没有相关资源"),

    METHOD_NOT_ALLOW(405, "方法不允许");
    /**
     * 错误吗
     */
    private final Integer code;
    /**
     * 错误信息
     */
    private final String message;


    ResultCode(Integer code, String message) {
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
