package com.winston.service.impl;

import com.winston.entity.*;
import com.winston.exception.ErrorException;
import com.winston.mapper.PermissionMapper;
import com.winston.service.IGroupRolePermissionService;
import com.winston.service.IGroupUserRoleService;
import com.winston.service.IPermissionService;
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
    private IGroupUserRoleService groupUserRoleService;

    @Autowired
    private IGroupRolePermissionService groupRolePermissionService;

    @Override
    public List<Permission> queryAll() {
        return mapper.selectByExample(new PermissionExample());
    }

    @Override
    public List<Permission> queryByUserName(String username) {
        User user = userService.selectByUsername(username);
        if(user != null){
            List<GroupUserRole> userRoleKeys = groupUserRoleService.queryByUserId(user.getId());
            if(userRoleKeys != null && userRoleKeys.size() > 0){
                List<Integer> roleIds = new ArrayList<>();
                List<Integer> groupIds = new ArrayList<>();
                for(GroupUserRole userRole : userRoleKeys){
                    if(userRole.getRoleId() != null){
                        roleIds.add(userRole.getRoleId());
                    }
                    if(userRole.getGroupId() != null){
                        groupIds.add(userRole.getGroupId());
                    }
                }
                List<GroupRolePermission> rolePermissionKeys = groupRolePermissionService.queryByRoleIds(roleIds);
                List<GroupRolePermission> groupRolePermissions = groupRolePermissionService.queryByGroupIds(groupIds);
                List<Permission> permissionListByRoleId = null;
                List<Permission> permissionListByGroupId = null;
                if(rolePermissionKeys != null && rolePermissionKeys.size() > 0){
                    List<Integer> perIds = new ArrayList<>();
                    for(GroupRolePermission rolePer : rolePermissionKeys){
                        perIds.add(Integer.valueOf(rolePer.getPerId()));
                    }
                    PermissionExample example = new PermissionExample();
                    example.createCriteria().andIdIn(perIds);
                    permissionListByRoleId = mapper.selectByExample(example);
                }
                if(groupRolePermissions != null && groupRolePermissions.size() > 0){
                    List<Integer> perIds = new ArrayList<>();
                    for(GroupRolePermission rolePer : groupRolePermissions){
                        perIds.add(Integer.valueOf(rolePer.getPerId()));
                    }
                    PermissionExample example = new PermissionExample();
                    example.createCriteria().andIdIn(perIds);
                    permissionListByGroupId = mapper.selectByExample(example);
                }
                if(permissionListByRoleId != null || permissionListByGroupId != null ){
                    if(permissionListByGroupId != null && permissionListByRoleId != null){
                        for(Permission permission : permissionListByGroupId){
                            for(int i=0; i<permissionListByRoleId.size(); i++)
                            {
                                if(permission.getId().equals(permissionListByRoleId.get(i).getId()) ){
                                    permissionListByRoleId.remove(permissionListByRoleId.get(i));
                                }
                            }
                        }
                        permissionListByGroupId.addAll(permissionListByRoleId);
                        return permissionListByGroupId;
                    }else{
                        if(permissionListByRoleId == null){
                            return permissionListByGroupId;
                        }else{
                            return permissionListByRoleId;
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public List<Permission> queryHaveNot(String username) {
        List<Permission> permissionsAll = queryAll();
        List<Permission> permissionsHave = queryByUserName(username);
        if(permissionsAll != null){
            if(permissionsHave == null){
                return permissionsAll;
            }
            for(Permission permission : permissionsHave){
                for(int perHave=0; perHave<permissionsAll.size() ; perHave++){
                    if(permission.getId().equals(permissionsAll.get(perHave).getId()) ){
                        permissionsAll.remove(permissionsAll.get(perHave));
                    }
                }
            }
            return permissionsAll;
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
        List<GroupRolePermission> rolePermissions = groupRolePermissionService.queryByPerIds(id);
        if(rolePermissions != null && rolePermissions.size() > 0){
            throw new ErrorException(CodeMsg.PERMISSION_HAS_ROLE_USE);
        }
        mapper.deleteByPrimaryKey(id);
    }
}
