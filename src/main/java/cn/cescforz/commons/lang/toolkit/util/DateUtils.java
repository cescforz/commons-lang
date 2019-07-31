package cn.cescforz.commons.lang.toolkit.util;

import cn.cescforz.commons.lang.constant.SystemConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>©2018 Cesc. All Rights Reserved.</p>
 * <p>Description: 日期的工具类</p>
 *
 * @author cesc
 * @version v1.0
 * @date Create in 2018-12-22 20:33
 */
@Slf4j
public final class DateUtils {

    private DateUtils(){throw new AssertionError();}

    /**
     * <p>Description: 把日期按自定义格式转化为字符串</p>
     * @param pattern 日期格式
     * @param date 日期
     * @return java.lang.String
     */
    public static String dateToString(String pattern, Date date){
        if (StringUtils.isBlank(pattern)) {
            pattern = SystemConstants.PATTERN_DATETIME_24;
        }
        DateTimeFormatter dt = DateTimeFormatter.ofPattern(pattern);
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), zoneId);
        return localDateTime.format(dt);
    }

    /**
     * <p>Description: 把字符串按指定自定义格式转化为日期格式</p>
     * @param pattern 日期格式
     * @param dateStr  字符串
     * @return java.util.Date
     */
    public static Date stringToDate(String pattern,String dateStr){
        if (StringUtils.isBlank(pattern)) {
            pattern = SystemConstants.PATTERN_DATETIME_24;
        }
        DateTimeFormatter dt = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime ldt = LocalDateTime.parse(dateStr, dt);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = ldt.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * <p>Description: 获取指定日期前一天</p>
     * @param specifiedDay 指定日期
     * @return java.util.Date
     */
    public static Date getSpecifiedDayBefore(Date specifiedDay){
        Calendar c = Calendar.getInstance();
        c.setTime(specifiedDay);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day-1);
        return c.getTime();
    }


    /**
     * <p>Description: 获取指定日期前一天</p>
     * @param specifiedDay 指定日期
     * @return java.util.Date
     */
    public static Date getSpecifiedDayBefore(Date specifiedDay,int subDay){
        Calendar c = Calendar.getInstance();
        c.setTime(specifiedDay);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day-subDay);
        return c.getTime();
    }
}
