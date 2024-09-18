package com.banmenit.libcore.common.utils;



import com.banmenit.libcore.common.dto.GlobalRequestDTO;

import java.util.Optional;

/**
 * 请求参数传递辅助类
 *
 * @author yangyi
 */
public class AuthorizationDataUtil {
    /**
     * 请求参数存取
     */
    private static final ThreadLocal<GlobalRequestDTO> REQUEST_DATA = new ThreadLocal<>();

    /**
     * 设置请求参数
     */
    public static void setRequestData(GlobalRequestDTO dto) {
        REQUEST_DATA.set(dto);
    }

    /**
     * 获取请求参数
     *
     * @return 请求参数 MAP 对象
     */
    public static void removeRequestData() {
        REQUEST_DATA.remove();
    }

    /**
     * 获取请求参数
     *
     * @return 请求参数 MAP 对象
     */
    public static GlobalRequestDTO getRequestData() {
        GlobalRequestDTO globalRequest = REQUEST_DATA.get();
        return Optional.ofNullable(globalRequest).orElse(new GlobalRequestDTO());
    }
}
