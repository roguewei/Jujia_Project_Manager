package com.winston.service.impl;

import com.winston.entity.User;
import com.winston.entity.UserRoleExample;
import com.winston.entity.UserRole;
import com.winston.mapper.UserRoleMapper;
import com.winston.service.IUserRoleService;
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
public class UserRoleServiceImpl implements IUserRoleService {

    @Autowired
    private UserRoleMapper mapper;

    @Autowired
    private IUserService userService;

    @Autowired
    private MyShiroRealm myShiroRealm;

    @Override
    public List<UserRole> queryByUserId(Integer userId) {
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<UserRole> userRoleKeys = mapper.selectByExample(example);
        return userRoleKeys;
    }

    @Override
    public List<UserRole> queryByRoleId(Integer roleId) {
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<UserRole> userRoles = mapper.selectByExample(example);
        return userRoles;
    }

    @Override
    public List<Integer> getRoleIdsByUserName(String username) {
        User user = userService.selectByUsername(username);
        if(user != null){
            List<UserRole> userRoles = queryByUserId(user.getId());
            if(userRoles != null && userRoles.size() > 0) {
                List<Integer> roleIds = new ArrayList<>();
                for (UserRole userRole : userRoles) {
                    roleIds.add(userRole.getRoleId());
                }
                return roleIds;
            }
        }
        return null;
    }

    @Override
    public void addUserRole(UserRole userRole) {
        // 删除
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUserIdEqualTo(userRole.getUserId());
        mapper.deleteByExample(example);
        // 添加
        mapper.insert(userRole);

        //更新当前登录的用户的权限缓存
        List<Integer> userid = new ArrayList<Integer>();
        userid.add(userRole.getUserId());
        myShiroRealm.clearUserAuthByUserId(userid);
    }
}
