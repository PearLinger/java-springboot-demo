package com.banmenit.libcore.web.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.time.ZoneOffset;
import java.util.TimeZone;

/**
 * 时区时间转换
 *
 * @Author: yaok
 * @CreateTime: 2023-03-01  17:38
 * @Version: 1.0
 */
public class TimeZoneUtils {
    private static final String TIME_ZONE = "utcOffset"; // 前端时区

    /**
     * 按header时区转换成对应的时间(一般针对前端时间进行转换)
     *
     * @param date
     * @return: {@link}
     * @throws:
     */
    public static Long changeTimeOfUTC(Long date) {
        if (ServletUtils.getRequest() == null) {
            return date;
        }
        String timeZone = ServletUtils.getRequest().getHeader(TIME_ZONE);
        if (!StringUtils.isEmpty(timeZone) && date != null) {
            DateTime dateTime = new DateTime(date, TimeZone.getTimeZone(ZoneOffset.UTC));
            dateTime = DateUtil.offsetHour(dateTime, Integer.valueOf(timeZone));
            return dateTime.getTime();
        }
        return date;
    }

    /**
     * 按header时区转换成对应的时间(一般针对后端时间响应进行转换)
     *
     * @param date
     * @return: {@link}
     * @throws:
     */
    public static Long changeTimeToHeader(Long date) {
        if (ServletUtils.getRequest() == null) {
            return date;
        }
        String timeZone = ServletUtils.getRequest().getHeader(TIME_ZONE);
        if (!StringUtils.isEmpty(timeZone) && date != null) {
            DateTime dateTime = new DateTime(date, TimeZone.getTimeZone(ZoneOffset.UTC));
            dateTime = DateUtil.offsetHour(dateTime, -Integer.valueOf(timeZone));
            return dateTime.getTime();
        }
        return date;
    }
}
