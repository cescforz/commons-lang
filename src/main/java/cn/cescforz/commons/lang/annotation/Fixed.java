package cn.cescforz.commons.lang.annotation;

import java.lang.annotation.*;

/**
 * <p>©2019 Cesc. All Rights Reserved.</p>
 * <p>Description: 标识字段是否是可变的</p>
 *
 * @author cesc
 * @version v1.0
 * @date Create in 2019-03-07 19:26
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface Fixed {
}
