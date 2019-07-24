package cn.cescforz.commons.lang.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>©2018 Cesc. All Rights Reserved.</p>
 * <p>Description: </p>
 *
 * @author cesc
 * @version 1.0
 * @date Create in 2018-12-17 10:02
 */
@Getter
@Setter
public class HttpClientException extends Exception {


    private static final long serialVersionUID = 8553133716564514414L;

    /** 状态码 */
    private Integer statusCode;
    /** 返回内容 */
    private String content;

    public HttpClientException(String message, int statusCode, String content) {
        super(message);
        this.statusCode = statusCode;
        this.content = content;
    }
}
