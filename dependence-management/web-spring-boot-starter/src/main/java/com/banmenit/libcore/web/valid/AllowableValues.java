package com.banmenit.libcore.web.valid;

import org.springframework.util.StringUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 枚举校验, 合法值以 , 分割
 *
 * @author fangm
 * @date 2023/1/11
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AllowableValues.Validator.class)
public @interface AllowableValues {

    String message() default "value is invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String values() default "";

    class Validator implements ConstraintValidator<AllowableValues, Object> {

        private String values;

        @Override
        public void initialize(AllowableValues allowableValues) {
            values = allowableValues.values();
        }

        @Override
        public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
            if (value == null) {
                return true;
            }

            if (StringUtils.isEmpty(values)) {
                return true;
            }

            String[] allowValues = values.split(",");
            for (String allowValue : allowValues) {
                if (allowValue.equals(value.toString())) {
                    return true;
                }
            }
            return false;
        }

    }
}
