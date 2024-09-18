package com.banmenit.libcore.web.annotation;


import com.banmenit.libcore.web.config.JwtInterceptorConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 在启动类或者配置类上加上注解标识需要解析jwt信息的功能,因为放在common包里,
 * 避免只需要引入common其他功能但是不需要jwt解析功能
 *
 * @author tangsq
 */
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({JwtInterceptorConfig.class})
public @interface EnableJwtInfoAnnotation {
}
