package com.banmenit.libcore.web.valid;

import cn.hutool.http.HtmlUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author lizrd
 * @date 2023/5/25 9:41
 */
public class XssCheck implements ConstraintValidator<XssCheckValid, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        String filter = HtmlUtil.filter(value);
        return value.equals(filter);
    }
}
