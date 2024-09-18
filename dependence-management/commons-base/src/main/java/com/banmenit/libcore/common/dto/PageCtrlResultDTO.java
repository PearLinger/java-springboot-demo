package com.banmenit.libcore.common.dto;

import lombok.Data;

import java.util.List;

/**
 * 分页全选接口返回
 * @author yaok
 * @version v 1.0
 * @date 2022/11/15 14:35
 */
@Data
public class PageCtrlResultDTO<T> {
    /**
     * 每页记录数
     */
    private long pageSize;
    /**
     * 数据
     */
    private List<T> data;

}
