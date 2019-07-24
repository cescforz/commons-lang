package cn.cescforz.commons.lang.bean.model;

import cn.cescforz.commons.lang.enums.ResponseEnum;
import lombok.Data;

import java.io.Serializable;

/*
 　　　　　　　　┏┓　　　┏┓+ +
 　　　　　　　┏┛┻━━━┛┻┓ + +
 　　　　　　　┃　　　　　　　┃
 　　　　　　　┃　　　━　　　┃ ++ + + +
 　　　　　　 ████━████ ┃+
 　　　　　　　┃　　　　　　　┃ +
 　　　　　　　┃　　　┻　　　┃
 　　　　　　　┃　　　　　　　┃ + +
 　　　　　　　┗━┓　　　┏━┛
 　　　　　　　　　┃　　　┃
 　　　　　　　　　┃　　　┃ + + + +
 　　　　　　　　　┃　　　┃　　　　Talk is cheap. Show me the money.
 　　　　　　　　　┃　　　┃ + 　　　　
 　　　　　　　　　┃　　　┃
 　　　　　　　　　┃　　　┃　　+
 　　　　　　　　　┃　 　　┗━━━┓ + +
 　　　　　　　　　┃ 　　　　　　　┣┓
 　　　　　　　　　┃ 　　　　　　　┏┛
 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 　　　　　　　　　　┃┫┫　┃┫┫
 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 */

/**
 * <p>©2018 Cesc. All Rights Reserved.</p>
 * <p>Description: 基础的服务响应体</p>
 *
 * @author cesc
 * @version v1.0
 * @date Create in 2018-12-26 10:03
 */
@Data
public abstract class BaseApiResponse implements Serializable {

    private static final long serialVersionUID = -8245275405854982904L;

    private static final String RESP_CODE_SUCCESS = ResponseEnum.EXEC_SUCCESS.getCode();
    private static final String RESP_CODE_ERROR = ResponseEnum.EXEC_FAIL.getCode();

    /** 响应码 */
    private String respCode;
    /** 响应内容 */
    private String respDesc;
    /** 错误码 */
    private String errCode;
    /** 错误内容 */
    private String errDesc;

    public BaseApiResponse(boolean success, String errCode, String errDesc) {
        this(errCode, errDesc);
        this.respCode = success ? RESP_CODE_SUCCESS : RESP_CODE_ERROR;
        this.respDesc = success ? ResponseEnum.EXEC_SUCCESS.getCnInfo() : ResponseEnum.EXEC_FAIL.getCnInfo();
    }

    public BaseApiResponse(String errCode, String errDesc) {
        this.errCode = errCode;
        this.errDesc = errDesc;
    }

    public BaseApiResponse() {
        this(true, null, null);
    }

    public boolean checkSuccess() {
        return RESP_CODE_SUCCESS.equals(this.respCode);
    }
}
