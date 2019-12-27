package priv.linyu.bill.core.mapper;

import org.apache.ibatis.annotations.Param;
import priv.linyu.bill.core.entity.po.UserInfo;

/**
 * @className: SysMapper
 * @author: QiuShangLin
 * @description:
 * @date: 2019/12/15 11:55
 * @version: 1.0
 **/
public interface UserMapper {

    /**
     * 根据账户名查询单个系统账户对象
     * @param username 账户名
     * @return
     */
    UserInfo selectBySysName(@Param(value = "username")String username);

    /**
     * 校验原密码是否存在
     * @param userId
     * @param oldpassword
     * @return
     */
    Integer checkBypassword(@Param(value = "userId")Integer userId, @Param(value = "oldpassword")String oldpassword);

    /**
     * 修改密码
     * @param id
     * @param newpassword
     * @return
     */
    Integer updatePassword(@Param(value = "userId")Integer id,@Param(value = "newpassword")String newpassword);


}
