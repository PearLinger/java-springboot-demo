package com.banmenit.libcore.common.constant;

/**
 * R 字段对应字符创常量
 *
 * @author tangsq
 * @date 2023/03/05
 */
public interface ResultConstant {

    /**
     * 错误信息
     */
    String MSG = "msg";

    /**
     * 错误码
     */
    String CODE = "code";

    /**
     * 错误描述，一般为code对应枚举值toString
     */
    String STATUS = "status";

    /**
     * 数据域
     */
    String DATA = "data";

}
