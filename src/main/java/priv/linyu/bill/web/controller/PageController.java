package priv.linyu.bill.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @className: PageController
 * @author: q-linyu
 * @description:
 * @date: 2019/12/16 22:24
 * @version: 1.0
 **/
@CrossOrigin
@Controller
public class PageController {

    /**
     * 打开登录页
     * @return
     */
    @RequestMapping(value = "/login.html")
    public String login(){
        return "login";
    }


    /**
     * 打开用户列表
     * @return
     */
    @GetMapping(value = "/customer/list.html")
    public String userList(){
        return "customer/list";
    }

    /**
     * 打开用户列表
     * @return
     */
    @GetMapping(value = "/customer/add.html")
    public String customerAdd(){
        return "customer/add";
    }

    /**
     * 打开管理员信息
     * @return
     */
    @GetMapping(value = "/personal.html")
    public String personal(){
        return "sys/personal";
    }

    /**
     * 打开管理员信息
     * @return
     */
    @GetMapping(value = "/logs.html")
    public String logs(){
        return "logs/list";
    }

    /**
     * 打开修改密码页面
     * @return
     */
    @GetMapping(value = "/password.html")
    public String password(){
        return "sys/password";
    }

    /**
     * 打开供应商列表
     * @return
     */
    @GetMapping(value = "/provider/list.html")
    public String providerList(){
        return "provider/list";
    }


    /**
     * 打开供应商添加页面
     * @return
     */
    @GetMapping(value = "/provider/add.html")
    public String providerAdd(){
        return "provider/add";
    }


    /**
     * 打开账单列表
     * @return
     */
    @GetMapping(value = "/bill/list.html")
    public String billList(){
        return "bill/list";
    }


    /**
     * 打开账单添加页面
     * @return
     */
    @GetMapping(value = "/bill/add.html")
    public String billAdd(){
        return "bill/add";
    }

    /**
     * 打开统计分析
     * @return
     */
    @GetMapping(value = "/home.html")
    public String echart(){
        return "sys/echart";
    }
}
