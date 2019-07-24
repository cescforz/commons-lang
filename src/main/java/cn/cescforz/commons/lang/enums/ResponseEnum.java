package cn.cescforz.commons.lang.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>©2019 Cesc. All Rights Reserved.</p>
 * <p>Description: 响应枚举类</p>
 *
 * @author cesc
 * @version 1.0
 * @date Create in 2019-07-24 10:11
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum {

    /** 成功 */
    EXEC_SUCCESS("000", "exec success", "成功"),
    /** 失败 */
    EXEC_FAIL("001", "exec fail", "失败"),
    /** 系统错误 */
    SYSTEM_ERROR("999", "system error", "系统错误"),
    /** 服务异常 */
    SERVICE_EXCEPTION("002", "service exception", "服务异常"),
    /** 非法参数异常 */
    ILLEGAL_ARGUMENT_EXCEPTION("003", "illegal argument exception", "非法参数异常"),
    /** 网关验签失败 */
    GATE_WAY_CHECK_SIGN_FAIL("004", "gateway check sign fail", "网关验签失败"),
    /** 数据已存在 */
    RECORD_EXIST("005", "record exist", "数据已存在"),
    /** 数据不存在 */
    RECORD_NOT_EXIST("006", "record not exist", "数据不存在"),
    /** 数据已被关联 */
    RECORD_RELATED("007", "record related", "数据已被关联"),
    /** 账户登录异常 */
    ACCOUNT_LOGIN_EXCEPTION("008", "account login exception", "账户登录异常"),
    /** 用户名或密码不正确 */
    ACCOUNT_BAD_CREDENTIALS("009", "login bad credentials", "用户名或密码不正确"),
    /** 账户已锁定 */
    ACCOUNT_LOCKED("010", "account locked", "账户已锁定"),
    /** 账户未启用 */
    ACCOUNT_DISABLED("011", "account disabled", "账户未启用"),
    /** 账户已过期 */
    ACCOUNT_EXPIRED("012", "account expired", "账户已过期");


    private String code;
    private String enInfo;
    private String cnInfo;
}
