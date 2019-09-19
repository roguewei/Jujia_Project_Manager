package com.winston.controller;

import com.winston.entity.User;
import com.winston.service.IUserService;
import com.winston.utils.result.Result;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Winston
 * @title: UserController
 * @projectName shiroDemo
 * @description:
 * @date 2019/7/24 15:53
 */
@Api(description = "用户相关接口")
@RestController
@RequestMapping("/app/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "查询用户", notes = "根据条件查询用户")//接口说明
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = false, dataType = "Integer", paramType = "Integer"),
            @ApiImplicitParam(name = "username", value = "用户名", required = false, dataType = "Integer", paramType = "Integer"),
            @ApiImplicitParam(name = "relName", value = "真实姓名", required = false, dataType = "Integer", paramType = "Integer"),
            @ApiImplicitParam(name = "status", value = "用户状态", required = false, dataType = "Integer", paramType = "Integer"),
            @ApiImplicitParam(name = "page", value = "页码,第几页", required = false, dataType = "Integer", paramType = "Integer"),
            @ApiImplicitParam(name = "length", value = "每页数量", required = false, dataType = "Integer", paramType = "Integer")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功请求", response = Result.class),
            @ApiResponse(code = 401, message = "无权限访问"),
            @ApiResponse(code = 500203, message = "未登录")
    })
    @RequiresPermissions("/web/users")
    @GetMapping
    public Result query(User user,
                        @RequestParam(required = false, defaultValue = "1") int page,
                        @RequestParam(required = false, defaultValue = "10") int length){
        return userService.queryByUser(user, page, length);
    }

}
