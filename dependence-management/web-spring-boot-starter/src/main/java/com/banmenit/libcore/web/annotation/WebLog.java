package com.banmenit.libcore.web.annotation;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface WebLog {

    /**
     * 日志描述信息
     *
     * @return
     */
    String description() default "";
}
