package com.example.springboot_shiro.Controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 陈睿
 * @date 2020-06-05 18:33
 */
@Controller
public class UserController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("success")
    public String success(){
        return "success";
    }
    @RequestMapping("success1")
    public String success1(){
        return "success1";
    }
    @RequestMapping("success2")
    public String success2(){
        return "success2";
    }
    @RequestMapping("success3")
    public String success3(){
        return "success3";
    }
    @RequestMapping("toLogin")
    public String toLogin(){
        return "login";
    }
    @RequestMapping("login")
    public String login(String name, String password, Model model){
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        try {
            subject.login(token);
            return "index";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }
    @ResponseBody
    @RequestMapping("Unauthorized")
    public String Unauthorized(){
        return "未授权页面不能访问";
    }
    @RequestMapping("logout")
    public String logout(){
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "index";
    }
}
