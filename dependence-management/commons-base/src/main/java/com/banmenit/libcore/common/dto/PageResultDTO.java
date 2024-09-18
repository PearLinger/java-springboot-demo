package com.banmenit.libcore.common.dto;

import java.util.List;

/**
 * @author yangyi
 * @version v 1.0
 * @date 2022/11/15 14:35
 */
public class PageResultDTO<T> {

    /**
     * 当前页
     */
    private long pageIndex;
    /**
     * 每页记录数
     */
    private long pageSize;
    /**
     * 总记录数
     */
    private long totalCount;
    /**
     * 总页数
     */
    private long totalPage;
    /**
     * 数据
     */
    private List<T> data;


    public PageResultDTO() {
    }

    public long getPageIndex() {
        return this.pageIndex;
    }

    public long getPageSize() {
        return this.pageSize;
    }

    public long getTotalCount() {
        return this.totalCount;
    }

    public long getTotalPage() {
        return this.totalPage;
    }

    public List<T> getData() {
        return this.data;
    }

    public void setPageIndex(long pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public static <T> PageResultDTO<T> of(long pageIndex, long pageSize, long totalCount, long totalPage, List<T> data) {
        PageResultDTO<T> dto = new PageResultDTO<>();
        dto.setPageIndex(pageIndex);
        dto.setPageSize(pageSize);
        dto.setTotalCount(totalCount);
        dto.setTotalPage(totalPage);
        dto.setData(data);
        return dto;
    }

    public static <T> PageResultDTO<T> empty(long pageIndex, long pageSize) {
        PageResultDTO<T> dto = new PageResultDTO<>();
        dto.setPageIndex(pageIndex);
        dto.setPageSize(pageSize);
        return dto;
    }
}
