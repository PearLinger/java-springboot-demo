package com.banmenit.libcore.web.utils;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.banmenit.libcore.common.dto.PageDTO;
import com.banmenit.libcore.common.dto.PageResultDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


/**
 * 分页工具类
 *
 * @author lizrd
 */
public class PageUtils {

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_INDEX = "pageIndex";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 封装分页对象
     *
     * @param pageIndex 页码（不传有默认值1）
     * @param pageSize  页量（不传有默认值10）
     */
    public static <T> Page<T> buildPage(Integer pageIndex, Integer pageSize) {
        Page<T> page = new Page<>();
        if (pageIndex != null && pageSize != null) {
            page.setCurrent(pageIndex);
            page.setSize(pageSize);
        } else {
            if (pageSize != null && pageSize == -1) {
                page.setSize(-1);
                page.setCurrent(1);
            }else{
                page.setSize(toInt(ServletUtils.getParameter(PAGE_SIZE), 10));
                page.setCurrent(toInt(ServletUtils.getParameter(PAGE_INDEX), 1));
            }
        }
        return page;
    }

    /**
     * 转换为int<br>
     * 如果给定的值为空，或者转换失败，返回默认值<br>
     * 转换失败不会报错
     *
     * @param value        被转换的值
     * @param defaultValue 转换错误时的默认值
     * @return 结果
     */
    public static Integer toInt(Object value, Integer defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof Integer) {
            return (Integer) value;
        }
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        final String valueStr = toStr(value, null);
        if (StringUtils.isEmpty(valueStr)) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(valueStr.trim());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 转换为字符串<br>
     * 如果给定的值为null，或者转换失败，返回默认值<br>
     * 转换失败不会报错
     *
     * @param value        被转换的值
     * @param defaultValue 转换错误时的默认值
     * @return 结果
     */
    public static String toStr(Object value, String defaultValue) {
        if (null == value) {
            return defaultValue;
        }
        if (value instanceof String) {
            return (String) value;
        }
        return value.toString();
    }


    public static <T> PageResultDTO<T> get(Page<T> page) {
        PageResultDTO<T> rs = new PageResultDTO<>();
        rs.setPageIndex(page.getCurrent());
        rs.setPageSize(page.getSize());
        rs.setTotalCount(page.getTotal());
        rs.setTotalPage(page.getPages());
        rs.setData(page.getRecords());
        return rs;
    }

    public static <T> PageResultDTO<T> get(Page page, Class<T> clazz) {
        PageResultDTO<T> rs = new PageResultDTO<>();
        rs.setPageIndex(page.getCurrent());
        rs.setPageSize(page.getSize());
        rs.setTotalCount(page.getTotal());
        rs.setTotalPage(page.getPages());
        rs.setData(BeanUtil.copyToList(page.getRecords(), clazz, (CopyOptions) null));
        return rs;
    }

    public static <T> PageResultDTO<T> get(PageResultDTO page, Class<T> clazz) {
        PageResultDTO<T> rs = new PageResultDTO<>();
        rs.setPageIndex(page.getPageIndex());
        rs.setPageSize(page.getPageSize());
        rs.setTotalCount(page.getTotalCount());
        rs.setTotalPage(page.getTotalPage());
        rs.setData(BeanUtil.copyToList(page.getData(), clazz, (CopyOptions) null));
        return rs;
    }


    public static Page buildPage(PageDTO pageDto) {
        return buildPage(pageDto.getPageIndex(), pageDto.getPageSize());
    }

}
