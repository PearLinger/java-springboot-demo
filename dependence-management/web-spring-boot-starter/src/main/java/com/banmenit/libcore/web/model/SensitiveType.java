package com.banmenit.libcore.web.model;

public enum SensitiveType {
    /**
     * 手机号
     * * 手机位数 脱敏方式(展示手机号)
     *    12-15  展示前4、5、6、7 后4
     *    11     展示前3 后4
     *    10     展示前3 后3
     *    9      展示前2 后3
     *    8      展示前2 后2
     *    7      展示前1 后2
     *    6      展示前1 后1
     *    5      不脱敏
     */
    USER_PHONE,
    /**
     * 身份证号
     */
    ID_CARD,
    NONE
}