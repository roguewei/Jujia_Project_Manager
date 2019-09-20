package com.winston.service;

import com.winston.entity.GroupUserRole;
import com.winston.entity.UserRole;

import java.util.List;

public interface IGroupUserRoleService {

    List<GroupUserRole> queryByUserId(Integer userId);

    List<GroupUserRole> queryByRoleId(Integer roleId);

    List<Integer> getRoleIdsByUserName(String username);

    void addGroupUserRole(GroupUserRole groupUserRole);

}
