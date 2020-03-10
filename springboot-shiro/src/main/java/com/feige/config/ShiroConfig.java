package com.feige.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {


    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        /*
        添加shiro的内置过滤器
        anon:无需认证就可以访问
        authc:必须认证才可以访问
        user:记住我功能才能用
        perms：拥有对某个资源的权限才可以访问perms[用户:权限]
        role：拥有某个角色权限才可以访问

         */
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("/add","perms[user:add]");
        linkedHashMap.put("/update","authc");
        //支持通配符linkedHashMap.put("/*","authc");
        bean.setFilterChainDefinitionMap(linkedHashMap);
        //设置登录请求
        bean.setLoginUrl("/toLogin");
        //未授权请求
        bean.setUnauthorizedUrl("/noauth");
        bean.setSuccessUrl("/index");
        return bean;
    }
    //DefaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建realm对象，需要自定义
    @Bean//被spring托管
    public UserRealm userRealm(){
        return new UserRealm();
    }
    //整合ShiroDialect:用来整合shiro-thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
