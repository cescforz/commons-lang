package cn.cescforz.commons.lang.toolkit.util;


import java.util.UUID;

/**
 * <p>©2019 Cesc. All Rights Reserved.</p>
 * <p>Description: redis主键生成策略工具类</p>
 *
 * @author cesc
 * @version v1.0
 * @date Create in 2019-01-09 09:12
 */
public final class RedisUtils {


    private RedisUtils(){throw new AssertionError();}


    /**
     * 生成redis key
     *
     * @param moduleName : 模块名
     * @param tableName  : 表名
     * @param column     : 列名
     * @param variable   : 变量
     * @return java.lang.String
     */
    public static String getRedisKey(String moduleName, String tableName, String column, String variable) {
        return StringTools.assembleStr(moduleName, ":", tableName, ":", column, ":", variable);
    }

    /**
     * 生成redis key
     *
     * @param moduleName : 模块名
     * @param tableName  : 表名
     * @param variable   : 变量
     * @return java.lang.String
     */
    public static String getRedisKey(String moduleName, String tableName, String variable) {
        return StringTools.assembleStr(moduleName, ":", tableName, ":", variable);
    }

    /**
     * uuid生成redis requestId（非纯数字）
     *
     * @return java.lang.String
     */
    public static String getRedisRequestId() {
        return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
    }

    /**
     * 利用snowflake算法生成redis requestId（纯数字）
     *
     * @return java.lang.String
     */
    public static String getRedisRequestNumId(long workerId, long datacenterId) {
        return null;
    }
}
