package com.banmenit.libcore.common.dto;

import lombok.Data;

/**
 * @author yangyi
 * @version v 1.0
 * @date 2022/11/15 14:28
 * 分页查询
 */
@Data
public class PageDTO {
    /**
     * 每页多少条
     */
    private Integer pageSize;

    /**
     * 第多少页
     */
    private Integer pageIndex;
}
