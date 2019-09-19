package com.winston.service.impl;

import com.winston.entity.*;
import com.winston.exception.ErrorException;
import com.winston.mapper.PermissionMapper;
import com.winston.service.IPermissionService;
import com.winston.service.IRolePermissionService;
import com.winston.service.IUserRoleService;
import com.winston.service.IUserService;
import com.winston.utils.result.CodeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Winston
 * @title: PermissionServiceImpl
 * @projectName shiroDemo
 * @description:
 * @date 2019/7/24 14:32
 */
@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper mapper;

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IRolePermissionService rolePermissionService;

    @Override
    public List<Permission> queryAll() {
        return mapper.selectByExample(new PermissionExample());
    }

    @Override
    public List<Permission> queryByUserName(String username) {
        User user = userService.selectByUsername(username);
        if(user != null){
            List<UserRole> userRoleKeys = userRoleService.queryByUserId(user.getId());
            if(userRoleKeys != null && userRoleKeys.size() > 0){
                List<Integer> roleIds = new ArrayList<>();
                for(UserRole userRole : userRoleKeys){
                    roleIds.add(Integer.valueOf(userRole.getRoleId()));
                }
                List<RolePermission> rolePermissionKeys = rolePermissionService.queryByRoleIds(roleIds);
                if(rolePermissionKeys != null && rolePermissionKeys.size() > 0){
                    List<Integer> perIds = new ArrayList<>();
                    for(RolePermission rolePer : rolePermissionKeys){
                        perIds.add(Integer.valueOf(rolePer.getPerId()));
                    }
                    PermissionExample example = new PermissionExample();
                    example.createCriteria().andIdIn(perIds);
                    List<Permission> permissionList = mapper.selectByExample(example);
                    return permissionList;
                }
            }
        }
        return null;
    }

    @Override
    public void addAllUrl(Permission permission) {
        mapper.insert(permission);
    }

    @Override
    public void addPermission(Permission permission) {
        if(permission.getPerName() != null && permission.getPerUrl() != null){
            PermissionExample example = new PermissionExample();
            example.createCriteria().andPerNameEqualTo(permission.getPerName()).andPerUrlEqualTo(permission.getPerUrl());
            List<Permission> permissions = mapper.selectByExample(example);
            if(permissions != null && permissions.size() > 0){
                throw new ErrorException(CodeMsg.PERMISSION_ALERADY_EXIST);
            }
            mapper.insert(permission);
        }else{
            throw new ErrorException(CodeMsg.PERMISSION_PARAM_NULL);
        }
    }

    @Override
    public void updatePermission(Permission permission) {
        mapper.updateByPrimaryKeySelective(permission);
    }

    @Override
    public void delPermission(Integer id) {
        List<RolePermission> rolePermissions = rolePermissionService.queryByPerIds(id);
        if(rolePermissions != null && rolePermissions.size() > 0){
            throw new ErrorException(CodeMsg.PERMISSION_HAS_ROLE_USE);
        }
        mapper.deleteByPrimaryKey(id);
    }
}
