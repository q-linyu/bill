package priv.linyu.bill.core.service;

import org.apache.ibatis.annotations.Param;
import priv.linyu.bill.core.entity.po.UserInfo;

/**
 * @className: UserService
 * @author: QiuShangLin
 * @description:
 * @date: 2019/12/15 12:06
 * @version: 1.0
 **/
public interface UserService {

    /**
     * 根据账户名查询当前账户信息
     * @param sysName 系统账户
     * @return
     */
    UserInfo selectBySysName(String sysName);

    /**
     * 修改密码
     * @param id
     * @param newpassword
     * @return
     */
    void updatePassword(@Param(value = "id")Integer id, @Param(value = "newpassword")String newpassword);

    /**
     * 校验原密码是否存在
     * @param userId
     * @param oldpassword
     */
    void checkBypassword(Integer userId, String oldpassword);
}
