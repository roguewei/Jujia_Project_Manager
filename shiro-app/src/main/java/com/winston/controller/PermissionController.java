package com.winston.controller;

import com.winston.entity.Permission;
import com.winston.service.IPermissionService;
import com.winston.utils.result.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName PermissionController
 * @Author: Winston
 * @Description: 权限管理
 * @Date:Create：in 2019/9/19 17:01
 * @Version：
 */
@Api(description = "权限管理相关接口")
@Validated
@RestController
@RequestMapping("/app/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @ApiOperation(value = "根据用户名查询拥有的权限", notes = "根据用户名查询拥有的权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "登录用户名", required = true, dataType = "String", paramType = "String"),
    })
    @ApiResponse(code = 200, message = "成功请求", response = Result.class)
    @GetMapping("/queryByUsername")
    public Result queryByUsername(@NotBlank(message = "用户名不能为空") String username){
        List<Permission> permissions = permissionService.queryByUserName(username);
        return Result.success(permissions);
    }

    @ApiOperation(value = "根据用户名查询该用户没有的权限", notes = "根据用户名查询该用户没有的权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "登录用户名", required = true, dataType = "String", paramType = "String"),
    })
    @ApiResponse(code = 200, message = "成功请求", response = Result.class)
    @GetMapping("/queryHaveNot")
    public Result queryHaveNot(@NotBlank(message = "用户名不能为空")String username){
        List<Permission> permissions = permissionService.queryHaveNot(username);
        return Result.success(permissions);
    }

    @ApiOperation(value = "新增权限", notes = "新增权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "perName", value = "权限名", required = true, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "perUrl", value = "权限路径", required = true, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "type", value = "权限类型（0：菜单，1：按钮）", required = true, dataType = "Integer", paramType = "Integer"),
            @ApiImplicitParam(name = "parentId", value = "父级权限id", required = true, dataType = "Integer", paramType = "Integer"),
    })
    @ApiResponse(code = 200, message = "成功请求", response = Result.class)
    @PostMapping("/add")
    public Result add(Permission permission){
        permissionService.addPermission(permission);
        return Result.success("新增成功");
    }

    @ApiOperation(value = "修改权限", notes = "修改权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer", paramType = "Integer"),
            @ApiImplicitParam(name = "perName", value = "权限名", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "perUrl", value = "权限路径", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "type", value = "权限类型（0：菜单，1：按钮）", required = false, dataType = "Integer", paramType = "Integer"),
            @ApiImplicitParam(name = "parentId", value = "父级权限id", required = false, dataType = "Integer", paramType = "Integer"),
    })
    @ApiResponse(code = 200, message = "成功请求", response = Result.class)
    @PostMapping("/update")
    public Result update(Permission permission){
        permissionService.updatePermission(permission);
        return Result.success("修改成功");
    }

    @ApiOperation(value = "删除权限", notes = "删除权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer", paramType = "Integer"),
    })
    @ApiResponse(code = 200, message = "成功请求", response = Result.class)
    @GetMapping("/del")
    public Result del(@NotNull(message = "权限id不能为空")Integer id){
        permissionService.delPermission(id);
        return Result.success("删除成功");
    }

}
