package com.banmenit.libcore.web.utils;

import cn.hutool.core.util.StrUtil;
import com.banmenit.libcore.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Author: chenlj
 */
@Slf4j
public class SensitiveInfoUtil {
    private static final int PHONE_MIX_LENGTH = 5;
    private static final int PHONE_SENSITIVE_LENGTH = 4;

    public static String phone(String value) {
        if (StrUtil.isEmpty(value)) {
            return value;
        }
        try {
            value = value.trim();
            int length = value.length();
            if (length <= PHONE_MIX_LENGTH) {
                return value;
            }
            int index = 0;
            if (length > 11) {
                index = length - 8;
            } else {
                index = (length - PHONE_SENSITIVE_LENGTH) / 2;
            }
            value = value.substring(0, index) + "****" + value.substring(index + 4);
        } catch (Exception e) {
            log.error("手机号脱敏失败 {}", value, e);
            throw BizException.buildException("PHONE_DESENSITIZATION", "手机号脱敏失败");
        }
        return value;
    }


    public static String idCard(String value) {
        //todo 暂无需求，有需求在此添加规则
        return value;
    }
}
