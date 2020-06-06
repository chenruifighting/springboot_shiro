package com.example.springboot_shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 陈睿
 * @date 2020-06-05 18:11
 */
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(defaultWebSecurityManager);

//        shiro的内置过滤器
//        1、anno:无需认证可以访问
//        2、authc:登录后可以访问
//        3、user:必须拥有记住我功能可以访问
//        4、perms:必须登录并授权才能访问
//        5、role:必须拥有某个权限才能访问

        //拦截页面
        Map<String,String> fileMap = new LinkedHashMap();
        //不用登录可以访问
        fileMap.put("/success","anon");
        //必须登录才能访问
        fileMap.put("/success1","authc");
        //必须登录并授权才能访问
        fileMap.put("/success2","perms[2]");
        fileMap.put("/success3","perms[3]");
        bean.setFilterChainDefinitionMap(fileMap);
        //未登录，跳转登录页面
        bean.setLoginUrl("/toLogin");
        //未授权，跳转未授权页面
        bean.setUnauthorizedUrl("/Unauthorized");
        return bean;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //整合ShiroDialect,用来整合shiro-thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

}
