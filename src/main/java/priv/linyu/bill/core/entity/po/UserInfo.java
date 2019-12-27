package priv.linyu.bill.core.entity.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @className: UserInfo
 * @author: QiuShangLin
 * @description: 系统账户实体类
 * @date: 2019/12/15 11:52
 * @version: 1.0
 **/
@Data
public class UserInfo implements Serializable {

    /**
     * 主键id
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 是否锁定  1：正常  0：锁定
     */
    private Integer isLock;
}
