package priv.linyu.bill.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.linyu.bill.common.enums.HttpState;
import priv.linyu.bill.common.exception.BusinessRuntimeException;
import priv.linyu.bill.common.utils.SHA256Util;
import priv.linyu.bill.core.entity.po.UserInfo;
import priv.linyu.bill.core.mapper.LogsMapper;
import priv.linyu.bill.core.mapper.UserMapper;
import priv.linyu.bill.core.service.UserService;


/**
 * @className: UserServiceImpl
 * @author: QiuShangLin
 * @description:
 * @date: 2019/12/15 12:08
 * @version: 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LogsMapper logsMapper;

    /**
     * 根据账户名查询当前账户信息
     * @param sysName 系统账户
     * @return
     */
    @Override
    public UserInfo selectBySysName(String sysName){
        return userMapper.selectBySysName(sysName);
    }

    /**
     * 修改密码
     * @param id
     * @param newpassword
     */
    @Override
    public void updatePassword(Integer id, String newpassword) {
        // 密码加密
        String shxPassword = SHA256Util.sha256(newpassword,"Q.SGK66842ASLPZQBFSAKLZUI");
        int count = userMapper.updatePassword(id,shxPassword);
        if (count <= 0){
            throw new BusinessRuntimeException(HttpState.UPDATE_FAILD);
        }

    }

    /**
     * 校验原密码是否存在
     * @param userId
     * @param oldpassword
     */
    @Override
    public void checkBypassword(Integer userId, String oldpassword) {
        // 密码加密
        String shxPassword = SHA256Util.sha256(oldpassword,"Q.SGK66842ASLPZQBFSAKLZUI");
        int count = userMapper.checkBypassword(userId, shxPassword);
        if (count <= 0){
            throw new BusinessRuntimeException(HttpState.OLD_PASSWORD_FAILD);
        }
    }

}
