package com.winston.service.impl;

import com.winston.entity.*;
import com.winston.exception.ErrorException;
import com.winston.mapper.RoleMapper;
import com.winston.service.IGroupRolePermissionService;
import com.winston.service.IGroupUserRoleService;
import com.winston.service.IRoleService;
import com.winston.service.IUserService;
import com.winston.utils.result.CodeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Winston
 * @title: RoleServiceImpl
 * @projectName shiroDemo
 * @description:
 * @date 2019/7/24 14:32
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper mapper;

    @Autowired
    private IGroupRolePermissionService rolePermissionService;

    @Autowired
    private IGroupUserRoleService userRoleService;

    @Autowired
    private IUserService userService;

    @Override
    public List<Role> queryAll(Role role) {
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        if(role.getRoleName() != null){
            criteria.andRoleNameEqualTo(role.getRoleName());
        }
        if(role.getRoleDesc() != null){
            criteria.andRoleDescEqualTo(role.getRoleDesc());
        }
        List<Role> roles = mapper.selectByExample(example);
        return roles;
    }

    @Override
    public Role queryById(Integer id) {
        Role role = mapper.selectByPrimaryKey(id);
        return role;
    }

    @Override
    public List<Role> queryByIds(List<Integer> roleIds) {
        if(roleIds != null && roleIds.size() > 0){
            RoleExample example = new RoleExample();
            example.createCriteria().andIdIn(roleIds);
            List<Role> roles = mapper.selectByExample(example);
            return roles;
        }
        return null;
    }

    @Override
    public List<Role> queryByUserName(String username) {
        User user = userService.selectByUsername(username);
        if(user != null){
            List<GroupUserRole> userRoles = userRoleService.queryByUserId(user.getId());
            if(userRoles != null && userRoles.size() > 0){
                List<Integer> roleIds = new ArrayList<>();
                for(GroupUserRole userRole : userRoles){
                    roleIds.add(userRole.getRoleId());
                }
                return queryByIds(roleIds);
            }
        }
        return null;
    }

    @Override
    public void addRole(Role role) {
        if(role.getRoleDesc() == null || role.getRoleName() == null){
            throw new ErrorException(CodeMsg.ROLE_ADD_ERROR);
        }
        mapper.insert(role);
    }

    @Override
    public void updateRole(Role role) {
        int i = mapper.updateByPrimaryKeySelective(role);
        if(i <= 0){
            throw new ErrorException(CodeMsg.ROLE_UPDATE_ERROR);
        }
    }

    @Override
    public void delRole(Integer roleid) {
        List<GroupUserRole> userRoles = userRoleService.queryByRoleId(roleid);
        if(userRoles != null && userRoles.size() > 0){
            throw new ErrorException(CodeMsg.ROLE_HAS_USER_USE);
        }

        // 删除角色资源
        rolePermissionService.deleteByRoleId(roleid);
        // 删除角色
        mapper.deleteByPrimaryKey(roleid);
    }
}
