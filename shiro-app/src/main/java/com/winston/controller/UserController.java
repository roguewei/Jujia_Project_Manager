package com.winston.controller;

import com.winston.entity.GroupUserRole;
import com.winston.entity.User;
import com.winston.service.IGroupUserRoleService;
import com.winston.service.IUserService;
import com.winston.utils.result.CodeMsg;
import com.winston.utils.result.Result;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Winston
 * @title: UserController
 * @projectName shiroDemo
 * @description:
 * @date 2019/7/24 15:53
 */
@Api(description = "用户相关接口")
@Validated
@RestController
@RequestMapping("/app/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IGroupUserRoleService groupUserRoleService;

    @ApiOperation(value = "查询用户", notes = "根据条件查询用户")//接口说明
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = false, dataType = "Integer", paramType = "Integer"),
            @ApiImplicitParam(name = "username", value = "用户名", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "relName", value = "真实姓名", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "status", value = "用户状态", required = false, dataType = "Integer", paramType = "Integer"),
            @ApiImplicitParam(name = "page", value = "页码,第几页", required = false, dataType = "Integer", paramType = "Integer"),
            @ApiImplicitParam(name = "length", value = "每页数量", required = false, dataType = "Integer", paramType = "Integer")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功请求", response = Result.class),
            @ApiResponse(code = 401, message = "无权限访问"),
            @ApiResponse(code = 500203, message = "未登录")
    })
//    @RequiresPermissions("/web/users")
    @GetMapping
    public Result query(User user,
                        @RequestParam(required = false, defaultValue = "1") int page,
                        @RequestParam(required = false, defaultValue = "10") int length){
        return userService.queryByUser(user, page, length);
    }

    @ApiOperation(value = "根据id查询用户", notes = "根据id查询用户")//接口说明
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = true, dataType = "Integer", paramType = "Integer"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功请求", response = Result.class),
            @ApiResponse(code = 401, message = "无权限访问"),
            @ApiResponse(code = 500203, message = "未登录")
    })
//    @RequiresPermissions("/web/users")
    @GetMapping("/queryById")
    public Result queryById(@NotNull(message = "用户id不能为空")Integer id){
        return Result.success(userService.selectById(id));
    }

    @ApiOperation(value = "新增用户", notes = "新增用户")//接口说明
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "password", value = "用户名", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "relName", value = "真实姓名", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "jobNumber", value = "工号", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "sex", value = "性别代码", required = false, dataType = "Integer", paramType = "Integer"),
            @ApiImplicitParam(name = "mobile", value = "联系电话", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "email", value = "邮箱", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "address", value = "联系地址", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "type", value = "用户类型（0：员工，1：客户）", required = false, dataType = "String", paramType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功请求", response = Result.class),
            @ApiResponse(code = 401, message = "无权限访问"),
            @ApiResponse(code = 500203, message = "未登录")
    })
//    @RequiresPermissions("/web/users")
    @PostMapping("/add")
    public Result add(User user){
        userService.save(user);
        return Result.success("新增成功");
    }

    @ApiOperation(value = "修改用户", notes = "修改用户")//接口说明
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = true, dataType = "Integer", paramType = "Integer"),
            @ApiImplicitParam(name = "username", value = "用户名", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "relName", value = "真实姓名", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "jobNumber", value = "工号", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "sex", value = "性别代码", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "mobile", value = "联系电话", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "email", value = "邮箱", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "address", value = "联系地址", required = false, dataType = "String", paramType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功请求", response = Result.class),
            @ApiResponse(code = 401, message = "无权限访问"),
            @ApiResponse(code = 500203, message = "未登录")
    })
//    @RequiresPermissions("/web/users")
    @PostMapping("/update")
    public Result update(User user){
        userService.update(user);
        return Result.success("修改成功");
    }

    @ApiOperation(value = "根据id删除用户", notes = "根据id删除用户")//接口说明
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = true, dataType = "Integer", paramType = "Integer"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功请求", response = Result.class),
            @ApiResponse(code = 401, message = "无权限访问"),
            @ApiResponse(code = 500203, message = "未登录")
    })
//    @RequiresPermissions("/web/users")
    @GetMapping("/del")
    public Result del(@NotNull(message = "用户id不能为空")Integer id){
        userService.delete(id);
        return Result.success("删除成功");
    }

    @ApiOperation(value = "用户添加组织或角色", notes = "用户添加组织或角色")//接口说明
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户编号", required = true, dataType = "Integer", paramType = "Integer"),
            @ApiImplicitParam(name = "roleId", value = "角色编号", required = true, dataType = "Integer", paramType = "Integer"),
            @ApiImplicitParam(name = "groupId", value = "组织编号", required = true, dataType = "Integer", paramType = "Integer"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功请求", response = Result.class),
            @ApiResponse(code = 401, message = "无权限访问"),
            @ApiResponse(code = 500203, message = "未登录")
    })
    @PostMapping("/saveRoleGroup")
    public Result saveRoleGroup(GroupUserRole groupUserRole){
        if (StringUtils.isEmpty(groupUserRole.getUserId())) {
            return Result.error(CodeMsg.ROLE_USER_NO_CHOOES);
        }
        if (StringUtils.isEmpty(groupUserRole.getRoleId()) && StringUtils.isEmpty(groupUserRole.getGroupId())) {
            return Result.error(CodeMsg.ROLE_USER_NO_CHOOES);
        }
        groupUserRoleService.addGroupUserRole(groupUserRole);
        return Result.success("添加成功");
    }

}
