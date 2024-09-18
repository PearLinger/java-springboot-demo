package com.banmenit.libcore.web.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: chenlj
 */
@Component
@Data
public class SecureConfig {
    /**
     * 脱敏AES默认key
     */
    @Value("${sensitive.aes.key:3ae1757c9e7828c6c9064a7f0dd0467e}")
    private String sensitiveAesKey;

}
