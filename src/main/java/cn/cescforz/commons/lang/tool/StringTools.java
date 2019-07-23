package cn.cescforz.commons.lang.tool;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>©2018 Cesc. All Rights Reserved.</p>
 * <p>Description: 自定义字符串工具类</p>
 *
 * @author cesc
 * @version v1.0
 * @date Create in 2018-12-22 19:04
 */
public final class StringTools {

    /**
     * 工具类中的方法都是静态方式访问的因此将构造器私有不允许创建对象
     */
    private StringTools() {
        throw new AssertionError();
    }


    /**
     * 可变参数的特点：
     * （1）只能出现在参数列表的最后；
     * （2）...位于变量类型和变量名之间，前后有无空格都可以；
     * （3）调用可变参数的方法时，编译器为该可变参数隐含创建一个数组，在方法体中一数组的形式访问可变参数。
     */

    /**
     * <p>Description: 组装字符串</p>
     *
     * @param args 字符串可变参数
     * @return java.lang.String
     */
    public static String assembleStr(String... args) {
        StringBuffer sb = new StringBuffer();
        if (args != null && args.length != 0) {
            for (Object o : args) {
                if (o != null) {
                    sb.append(o);
                }
            }
        }
        return sb.toString();
    }

    public static boolean isEquals(String str1, String str2) {
        return StringUtils.equals(str1, str2);
    }

    /**
     * <p>Description: 判断某字符串是否为空，为空的标准是 str==null 或 str.length()==0</p>
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(String str) {
        return StringUtils.isNotEmpty(str);
    }

    /**
     * <p>Description: 判断某字符串是否为空或长度为0或由空白符(whitespace) 构成 </p>
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean isBlank(String str) {
        return StringUtils.isBlank(str);
    }

    public static boolean isNotBlank(String str) {
        return StringUtils.isNotBlank(str);
    }

    public static String arraysToStr(Object[] obj) {
        return StringUtils.join(obj);
    }


    public boolean isDigit(String strNum) {
        return strNum.matches("[0-9]{1,}");
    }

    static Pattern pattern = Pattern.compile("\\d+");

    public static String getNumbers(String content) {
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    static Pattern pattern1 = Pattern.compile("\\D+");

    public static String splitNotNumber(String content) {
        Matcher matcher = pattern1.matcher(content);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    private static Pattern MATCH_CN = Pattern.compile("[\u4e00-\u9fa5]");

    public static boolean isContainChinese(String content) {
        Matcher matcher = MATCH_CN.matcher(content);
        return matcher.find();
    }

    public static String insert(int offset, Object source, String target) {
        StringBuffer sb = new StringBuffer(target.trim());
        return sb.insert(offset, source).toString();
    }

    public static String subString(int offset, String source, String target) {
        if (target.trim().contains(source)) {
            target = target.substring(offset);
        }
        return target;
    }

    /**
     * 将目标字符串中子字符串替换为源字符串
     *
     * @param replaceStr : 子字符串
     * @param source     : 源字符串
     * @param target     : 目标字符串
     * @return java.lang.String
     */
    public static String replace(String replaceStr, String source, String target) {
        if (target.contains(replaceStr)) {
            int index = target.indexOf(replaceStr);
            StringBuffer sb = new StringBuffer(target);
            sb.replace(index, index + 1, source);
            return sb.toString();
        }
        return target;
    }

    /**
     *  String.format()
     *  转换符      说明        示例
     *  %s         字符串类型   "mingrisoft"
     *  %c         字符类型     'm'
     *  %b         布尔类型     true
     *  %d         整数类型（十进制）99
     *  %x         整数类型（十六进制）FF
     *  %o         整数类型（八进制）77
     *  %f         浮点类型     99.99
     *  %a         十六进制浮点类型 FF.35AE
     *  %e         指数类型     9.38e+5
     *  %g         通用浮点类型（f和e类型中较短的）
     *  %h         散列码
     *  %%         百分比类型   ％
     *  %n         换行符
     *  %tx        日期与时间类型（x代表不同的日期与时间转换符)
     */
}
