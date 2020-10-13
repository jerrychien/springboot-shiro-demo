package com.example.springbootshirodemo.realm;

import com.example.springbootshirodemo.entity.User;
import com.example.springbootshirodemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * @author: qianzhiyong
 * @date: 2020/10/12 17:13
 * @description: TODO
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 权限配置类
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //加入了ehcache之后，有缓存了，在10分钟缓存有效期内都不走这个方法了
        //不加ehcache的情况下，每次调用都会走权限获取的方法
        //获取登录用户名
        String username = (String) principalCollection.getPrimaryPrincipal();
        log.info("--------------------");
        log.info("用户:{} 开始登陆，判断权限是否存在", username);
        User user = userService.getUserByName(username);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        user.getRoleSet().stream().forEach(role -> {
            simpleAuthorizationInfo.addRole(role.getName());
            log.info("用户:{} 拥有角色:{}", username, role.getName());
            role.getPermissionSet().stream().forEach(permission -> {
                simpleAuthorizationInfo.addStringPermission(permission.getName());
                log.info("用户:{} 拥有权限:{}", username, permission.getName());
            });
        });
        return simpleAuthorizationInfo;
    }

    /**
     * 认证配置类
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (StringUtils.isEmpty(authenticationToken.getPrincipal())) {
            return null;
        }
        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        log.info("用户:{} 登陆用户名密码验证", name);
        User user = userService.getUserByName(name);
        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, user.getPassword(), getName());
            return simpleAuthenticationInfo;
        }
    }
}
