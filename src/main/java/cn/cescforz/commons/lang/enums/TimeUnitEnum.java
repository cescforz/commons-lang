package cn.cescforz.commons.lang.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * <p>©2019 Cesc. All Rights Reserved.</p>
 * <p>Description: 时间单位枚举</p>
 *
 * @author cesc
 * @version 1.0
 * @date Create in 2019-07-24 14:11
 */
@Getter
@AllArgsConstructor
public enum TimeUnitEnum {

    /** 毫秒 */
    MILLISECONDS(ChronoUnit.MILLIS, TimeUnit.MILLISECONDS, Calendar.MILLISECOND, 1L, 'I', UnitEnum.MILLISECONDS),
    /** 秒 */
    SECONDS(ChronoUnit.SECONDS, TimeUnit.SECONDS, Calendar.SECOND, 1000L, 'S', UnitEnum.SECONDS),
    /** 分 */
    MINUTES(ChronoUnit.MINUTES, TimeUnit.MINUTES, Calendar.MINUTE, 1000L * 60, 'N', UnitEnum.MINUTES),
    /** 时 */
    HOURS(ChronoUnit.HOURS, TimeUnit.HOURS, Calendar.HOUR_OF_DAY, 1000L * 3600, 'H', UnitEnum.HOURS),
    /** 天 */
    DAYS(ChronoUnit.DAYS, TimeUnit.DAYS, Calendar.DAY_OF_MONTH, 1000L * 3600 * 24, 'D', UnitEnum.DAYS),
    /** 周 */
    WEEKS(ChronoUnit.WEEKS, null, Calendar.DAY_OF_WEEK, 1000L * 3600 * 24 * 7, 'W', UnitEnum.WEEKS),
    /** 月 */
    MONTHS(ChronoUnit.MONTHS, null, Calendar.MONTH, 2630000000L, 'M', UnitEnum.MONTHS),
    /** 年 */
    YEARS(ChronoUnit.YEARS, null, Calendar.YEAR, 31556900000L, 'Y', UnitEnum.YEARS);


    private ChronoUnit chronoUnit;
    private TimeUnit timeUnit;
    private int calendarField;
    private long milliseconds;
    private char code;
    private UnitEnum unitEnum;

    public static TimeUnitEnum of(TimeUnit timeUnit) {
        if (timeUnit == null) {
            return null;
        }
        switch (timeUnit) {
            case MILLISECONDS:
                return MILLISECONDS;
            case SECONDS:
                return SECONDS;
            case MINUTES:
                return MINUTES;
            case HOURS:
                return HOURS;
            case DAYS:
                return DAYS;
            default:
                throw new IllegalArgumentException(String.format("Unsupported TimeUnit:%s", timeUnit));
        }
    }


    public static TimeUnitEnum of(ChronoUnit chronoUnit) {
        if (chronoUnit == null) {
            return null;
        }
        switch (chronoUnit) {
            case MILLIS:
                return MILLISECONDS;
            case SECONDS:
                return SECONDS;
            case MINUTES:
                return MINUTES;
            case HOURS:
                return HOURS;
            case DAYS:
                return DAYS;
            case WEEKS:
                return WEEKS;
            case MONTHS:
                return MONTHS;
            case YEARS:
                return YEARS;
            default:
                throw new IllegalArgumentException(String.format("Unsupported ChronoUnit:%s", chronoUnit));
        }
    }

    public static TimeUnitEnum of(UnitEnum unitEnum) {
        if (unitEnum == null) {
            return null;
        }
        switch (unitEnum) {
            case MILLISECONDS:
                return MILLISECONDS;
            case SECONDS:
                return SECONDS;
            case MINUTES:
                return MINUTES;
            case HOURS:
                return HOURS;
            case DAYS:
                return DAYS;
            case WEEKS:
                return WEEKS;
            case MONTHS:
                return MONTHS;
            case YEARS:
                return YEARS;
            default:
                throw new IllegalArgumentException(String.format("Unsupported UnitEnum:%s", unitEnum));
        }
    }


    public static TimeUnitEnum from(char code) {
        switch (code) {
            case 'I':
                return MILLISECONDS;
            case 'S':
                return SECONDS;
            case 'N':
                return MINUTES;
            case 'H':
                return HOURS;
            case 'D':
                return DAYS;
            case 'W':
                return WEEKS;
            case 'M':
                return MONTHS;
            case 'Y':
                return YEARS;
            default:
                throw new IllegalArgumentException(String.format("Unknown:%c", code));
        }
    }

    public char toShortCode(){
        return code;
    }

    public ChronoUnit toChronoUnit(){
        return chronoUnit;
    }

    public TimeUnit toTimeUnit(){
        return timeUnit;
    }

    public int toCalendarField(){
        return calendarField;
    }

    public long toMilliseconds(){
        return milliseconds;
    }

    public UnitEnum toUnitEnum(){
        return unitEnum;
    }

    public boolean isShorterThan(TimeUnitEnum another) {
        return this.milliseconds < another.milliseconds;
    }

    public boolean isLongerThan(TimeUnitEnum another) {
        return this.milliseconds > another.milliseconds;
    }

    public static  TimeUnitEnum from(String unitString) {
        unitString = unitString.trim().toUpperCase();
        if(unitString.charAt(unitString.length() - 1) != 'S') {
            unitString = unitString + "S";
        }
        return TimeUnitEnum.valueOf(unitString);
    }


    /*

    from——A 类型转换方法，它接受单个参数并返回此类型的相应实例

    of——一个聚合方法，接受多个参数并返回该类型的实例，并把他们合并在一起

    valueOf——from 和 to 更为详细的替代 方式

    instance 或 getinstance——返回一个由其参数 (如果有的话) 描述的实例，但不能说它具有相同的值

    create 或 newInstance——与 instance 或 getInstance 类似，除了该方法保证每个调用返回一个新的实例

    getType——与 getInstance 类似，但是如果在工厂方法中不同的类中使用。Type 是工厂方法返回的对象类型

    newType——与 newInstance 类似，但是如果在工厂方法中不同的类中使用。Type 是工厂方法返回的对象类型

     */
}
