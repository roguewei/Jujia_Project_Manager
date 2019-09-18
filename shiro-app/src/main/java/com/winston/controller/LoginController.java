package com.winston.controller;

import com.winston.entity.User;
import com.winston.service.IUserService;
import com.winston.utils.result.CodeMsg;
import com.winston.utils.result.Result;
import com.winston.utils.service.TokenService;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Winston
 * @title: LoginController
 * @projectName shiroDemo
 * @description:
 * @date 2019/7/24 16:24
 */
@Api(description = "登录登出相关接口")
@Validated
@RestController
public class LoginController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "登录提示接口", notes = "后台判断未登录时重定向调用，前端无需调用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "登录用户名", required = true, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "password", value = "登录密码", required = true, dataType = "String", paramType = "String"),
    })
    @ApiResponse(code = 200, message = "成功请求", response = Result.class)
    @GetMapping("/login")
    public Result login(){
        return Result.error(CodeMsg.IS_NOT_LOGIN);
    }

    @ApiOperation(value = "登录接口", notes = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "登录用户名", required = true, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "password", value = "登录密码", required = true, dataType = "String", paramType = "String"),
    })
    @ApiResponse(code = 200, message = "成功请求", response = Result.class)
    @PostMapping("/login")
    public Result login(User user){
        User user1 = userService.queryByUser(user);
        String token = tokenService.getToken(user1);
        return Result.success(token);
    }

    @ApiOperation(value = "退出登录接口", notes = "退出登录接口")
    @ApiImplicitParams({
    })
    @ApiResponse(code = 200, message = "成功请求", response = Result.class)
    @GetMapping("/logout")
    public Result logout(){
        tokenService.clearToken();

        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Result.success("您已退出登录");
    }

    @GetMapping("/unauthorized")
    public Result unauthorized(){
        return Result.success("未登录，请重新登录");
    }
}
