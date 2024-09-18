package com.banmenit.libcore.web.annotation;

import com.banmenit.libcore.web.model.SensitiveType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SensitiveInfo {
    /**
     * 敏感数据类型
     */
    SensitiveType type() default SensitiveType.NONE;

    /**
     * 是否需要解码
     */
    boolean decode() default false;

    /**
     * 解码所需key（需与编码时一致）
     *
     * @return
     */
    String decodeKey() default "";

    /**
     * 是否需要编码
     */
    boolean encode() default false;

    /**
     * 编码所需key（需与解码时一致）
     *
     * @return
     */
    String encodeKey() default "";
}