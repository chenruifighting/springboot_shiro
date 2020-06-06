package com.example.springboot_shiro.config;

import com.example.springboot_shiro.Mapper.UserMapper;
import com.example.springboot_shiro.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 陈睿
 * @date 2020-06-05 18:15
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //拿到当前登录的对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User)subject.getPrincipal();
        info.addStringPermission(currentUser.getPerms().toString());
        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        //数据库中获取用户信息
        User user = userMapper.findPwdByName(token.getUsername());
        if(user==null){
            return null;//抛出异常：UnknownAccountException
        }

        //返回给前端做判断
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("currentUser",user);

        //密码认证Shiro做
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");

    }
}
