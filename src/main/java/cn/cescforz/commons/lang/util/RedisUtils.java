package cn.cescforz.commons.lang.util;

import cn.cescforz.commons.lang.tool.StringTools;

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


    public static String keyGenerator(String tableName,String majorKey,String majorKeyValue,String column){
        return StringTools.assembleStr(tableName,":",majorKey,":",majorKeyValue,":",column);
    }


    public static String keyGenerator(String tableName,String majorKey,String majorKeyValue){
        return StringTools.assembleStr(tableName,":",majorKey,":",majorKeyValue);
    }

}
