package com.banmenit.libcore.common.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 转换对象
 *
 * @author Leoly Gu
 * @date 2020-12-03 11:42:17
 */
@Slf4j
public class TransUtils {

    public interface Transfer<T, S> {
        T trans(S source);
    }

    /**
     * 转换List对象
     *
     * @param sourceList 原始List对象
     * @param tClass     需要转换成的List对象中的数据对象class
     * @param <T>        需要转换的数据对象范型
     * @param <S>        原始List数据对象范型
     * @return
     */
    public static <T, S> List<T> list(Collection<S> sourceList, Class<T> tClass) {
        if (CollUtil.isEmpty(sourceList)) {
            return new ArrayList<>();
        }

        return BeanUtil.copyToList(sourceList,tClass,(CopyOptions)null);
    }

    public static <T, S> List<T> list(Collection<S> sourceList, Transfer<T, S> transfer) {
        if (CollUtil.isEmpty(sourceList)) {
            return new ArrayList<>();
        }
        return sourceList.stream().map(transfer::trans).collect(Collectors.toList());
    }

    /**
     * 转换Bean
     *
     * @param source 原始对象
     * @param tClass 目标对象class
     * @param <T>    目标对象范型
     * @param <S>    原始对象范型
     * @return
     */
    public static <T, S> T bean(S source, Class<T> tClass)  {
        if (source == null) {
            return null;
        }
        return BeanUtil.copyProperties(source,tClass);
    }

    /**
     * 转换Bean
     *
     * @param source 原始对象
     * @param <T>    目标对象范型
     * @param <S>    原始对象范型
     * @return
     */
    public static <T, S> void bean(S source, T object)  {
         BeanUtil.copyProperties(source,object);
    }
}
