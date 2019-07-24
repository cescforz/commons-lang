package cn.cescforz.commons.lang.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>©2019 Cesc. All Rights Reserved.</p>
 * <p>Description: 格式枚举类</p>
 *
 * @author cesc
 * @version 1.0
 * @date Create in 2019-07-24 10:11
 */
@Getter
@AllArgsConstructor
public enum  FormatEnum {

    /** 时间格式 */
    PATTERN_DEFAULT("yyyy-MM-dd"),
    PATTERN_DATETIME_24("yyyy-MM-dd HH:mm:ss"),
    PATTERN_DATETIME_12("yyyy-MM-dd hh:mm:ss"),
    PATTERN_DATETIME_COMPACT("yyyyMMddHHmmss"),
    PATTERN_YEAR_MON("yyyy-MM"),
    PATTERN_DATE_COMPACT("yyyyMMdd"),
    PATTERN_DATE_SHORT("yyMMdd"),
    PATTERN_YEARMONTH("yyyyMM");

    private String pattern;
}
