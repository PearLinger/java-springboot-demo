package com.banmenit.libcore.common.utils;

import java.security.SecureRandom;

/**
 * @author yangyi
 * @version v 1.0
 * @date 2022/11/15 17:36
 */
public class RandomUtil {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String CHARS = "abcdefghijklmnopqrstuvwxyz";
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(randomNumber(3));
            System.out.println(randomString(3));
        }
    }

    public static String randomNumber(int length) {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < length; i++) {
            ret.append(RANDOM.nextInt(10));
        }
        return ret.toString();
    }

    public static String randomString(int length) {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < length; i++) {
            ret.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
        }
        return ret.toString();
    }

    /**
     * 获取指定长度的字符串 字母大小写加数字
     * @param length 指定长度
     * @return 随机字符串
     */
    public static String getRandomStringArray(int length){
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(chars[RANDOM.nextInt(chars.length)]);
        }
        return sb.toString();
    }
}
