package com.feige.Controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {
    @RequestMapping("/index")
    public String index(){
        return "index/index";
    }
    @RequestMapping("/add")
    public String add(){
        return "index/add";
    }
    @RequestMapping("/update")
    public String update(){
        return "index/update";
    }
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "index/login";
    }
    @RequestMapping("/login")
    public String login(String username, String password, Model model){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        try {
            subject.login(token);//执行登录方法
            return "index/index";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户名不存在");
            return "index/login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "index/login";
        }


    }
    @GetMapping("/noauth")
    public String nonuth(){
        return "index/auth";
    }
}
