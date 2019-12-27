package priv.linyu.bill.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @className: HttpState
 * @author: q-linyu
 * @description: 状态码枚举类
 * @date: 2019/12/17 20:37
 * @version: 1.0
 **/
@Getter
@AllArgsConstructor
public enum HttpState {

    /**
     * 成功
     */
    SUCCESS(200,"success"),

    /**
     * 原密码错误
     */
    OLD_PASSWORD_FAILD(403,"原密码错误"),

    /**
     * 服务器内部错误
     */
    ERROR(500,"系统内部错误,请联系管理员!"),

    /**
     * 新增成功
     */
    CREATED(201,"新增成功"),

    /**
     * 新增失败
     */
    NO_CREATED(201,"新增失败"),


    /**
     * 修改成功
     */
    NO_CONTENT(204, "修改成功"),

    /**
     * 修改失败
     */
    UPDATE_FAILD(204, "修改失败"),

    /**
     * 删除成功
     */
    PARTIAL_CONTENT(206, "删除成功"),

    /**
     * 删除失败
     */
    DELETE_FAILD(403,"删除失败"),

    /**
     * 参数错误
     */
    BAD_REQUEST(400,"参数错误"),


    /**
     *  操作失败
     */
    FORBIDDEN(403, "操作失败"),


    /**
     * 用户名或密码错误
     */
    USERNAME_OR_PASSWORD_ERROR(403,"用户名或密码错误"),

    /**
     * 系统账户不存在
     */
    LOGIN_NAME_NOT_FOUND(404,"系统账户不存在"),

    /**
     * 没查到
     */
    NOT_FOUND(404, "暂无数据"),

    /**
     * 参数错误异常
     */
    PARAM_FAIL_CODE(1001,"参数错误异常"),

    /**
     * 参数校验异常
     */
    VALIDATION_CODE (1002,"参数校验异常"),

    /**
     * 数据重复,请检查后提交
     */
    DUPLICATE_KEY_CODE(1003,"数据重复,请检查后提交"),

    /**
     * 系统账户信息获取失败
     */
    GET_SYS_FAIL(1004,"系统账户信息获取失败"),

    /**
     * 登录失败，该用户已被冻结
     */
    LOCKED_FAIL(401,"该用户已被冻结"),

    /**
     * 登录失败，该用户已被冻结
     */
    AUTH_EXPIRE_FAIL(401,"身份认证已过期"),

    /**
     * 退出成功
     */
    LOGOUT_SUCCESS(200,"退出成功"),

    ;

    /**
     * 状态码
     */
    private final Integer status;


    /**
     * 消息
     */
    private final String msg;

}
