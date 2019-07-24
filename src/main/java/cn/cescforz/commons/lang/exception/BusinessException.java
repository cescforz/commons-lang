package cn.cescforz.commons.lang.exception;

import cn.cescforz.commons.lang.enums.ResponseEnum;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>©2018 Cesc. All Rights Reserved.</p>
 * <p>Description: 自定义异常</p>
 *
 * @author cesc
 * @version v1.0
 * @date Create in 2018-12-22 19:04
 */
@Getter
@Setter
public class BusinessException extends Exception {

    private static final long serialVersionUID = 1567951351425380751L;

    /** 错误码 */
    private String errorCode;
    /** 错误信息 */
    private String errorMsg;

    private ResponseEnum responseEnum;


    public BusinessException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BusinessException(String errorCode, String errorMsg) {
        super(String.format("错误码:[%s],错误信息:%s",errorCode,errorMsg));
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public BusinessException(ResponseEnum responseEnum) {
        super(responseEnum.getCnInfo());
        this.responseEnum = responseEnum;
    }

    public BusinessException(ResponseEnum responseEnum, String msg) {
        this(responseEnum, msg, null);
    }

    public BusinessException(ResponseEnum responseEnum, String msg, String boundSymbol) {
        super(String.format("%s%s %s", responseEnum.getCnInfo(), StringUtils.isEmpty(boundSymbol) ? "," : boundSymbol, msg));
        this.responseEnum = responseEnum;
    }
}
