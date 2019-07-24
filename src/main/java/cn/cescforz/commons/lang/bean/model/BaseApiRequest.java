package cn.cescforz.commons.lang.bean.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*
       ┌─┐       ┌─┐
    ┌──┘ ┴───────┘ ┴──┐
    │                 │
    │       ───       │
    │  ─┬┘       └┬─  │
    │                 │
    │       ─┴─       │
    │                 │
    └───┐         ┌───┘
        │         │
        │         │
        │         │
        │         └──────────────┐
        │                        │
        │                        ├─┐
        │                        ┌─┘
        │                        │
        └─┐  ┐  ┌───────┬──┐  ┌──┘
          │ ─┤ ─┤       │ ─┤ ─┤
          └──┴──┘       └──┴──┘

     今天之所以区别于昨天，恰恰是因为昨天的感受依然存在我们心中 =。=
 */

/**
 * <p>©2018 Cesc. All Rights Reserved.</p>
 * <p>Description: 基础请求抽象类</p>
 *
 * @author cesc
 * @version v1.0
 * @date Create in 2018-12-26 15:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseApiRequest implements Serializable {

    private static final long serialVersionUID = -4257753924890295195L;

    private String appKey;
    private String appSecret;


}
