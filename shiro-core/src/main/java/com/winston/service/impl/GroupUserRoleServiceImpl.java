package com.winston.service.impl;

import com.winston.entity.*;
import com.winston.mapper.GroupUserRoleMapper;
import com.winston.service.IGroupUserRoleService;
import com.winston.service.IUserService;
import com.winston.shiro.MyShiroRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Winston
 * @title: UserRoleServiceImpl
 * @projectName shiroDemo
 * @description:
 * @date 2019/7/24 14:33
 */
@Service
public class GroupUserRoleServiceImpl implements IGroupUserRoleService {

    @Autowired
    private GroupUserRoleMapper mapper;

    @Autowired
    private IUserService userService;

    @Autowired
    private MyShiroRealm myShiroRealm;

    @Override
    public List<GroupUserRole> queryByUserId(Integer userId) {
        GroupUserRoleExample example = new GroupUserRoleExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<GroupUserRole> userRoleKeys = mapper.selectByExample(example);
        return userRoleKeys;
    }

    @Override
    public List<GroupUserRole> queryByRoleId(Integer roleId) {
        GroupUserRoleExample example = new GroupUserRoleExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<GroupUserRole> groupUserRoles = mapper.selectByExample(example);
        return groupUserRoles;
    }

    @Override
    public List<Integer> getRoleIdsByUserName(String username) {
        User user = userService.selectByUsername(username);
        if(user != null){
            List<GroupUserRole> groupUserRoles = queryByUserId(user.getId());
            if(groupUserRoles != null && groupUserRoles.size() > 0) {
                List<Integer> roleIds = new ArrayList<>();
                for (GroupUserRole userRole : groupUserRoles) {
                    roleIds.add(userRole.getRoleId());
                }
                return roleIds;
            }
        }
        return null;
    }

    @Override
    public void addGroupUserRole(GroupUserRole groupUserRole) {
        // 删除
        if(groupUserRole.getRoleId() != null){
            GroupUserRoleExample example = new GroupUserRoleExample();
            example.createCriteria().andUserIdEqualTo(groupUserRole.getUserId()).andRoleIdIsNotNull();
            List<GroupUserRole> groupUserRoles = mapper.selectByExample(example);
            if(groupUserRoles != null && groupUserRoles.size() > 0 && groupUserRoles.get(0).getRoleId() != null){
                mapper.deleteByExample(example);
            }
        }
        if(groupUserRole.getGroupId() != null){
            GroupUserRoleExample example = new GroupUserRoleExample();
            example.createCriteria().andUserIdEqualTo(groupUserRole.getUserId()).andGroupIdIsNotNull();
            List<GroupUserRole> groupUserRoles = mapper.selectByExample(example);
            if(groupUserRoles != null && groupUserRoles.size() > 0 && groupUserRoles.get(0).getGroupId() != null){
                mapper.deleteByExample(example);
            }
        }
        // 添加
        mapper.insert(groupUserRole);

        //更新当前登录的用户的权限缓存
        List<Integer> userid = new ArrayList<Integer>();
        userid.add(groupUserRole.getUserId());
        myShiroRealm.clearUserAuthByUserId(userid);
    }
}
