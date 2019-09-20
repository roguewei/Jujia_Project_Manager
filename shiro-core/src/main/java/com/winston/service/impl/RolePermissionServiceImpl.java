package com.winston.service.impl;

import com.winston.entity.*;
import com.winston.mapper.GroupRolePermissionMapper;
import com.winston.mapper.GroupUserRoleMapper;
import com.winston.mapper.RolePermissionMapper;
import com.winston.mapper.UserRoleMapper;
import com.winston.service.IGroupRolePermissionService;
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
public class RolePermissionServiceImpl implements IGroupRolePermissionService {

    @Autowired
    private GroupRolePermissionMapper mapper;

    @Autowired
    private GroupUserRoleMapper groupUserRoleMapper;

    @Autowired
    private MyShiroRealm myShiroRealm;

    @Override
    public List<GroupRolePermission> queryByRoleIds(List<Integer> roleIds) {
        if(roleIds != null && roleIds.size() > 0){
            GroupRolePermissionExample example = new GroupRolePermissionExample();
            example.createCriteria().andRoleIdIn(roleIds);
            return mapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public List<GroupRolePermission> queryByGroupIds(List<Integer> groupIds) {
        if(groupIds != null && groupIds.size() > 0){
            GroupRolePermissionExample example = new GroupRolePermissionExample();
            example.createCriteria().andGroupIdIn(groupIds);
            return mapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public List<GroupRolePermission> queryByPerIds(Integer perId) {
        GroupRolePermissionExample example = new GroupRolePermissionExample();
        GroupRolePermissionExample.Criteria criteria = example.createCriteria();
        if(perId != null){
            criteria.andPerIdEqualTo(String.valueOf(perId));
            List<GroupRolePermission> rolePermissions = mapper.selectByExample(example);
            return rolePermissions;
        }
        return null;
    }

    //更新权限
    @Override
    public void addGroupRolePermission(GroupRolePermission groupRolePermission) {
        //删除
        GroupRolePermissionExample example = new GroupRolePermissionExample();
        GroupRolePermissionExample.Criteria criteria = example.createCriteria();
        if(groupRolePermission.getRoleId() != null){
            criteria.andRoleIdEqualTo(groupRolePermission.getRoleId());
            mapper.deleteByExample(example);
        }
        if(groupRolePermission.getGroupId() != null){
            criteria.andGroupIdEqualTo(groupRolePermission.getGroupId());
            mapper.deleteByExample(example);
        }
        //添加
        if(!StringUtils.isEmpty(groupRolePermission.getPerId())){
            String[] resourcesArr = groupRolePermission.getPerId().split(",");
            for(String resourcesId:resourcesArr ){
                GroupRolePermission r = new GroupRolePermission();
                if(groupRolePermission.getRoleId() != null){
                    r.setRoleId(groupRolePermission.getRoleId());
                }
                if(groupRolePermission.getGroupId() != null){
                    r.setGroupId(groupRolePermission.getGroupId());
                }
                r.setPerId(resourcesId);
                mapper.insert(r);
            }
        }

        GroupUserRoleExample userRoleExample = new GroupUserRoleExample();
//        userRoleExample.createCriteria().andRoleIdEqualTo(groupRolePermission.getRoleId());
        List<GroupUserRole> userRoles = groupUserRoleMapper.selectByExample(userRoleExample);
        List<Integer> userIds= new ArrayList<>();
        for(GroupUserRole userRole : userRoles){
            userIds.add(userRole.getUserId());
        }
        //更新当前登录的用户的权限缓存
        myShiroRealm.clearUserAuthByUserId(userIds);
    }

    @Override
    public void deleteByRoleId(Integer roleId) {
        GroupRolePermissionExample example = new GroupRolePermissionExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        mapper.deleteByExample(example);
    }
}
