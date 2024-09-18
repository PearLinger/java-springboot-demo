package com.banmenit.libcore.web.config;

import com.banmenit.libcore.common.utils.SnowflakeIdFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
@ConditionalOnProperty(value = "snowflake.enabled", havingValue = "true")
public class SnowflakeIdConfig {

    @Bean
    public SnowflakeIdFactory snowflakeIdFactory() {
        return new SnowflakeIdFactory((LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli() % 1024), 0);
    }
}
