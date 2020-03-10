package com.feige.config;

import com.feige.pojo.User;
import com.feige.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;


public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了doGetAuthorizationInfo方法");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //从数据库中获取某个用户的权限
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        String role;
        if("胡飞".equals(user.getUsername())){
            role = "user:add";
        }else {
            role = "user:update";
        }
        //给用户授权
        info.addStringPermission(role);
        //info.addStringPermission("user:add");
        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了doGetAuthenticationInfo方法");
        //用户名，密码~ 数据中取
        UsernamePasswordToken usernamePassword = (UsernamePasswordToken)authenticationToken;
        User user = userService.getUser(usernamePassword.getUsername());
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("loginUser",user.getUsername());
        if(!usernamePassword.getUsername().equals(user.getUsername())){
            return null;//抛出异常
        }
        //密码认证，shiro做
        //可以加密md5
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
