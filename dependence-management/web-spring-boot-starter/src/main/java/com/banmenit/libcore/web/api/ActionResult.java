package com.banmenit.libcore.web.api;

import com.banmenit.libcore.common.api.R;
import com.banmenit.libcore.common.api.ResultCode;
import com.banmenit.libcore.common.utils.JsonUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 返回工具类
 *
 * @author tangsq
 * @date 2023/01/16
 */
@Slf4j
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActionResult<T> {

    /**
     * 获取当前response
     */
    private static HttpServletResponse getResponse() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        return requestAttributes.getResponse();
    }


    /**
     * 正常返回数据
     *
     * @param httpStatus 状态码
     * @param data       数据
     * @return {@link T}
     */
    private static <T> T responseJson(HttpStatus httpStatus, T data) {
        HttpServletResponse response = getResponse();
        response.setStatus(httpStatus.value());
        response.setContentType("application/json;charset=utf-8");
        try {
            response.getWriter().print(JsonUtils.toJson(data));
        } catch (IOException e) {
            log.error("An error occurred with the returned data:", e);
        }
        return null;
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> T ok(T data) {
        return responseJson(HttpStatus.OK, data);
    }


    /**
     * 自定义错误码返回,非400,500调用
     *
     * @param httpStatus 状态码
     * @param code       错误码
     * @param message    错误信息
     * @param status     错误描述
     * @return {@link T}
     */
    public static <T> T responseFail(Integer httpStatus, Integer code, String message, String status) {
        HttpServletResponse response = getResponse();
        response.setStatus(httpStatus);
        response.setContentType("application/json;charset=utf-8");
        try {
            R<?> r = R.fail(code, message, status);
            response.getWriter().print(JsonUtils.toJson(r));
        } catch (IOException e) {
            log.error("An error occurred with the returned data:", e);
        }
        return null;
    }

    /**
     * 500返回
     * httpStatus = 500, code = 500
     *
     * @param message 错误信息中文
     * @param status  错误信息英文
     * @return {@link T}
     */
    public static <T> T responseFail500(String message, String status) {
        return responseFail(ResultCode.FAILED.getCode(), ResultCode.FAILED.getCode(), message, status);
    }


    /**
     * 通用失败返回结果
     * httpStatus = 500, code = 500, message= 操作失败, status = FAILED
     */
    public static <T> T fail() {
        return responseFail500(ResultCode.FAILED.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.name());
    }

    /**
     * 400返回
     * httpStatus = 400, code = 400
     *
     * @param message 错误信息中文
     * @param status  错误信息英文
     * @return {@link T}
     */
    public static <T> T responseFail400(String message, String status) {
        return responseFail(ResultCode.VALIDATE_FAILED.getCode(), ResultCode.VALIDATE_FAILED.getCode(), message, status);
    }

    /**
     * 客户端错误返回结果
     * httpStatus = 400, code = 400, message= 客户端错误, status = VALIDATE_FAILED
     */
    public static <T> T validateFailed() {
        return responseFail400(ResultCode.VALIDATE_FAILED.getMessage(), ResultCode.VALIDATE_FAILED.toString());
    }

    /**
     * 未登录返回结果
     * httpStatus = 401, code = 401, message= 暂未登录或token已经过期, status = UNAUTHORIZED
     */
    public static <T> T unauthorized() {
        ResultCode resultCode = ResultCode.UNAUTHORIZED;
        return responseFail(resultCode.getCode(), resultCode.getCode(), resultCode.getMessage(), resultCode.toString());
    }

    /**
     * 未授权返回结果
     * httpStatus = 403, code = 403, message= 没有相关权限, status = FORBIDDEN
     */
    public static <T> T forbidden() {
        ResultCode resultCode = ResultCode.FORBIDDEN;
        return responseFail(resultCode.getCode(), resultCode.getCode(), resultCode.getMessage(), resultCode.toString());
    }
}
