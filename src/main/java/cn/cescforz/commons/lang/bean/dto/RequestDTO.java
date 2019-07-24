package cn.cescforz.commons.lang.bean.dto;

import cn.cescforz.commons.lang.bean.model.BaseApiRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>©2019 Cesc. All Rights Reserved.</p>
 * <p>Description: </p>
 *
 * @author cesc
 * @version v1.0
 * @date Create in 2019-07-24 15:23
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RequestDTO<T> extends BaseApiRequest {


    private static final long serialVersionUID = 2951311414217518627L;

    /** 请求数据 */
    private T data;
}
