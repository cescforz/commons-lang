package cn.cescforz.commons.lang.toolkit.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;
import java.util.Map;

/**
 * <p>Description: Cglib BeanCopier 工具类</p>
 *
 * @author cesc
 * @version 1.00.00
 * @date Create in 2019/9/6 10:17
 */
@Slf4j
public final class BeanUtils {


    private static Map<String, BeanCopier> map = Maps.newHashMap();

    private BeanUtils() { throw new AssertionError();}

    /**
     * 对象复制
     * @param o 被复制对象，为空会抛出异常
     * @param c  复制类型
     * @param <T>
     * @return
     */
    public static <T> T copyProperties(Object o, Class<T> c) {
        if (o == null || c == null) {
            throw new IllegalArgumentException("复制对象或者被复制类型为空!");
        }
        T t = null;
        try {
            t = c.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            log.error("class newInstance error:", e);
        }
        String name = getClassName(o.getClass(), c);
        BeanCopier beanCopier;
        if (map.containsKey(name)) {
            beanCopier = map.get(name);
        } else {
            beanCopier = BeanCopier.create(o.getClass(), c, false);
            map.put(name, beanCopier);
        }
        beanCopier.copy(o, t, null);
        return t;
    }

    /**
     * 复制队列
     * @param list 被复制队列
     * @param classz 复制类型
     * @param <T>
     * @return
     */
    public static <T> List<T> copyList(List<?> list, Class<T> classz) {
        List<T> resultList = Lists.newLinkedList();
        if (CollectionUtils.isEmpty(list)) {
            return resultList;
        }
        list.forEach(o -> resultList.add(copyProperties(o, classz)));
        return resultList;
    }

    private static String getClassName(Class<?> c1, Class<?> c2) {
        return String.format("%s%s", c1.getName(), c2.getName());
    }

}
