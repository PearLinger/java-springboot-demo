package com.banmenit.libcore.common.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 供Feign统一返回使用以及controller错误返回信息包装
 *
 * @author tangsq
 * @date 2023/01/16
 */
@Slf4j
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class R<T> {

    /**
     * 信息
     */
    @JsonSerialize(using = GlobalizationSerializer.class)
    private String msg;

    /**
     * 码
     */
    private Integer code;

    /**
     * 错误描述，一般为code对应枚举值toString
     */
    private String status;

    /**
     * 数据
     */
    private T data;

    protected R() {
    }

    /**
     * openfeign 使用
     *
     * @param data
     * @return {@link R}<{@link T}>
     */
    public static <T> R<T> success(T data) {
        R<T> r = new R<>();
        r.setData(data);
        r.setCode(ResultCode.SUCCESS.getCode());
        return r;
    }


    /**
     * 构造失败返回对象
     *
     * @param code
     * @param msg
     * @return {@link R}<{@link ?}>
     */
    public static R<?> fail(Integer code, String msg, String status) {
        R<?> r = new R<>();
        r.setCode(code);
        r.setMsg(msg);
        r.setStatus(status);
        r.setData(null);
        return r;
    }

    /**
     * 构造失败返回对象
     *
     * @param iErrorCode
     * @return {@link R}<{@link ?}>
     */
    public static R<?> fail(IErrorCode iErrorCode) {
        R<?> r = new R<>();
        r.setCode(iErrorCode.getCode());
        r.setMsg(iErrorCode.getMessage());
        r.setData(null);
        return r;
    }

    /**
     * feign是否正确返回
     *
     * @return {@link Boolean}
     */
    @JsonIgnore
    public Boolean isSuccess() {
        return ResultCode.SUCCESS.getCode().equals(getCode());
    }
}
