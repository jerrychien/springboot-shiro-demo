package com.example.springbootshirodemo.controller;

import com.example.springbootshirodemo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: qianzhiyong
 * @date: 2020/10/12 17:32
 * @description: TODO
 */
@RestController
@Slf4j
public class CustomController {

    @GetMapping("/login")
    public String login(User user) {
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            return "请输入用户名和密码！";
        }
        //用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUsername(),
                user.getPassword()
        );
        usernamePasswordToken.setRememberMe(true);
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
        } catch (UnknownAccountException e) {
            log.error("用户名不存在！", e);
            return "用户名不存在！";
        } catch (AuthenticationException e) {
            log.error("账号或密码错误！", e);
            return "账号或密码错误！";
        } catch (AuthorizationException e) {
            log.error("没有权限！", e);
            return "没有权限";
        }
        log.info("{} 登陆成功", user.getUsername());
        return "success";
    }

    @RequiresPermissions("query")
    @GetMapping("/index")
    public String index() {
        return "index page";
    }

    @GetMapping("/error")
    public String error() {
        return "login error";
    }

    @RequiresRoles("admin")
    @GetMapping("/admin")
    public String admin() {
        return "admin success!";
    }

    @RequiresPermissions("add:query")
    @GetMapping("/addQuery")
    public String addQuery() {
        return "addQuery success!";
    }

    @RequiresPermissions("add:update")
    @GetMapping("/addUpdate")
    public String addUpdate() {
        return "addUpdate success!";
    }
}
