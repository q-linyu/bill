package priv.linyu.bill.common.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import priv.linyu.bill.common.utils.ShiroUtils;
import priv.linyu.bill.core.entity.po.UserInfo;
import priv.linyu.bill.core.service.UserService;

import java.util.Objects;

/**
 * @className: ShiroRealm
 * @author: q-linyu
 * @description: 自定义shiro身份认证器
 * @date: 2019/12/16 21:26
 * @version: 1.0
 **/
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权
     * @param principals  主体内容
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("AuthorizationInfo");
        return null;
    }

    /**
     * 认证
     * @param token 令牌
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // 获取当前系统账户名
        String username = (String) token.getPrincipal();

        // 根据账户名查询当前账户信息
        UserInfo record = userService.selectBySysName(username);
        if (Objects.isNull(record)){
            throw new AuthenticationException("身份认证失败");
        }

        // 判断账号是否被冻结
        if (record.getIsLock() == null || 0 == record.getIsLock()){
            throw new LockedAccountException();
        }

        // 验证
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                record,
                record.getPassword(),
                ByteSource.Util.bytes(record.getSalt()),
                getName()
        );

        // 校验成功后,清除缓存和session
        ShiroUtils.deleteCache(username,true);

        return info;
    }
}
