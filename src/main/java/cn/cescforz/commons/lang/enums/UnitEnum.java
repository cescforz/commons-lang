package cn.cescforz.commons.lang.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * <p>©2019 Cesc. All Rights Reserved.</p>
 * <p>Description: 单位枚举类</p>
 *
 * @author cesc
 * @version 1.0
 * @date Create in 2019-07-24 10:11
 */
@Getter
@AllArgsConstructor
public enum UnitEnum {

    /** 纳秒 */
    NANOSECONDS("ns","纳秒"),
    /** 微秒 */
    MICROSECONDS("μs","微秒"),
    /** 毫秒 */
    MILLISECONDS("ms","毫秒"),
    /** 秒 */
    SECONDS("sec","秒"),
    /** 分 */
    MINUTES("min","分"),
    /** 时 */
    HOURS("h","时"),
    /** 天 */
    DAYS("d","天"),
    /** 周 */
    WEEKS("week","周"),
    /** 月 */
    MONTHS("m","月"),
    /** 年 */
    YEARS("y","年");



    private String unit;
    private String des;

}
