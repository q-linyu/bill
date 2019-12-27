package priv.linyu.bill.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import priv.linyu.bill.common.enums.HttpState;
import priv.linyu.bill.common.exception.BusinessRuntimeException;
import priv.linyu.bill.common.utils.Constant;
import priv.linyu.bill.common.utils.RedisUtil;
import priv.linyu.bill.common.utils.ShiroUtils;
import priv.linyu.bill.core.entity.ResponseData;
import priv.linyu.bill.core.entity.po.UserInfo;
import priv.linyu.bill.core.entity.vo.UserVO;
import priv.linyu.bill.core.service.LogsService;
import priv.linyu.bill.core.service.UserService;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * @className: UserController
 * @author: QiuShangLin
 * @description: 系统账户视图控制器
 * @date: 2019/12/15 12:10
 * @version: 1.0
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private LogsService logsService;

    /**
     * 登录
     * @param username  账户名
     * @param password  密码
     * @return
     */
    @PostMapping(value = "/login")
    public ResponseData<String> login(@RequestParam(value = "username")String username,
                                      @RequestParam(value = "password")String password){
        try {

            // 获取主体内容
            Subject subject = SecurityUtils.getSubject();


            // 获取令牌
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);

            // 执行登录
            subject.login(token);

            // 记录日志
            logsService.save(ShiroUtils.getUserInfo().getUserId(),"登录");

            // 记录登录的次数
            redisUtil.incr(Constant.loginKey,1);

            return new ResponseData<>(HttpState.SUCCESS,ShiroUtils.getSession().getId().toString());

        }catch (ExpiredCredentialsException e){
            // 身份认证已过期
            throw new BusinessRuntimeException(HttpState.AUTH_EXPIRE_FAIL);
        }catch (IncorrectCredentialsException e){
            // 用户或者密码错误
            throw new BusinessRuntimeException(HttpState.USERNAME_OR_PASSWORD_ERROR);
        }catch (LockedAccountException  e){
            // 登录失败，该用户已被冻结
            throw new BusinessRuntimeException(HttpState.LOCKED_FAIL);
        } catch (AuthenticationException e){
            // 该用户不存在
            throw new BusinessRuntimeException(HttpState.LOGIN_NAME_NOT_FOUND);
        } catch (Exception e){
            // 系统内部错误,请联系管理员!
            throw new BusinessRuntimeException(HttpState.ERROR);
        }
    }


    /**
     * 获取当前用户信息
     * @return
     */
    @GetMapping("/getUserInfo")
    public ResponseData<UserVO> getUserInfo(){
        // 封装对象
        UserVO userInfo = new UserVO();
        UserInfo info = ShiroUtils.getUserInfo();
        userInfo.setId(info.getUserId());
        userInfo.setUsername(info.getUsername());
        // 设置当前系统的ip地址
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            userInfo.setIp(ip);
        }catch (UnknownHostException e){
            e.printStackTrace();
        }
        userInfo.setSessionId(ShiroUtils.getSession().getId().toString());
        userInfo.setLoginTime(new Date());

        return new ResponseData<>(HttpState.SUCCESS,userInfo);
    }

    /**
     * 退出
     * @return
     */
    @RequiresUser
    @GetMapping(value = "/logout")
    public ResponseData<Void> logout(){
        ShiroUtils.logout();
        // 记录日志
        logsService.save(ShiroUtils.getUserInfo().getUserId(),"退出成功");
        return new ResponseData<>(HttpState.SUCCESS);
    }

    /**
     * 校验原密码是否存在
     * @param oldpassword
     * @return
     */
    @GetMapping(value = "/checkBypassword")
    public ResponseData<Void> checkBypassword(@RequestParam(value = "oldpassword")String oldpassword){
        UserInfo info = ShiroUtils.getUserInfo();
        userService.checkBypassword(info.getUserId(),oldpassword);
        return new ResponseData<>(HttpState.SUCCESS);
    }

    /**
     * 修改密码
     * @param newpassword
     * @return
     */
    @PutMapping(value = "/updatePassword")
    public ResponseData<Void> updatePassword(@RequestParam(value = "newpassword")String newpassword){
        UserInfo info = ShiroUtils.getUserInfo();
        userService.updatePassword(info.getUserId(),newpassword);
        // 记录日志
        logsService.save(info.getUserId(),"修改密码成功");
        return new ResponseData<>(HttpState.NO_CONTENT);
    }


}
