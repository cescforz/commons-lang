package cn.cescforz.commons.lang.exception;

import cn.cescforz.commons.lang.enums.ResponseEnum;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>©2018 Cesc. All Rights Reserved.</p>
 * <p>Description: 自定义非检性异常</p>
 *
 * @author cesc
 * @version v1.0
 * @date Create in 2018-12-22 19:04
 */
@Getter
@Setter
public class BusinessRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -8420380872601474388L;

    /** 错误码 */
    private String errorCode;
    /** 错误信息 */
    private String errorMsg;

    private ResponseEnum responseEnum;


    public BusinessRuntimeException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BusinessRuntimeException(String errorCode, String errorMsg) {
        super(String.format("错误码:[%s],错误信息:%s",errorCode,errorMsg));
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public BusinessRuntimeException(ResponseEnum responseEnum) {
        super(responseEnum.getCnInfo());
        this.responseEnum = responseEnum;
    }

    public BusinessRuntimeException(ResponseEnum responseEnum, String msg) {
        this(responseEnum, msg, null);
    }

    public BusinessRuntimeException(ResponseEnum responseEnum, String msg, String boundSymbol) {
        super(String.format("%s%s %s", responseEnum.getCnInfo(), StringUtils.isEmpty(boundSymbol) ? "," : boundSymbol, msg));
        this.responseEnum = responseEnum;
    }

}
