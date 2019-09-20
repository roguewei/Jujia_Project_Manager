package com.winston.controller;

import com.winston.annotation.NeedLog;
import com.winston.entity.GroupRolePermission;
import com.winston.entity.Role;
import com.winston.entity.RolePermission;
import com.winston.service.IGroupRolePermissionService;
import com.winston.service.IRoleService;
import com.winston.utils.result.CodeMsg;
import com.winston.utils.result.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Winston
 * @title: RoleController
 * @projectName shiro-parent
 * @description:
 * @date 2019/7/19 19:19
 */
@Validated
@Api(description = "角色相关接口")
@RestController
@RequestMapping("/web/roles")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IGroupRolePermissionService rolePermissionService;

    @ApiOperation(value = "查询角色", notes = "查询角色")//接口说明
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleName", value = "角色名", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "roleDesc", value = "角色描述", required = false, dataType = "String", paramType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功请求", response = Result.class),
            @ApiResponse(code = 500400, message = "无权限访问"),
            @ApiResponse(code = 500105, message = "未登录")
    })
//    @RequiresPermissions("/web/roles")
    @GetMapping
    public Result query(Role role){
        List<Role> roles = roleService.queryAll(role);
        return Result.success(roles);
    }

    @ApiOperation(value = "根据id查询角色", notes = "根据id查询角色")//接口说明
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", required = false, dataType = "Integer", paramType = "Integer"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功请求", response = Result.class),
            @ApiResponse(code = 500400, message = "无权限访问"),
            @ApiResponse(code = 500105, message = "未登录")
    })
//    @RequiresPermissions("/web/roles/queryById")
    @GetMapping("/queryById")
    public Result queryById(Integer id){
        Role role = roleService.queryById(id);
        return Result.success(role);
    }

    @ApiOperation(value = "新增角色", notes = "新增角色")//接口说明
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleName", value = "角色名", required = true, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "roleDesc", value = "角色描述", required = true, dataType = "String", paramType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功请求", response = Result.class),
            @ApiResponse(code = 401, message = "无权限访问"),
            @ApiResponse(code = 500105, message = "未登录")
    })
//    @RequiresPermissions("/web/roles/add")
    @PostMapping("/add")
    @NeedLog(operator = "5", operatorDesc = "新增角色")
    public Result addRole(Role role){
        roleService.addRole(role);
        return Result.success("新增成功");
    }

    @ApiOperation(value = "修改角色", notes = "修改角色")//接口说明
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer", paramType = "Integer"),
            @ApiImplicitParam(name = "roleName", value = "角色名", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "roleDesc", value = "角色描述", required = false, dataType = "String", paramType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功请求", response = Result.class),
            @ApiResponse(code = 401, message = "无权限访问"),
            @ApiResponse(code = 500105, message = "未登录")
    })
//    @RequiresPermissions("/web/roles/update")
    @PostMapping("/update")
    @NeedLog(operator = "4", operatorDesc = "修改角色")
    public Result updateScenery(Role role){
        roleService.updateRole(role);
        return Result.success("修改成功");
    }

    @ApiOperation(value = "删除角色", notes = "删除角色")//接口说明
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "Integer", paramType = "Integer"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功请求", response = Result.class),
            @ApiResponse(code = 401, message = "无权限访问"),
            @ApiResponse(code = 500105, message = "未登录")
    })
//    @RequiresPermissions("/web/roles/del")
    @GetMapping("/del")
    @NeedLog(operator = "3", operatorDesc = "删除角色")
    public Result delRole(@NotNull(message = "角色id不能为空") Integer id) {
        roleService.delRole(id);
        return Result.success("删除成功");
    }

    @ApiOperation(value = "为角色分配权限", notes = "为角色分配权限")//接口说明
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "Integer", paramType = "Integer"),
            @ApiImplicitParam(name = "perId", value = "权限id", required = true, dataType = "Integer", paramType = "Integer"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功请求", response = Result.class),
            @ApiResponse(code = 401, message = "无权限访问"),
            @ApiResponse(code = 500105, message = "未登录")
    })
//    @RequiresPermissions("/web/roles/saveRolePermission")
    @PostMapping("/saveGroupRolePermission")
    @NeedLog(operator = "5", operatorDesc = "为角色分配权限")
    public Result saveRolePermission(GroupRolePermission groupRolePermission) {
        if (StringUtils.isEmpty(groupRolePermission.getRoleId()) && StringUtils.isEmpty(groupRolePermission.getGroupId())){
            return Result.error(CodeMsg.SELECT_ROLE_ERROR);
        }
        if (StringUtils.isEmpty(groupRolePermission.getPerId())){
            return Result.error(CodeMsg.SELECT_PERMOSSION_ERROR);
        }
        try{
            rolePermissionService.addGroupRolePermission(groupRolePermission);
            return Result.success("分配权限成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(CodeMsg.SELECT__ERROR);
        }
    }

}
