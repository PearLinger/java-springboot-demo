package com.banmenit.libcore.web.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import com.banmenit.libcore.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * @Description:
 * @Author: chenlj
 */
@Slf4j
public class SecureUtils {

    /**
     * 解码
     *
     * @param value 数据
     * @param key   密钥key
     * @return
     */
    public static String decode(String value, String key) {
        if (StrUtil.isEmpty(value)) {
            return value;
        }
        if (StrUtil.isEmpty(key)) {
            throw BizException.buildException("KEY_EMPTY", "秘钥Key不能为空");
        }
        String result = value;
        try {
            AES aes = SecureUtil.aes(key.getBytes(StandardCharsets.UTF_8));
            // 解码
            result = aes.decryptStr(value);
        } catch (Exception e) {
            log.error("数据解码失败", e);
        }
        return result;
    }

    /**
     * 编码
     *
     * @param value 数据
     * @param key   密钥key
     * @return
     */
    public static String encode(String value, String key) {
        if (StrUtil.isEmpty(value)) {
            return value;
        }
        if (StrUtil.isEmpty(key)) {
            throw BizException.buildException("KEY_EMPTY", "秘钥Key不能为空");
        }
        String result = value;
        try {
            AES aes = SecureUtil.aes(key.getBytes(StandardCharsets.UTF_8));
            // 编码
            result = aes.encryptHex(value);
        } catch (Exception e) {
            log.error("数据编码失败", e);
        }
        return result;
    }
}
