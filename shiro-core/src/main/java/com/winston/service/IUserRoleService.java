package com.winston.service;

import com.winston.entity.UserRole;

import java.util.List;

public interface IUserRoleService {

    List<UserRole> queryByUserId(Integer userId);

    List<UserRole> queryByRoleId(Integer roleId);

    List<Integer> getRoleIdsByUserName(String username);

    void addUserRole(UserRole userRole);

}
