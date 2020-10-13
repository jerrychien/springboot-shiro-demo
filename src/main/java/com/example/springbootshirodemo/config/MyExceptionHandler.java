package com.example.springbootshirodemo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: qianzhiyong
 * @date: 2020/10/12 17:37
 * @description: TODO
 */
@ControllerAdvice
@Slf4j
public class MyExceptionHandler {
    @ExceptionHandler
    @ResponseBody
    public String ErrorHandler(AuthorizationException e) {
        log.error("没有通过权限验证! message:{}", e.getMessage());
        return "没有通过权限验证！";
    }
}
