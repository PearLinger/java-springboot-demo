package com.banmenit.libcore.web.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = XssCheck.class)
public @interface XssCheckValid {

    String message() default "字段内容包含xss漏洞数据！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
