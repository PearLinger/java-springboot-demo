package com.banmenit.libcore.common.utils;


import com.banmenit.libcore.common.exception.ArgumentsIllegalException;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description TODO
 * @Author lizrd
 * @Date 2022/11/28 10:41
 */
public class ParameterVerifyUtils {


    public static void verifyParameter(Object object, String argName) {
        if (isEmpty(object)) {
            throw ArgumentsIllegalException.buildException("PARAMETER_ERROR", MessageFormat.format("参数【{0}】校验为空！", argName));
        }
    }


    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof String) {
            return "".equals(object.toString().trim());
        } else if (object instanceof List) {
            return ((List) object).size() == 0;
        } else if (object instanceof Map) {
            return ((Map) object).size() == 0;
        } else if (object instanceof Set) {
            return ((Set) object).size() == 0;
        } else if (object instanceof Object[]) {
            return ((Object[]) object).length == 0;
        } else if (object instanceof int[]) {
            return ((int[]) object).length == 0;
        } else if (object instanceof long[]) {
            return ((long[]) object).length == 0;
        }
        return false;
    }

}
