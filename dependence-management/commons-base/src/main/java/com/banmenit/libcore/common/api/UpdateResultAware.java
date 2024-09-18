package com.banmenit.libcore.common.api;

/**
 * 用于扩展修改返回结果:比如国际化
 *
 * @author tangsq
 * @date 2022/10/21
 */
public interface UpdateResultAware {

    /**
     * 更改返回值, 更新msg信息
     *
     * @param str
     * @return {@link String}
     */
    String updateResult(String str);
}
