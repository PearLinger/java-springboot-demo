package com.banmenit.libcore.common.api;

/**
 * 常用API返回对象接口
 *
 * @author tangsq
 */
public interface IErrorCode {
    /**
     * 返回码
     *
     * @return {@link Integer}
     */
    Integer getCode();

    /**
     * 返回信息
     *
     * @return {@link String}
     */
    String getMessage();
}