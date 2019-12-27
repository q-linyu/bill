package priv.linyu.bill.common.shiro;


import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * @className: ShiroSessionManager
 * @author: q-linyu
 * @description: shiro的session管理器
 * @date: 2019/12/16 21:47
 * @version: 1.0
 **/
public class ShiroSessionManager extends DefaultWebSessionManager {


    /**
     * 请求头
     */
    private final static  String AUTHORIZATION = "Authorization";

    /**
     * session唯一标识
     */
    private final static  String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    public ShiroSessionManager(){
        super();
        this.setDeleteInvalidSessions(true);
    }

    /**
     * 请求头获取token
     * @param request
     * @param response
     * @return
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {

        // 获取 token
        String token = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        if (StringUtils.isBlank(token)){
            return super.getSessionId(request, response);
        }

        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
        return token;

    }
}
