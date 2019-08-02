package cn.cescforz.commons.lang.bean.dto;

import cn.cescforz.commons.lang.bean.model.BaseApiResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * <p>©2019 Cesc. All Rights Reserved.</p>
 * <p>Description: 接口调用返回</p>
 *
 * @author cesc
 * @version v1.0
 * @date Create in 2019-07-24 15:23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ResponseDTO<T> extends BaseApiResponse {

    private static final long serialVersionUID = 7924193368059202977L;

    /** 返回数据 */
    private T respData;

    public ResponseDTO(boolean success, String errCode, String errDesc, T data) {
        super(success, errCode, errDesc);
        this.respData = data;
    }

    public ResponseDTO(boolean success, String errCode, String errDesc) {
        this(success, errCode, errDesc, null);
    }

    public ResponseDTO() {
        this(true, null, null, null);
    }

    public ResponseDTO(T data) {
        this(true, null, null, data);
    }
}
