package com.winston.service.impl;

import com.winston.entity.RolePermissionExample;
import com.winston.entity.RolePermission;
import com.winston.entity.UserRole;
import com.winston.entity.UserRoleExample;
import com.winston.mapper.RolePermissionMapper;
import com.winston.mapper.UserRoleMapper;
import com.winston.service.IRolePermissionService;
import com.winston.shiro.MyShiroRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Winston
 * @title: RolePermissionServiceImpl
 * @projectName shiroDemo
 * @description:
 * @date 2019/7/24 14:32
 */
@Service
public class RolePermissionServiceImpl implements IRolePermissionService {

    @Autowired
    private RolePermissionMapper mapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private MyShiroRealm myShiroRealm;

    @Override
    public List<RolePermission> queryByRoleIds(List<Integer> roleIds) {
        RolePermissionExample example = new RolePermissionExample();
        example.createCriteria().andRoleIdIn(roleIds);
        List<RolePermission> rolePermissionKeys = mapper.selectByExample(example);
        return rolePermissionKeys;
    }

    @Override
    public List<RolePermission> queryByPerIds(Integer perId) {
        RolePermissionExample example = new RolePermissionExample();
        RolePermissionExample.Criteria criteria = example.createCriteria();
        if(perId != null){
            criteria.andPerIdEqualTo(String.valueOf(perId));
            List<RolePermission> rolePermissions = mapper.selectByExample(example);
            return rolePermissions;
        }
        return null;
    }

    //更新权限
    @Override
    public void addRolePermission(RolePermission rolePermission) {
        //删除
        RolePermissionExample example = new RolePermissionExample();
        RolePermissionExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(rolePermission.getRoleId());
        mapper.deleteByExample(example);
        //添加
        if(!StringUtils.isEmpty(rolePermission.getPerId())){
            String[] resourcesArr = rolePermission.getPerId().split(",");
            for(String resourcesId:resourcesArr ){
                RolePermission r = new RolePermission();
                r.setRoleId(rolePermission.getRoleId());
                r.setPerId(resourcesId);
                mapper.insert(r);
            }
        }

        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andRoleIdEqualTo(rolePermission.getRoleId());
        List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
        List<Integer> userIds= new ArrayList<>();
        for(UserRole userRole : userRoles){
            userIds.add(userRole.getUserId());
        }
        //更新当前登录的用户的权限缓存
        myShiroRealm.clearUserAuthByUserId(userIds);
    }

    @Override
    public void deleteByRoleId(Integer roleId) {
        RolePermissionExample example = new RolePermissionExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        mapper.deleteByExample(example);
    }
}
