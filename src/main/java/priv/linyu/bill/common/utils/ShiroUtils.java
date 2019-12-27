package priv.linyu.bill.common.utils;

import java.util.Collection;
import java.util.Objects;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.LogoutAware;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisSessionDAO;
import priv.linyu.bill.core.entity.po.UserInfo;

/**
 * @className: ShiroUtils
 * @author: q-linyu
 * @description: shiro工具类
 * @date: 2019/12/16 21:07
 * @version: 1.0
 **/
public class ShiroUtils {


    /**
     * 私有构造器
     */
    private ShiroUtils(){ }

    private static RedisSessionDAO redisSessionDAO = SpringUtil.getBean(RedisSessionDAO.class);

    /**
     * 获取当前用户session
     * @return
     */
    public static Session getSession(){
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 登出
     */
    public static void logout(){
        SecurityUtils.getSubject().logout();
    }

    /**
     * 获取当前用户信息
     * @return
     */
    public static UserInfo getUserInfo(){
        return (UserInfo) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 删除用户缓存信息
     * @param username  用户名称
     * @param isSession  是否删除session
     */
    public static void deleteCache(String username, boolean isSession) {
        // 从缓存中获取session
        Session session = null;

        Collection<Session> sessions = redisSessionDAO.getActiveSessions();

        UserInfo userInfo;

        Object attribute = null;

        //遍历Session,找到该用户名称对应的Session
        for (Session sessionInfo : sessions) {
            attribute = sessionInfo.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (Objects.isNull(attribute)){
                continue;
            }

            userInfo = (UserInfo) ((SimplePrincipalCollection)attribute).getPrimaryPrincipal();
            if (Objects.isNull(userInfo)){
                continue;
            }

            if (Objects.equals(userInfo.getUsername(),username)){
                session = sessionInfo;
            }

        }

        if (session == null || attribute == null){
            return;
        }

        // 删除session
        if (isSession){
            redisSessionDAO.delete(session);
        }

        // 删除Cache,在访问受限接口是会重新授权
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        Authenticator authc = securityManager.getAuthenticator();
        ((LogoutAware) authc).onLogout((SimplePrincipalCollection) attribute);

    }

}