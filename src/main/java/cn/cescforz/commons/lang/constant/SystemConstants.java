package cn.cescforz.commons.lang.constant;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * <p>©2019 Cesc. All Rights Reserved.</p>
 * <p>Description: 系统常量类</p>
 *
 * @author cesc
 * @version v1.0
 * @date Create in 2019-07-30 20:18
 */
public final class SystemConstants {

    private SystemConstants(){throw new AssertionError();}

    public static final String CONFIRM = "Y";
    public static final String NEGATE = "N";


    /**
     * 编码格式
     */
    public static final Charset CHARSET_UTF_8 = StandardCharsets.UTF_8;
    public static final String CHARSET_STR_UTF_8 = "UTF-8";


    /**
     * 日期默认格式
     * yyyy-MM-dd hh:mm:ss 12小时制
     * yyyy-MM-dd HH:mm:ss 24小时制
     */
    public static final String DATE_PATTERN_24 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN_12 = "yyyy-MM-dd hh:mm:ss";
    public static final String DEFAULT_TIME_ZONE = "GMT+8";


    /** 线程池队列类型 -- 有界的任务队列 */
    public static final String QUEUE_TYPE_LIMITED = "LIMITED";
}
