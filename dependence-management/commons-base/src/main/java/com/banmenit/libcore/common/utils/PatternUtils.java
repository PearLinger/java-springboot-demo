package com.banmenit.libcore.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 * @Author: chenlj
 */
public class PatternUtils {

    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher num = pattern.matcher(str);
        if (!num.matches()) {
            return false;
        }
        return true;
    }
}
