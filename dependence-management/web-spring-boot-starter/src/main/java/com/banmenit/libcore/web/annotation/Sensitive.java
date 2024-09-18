package com.banmenit.libcore.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于controller层或者service层的方法上面，判断返回数据是否需要脱敏相关处理
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Sensitive {

    /**
     * 需要处理的Bean对象数组,不为空时，只处理此范围Bean中的数据
     *
     * @return
     */
    Class<?>[] targetClass() default {};

    /**
     * 需要忽略的Bean对象数组
     */
    Class<?>[] ignoreClass() default {};

}