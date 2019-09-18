package com.winston.service;

import com.winston.entity.UserRole;

import java.util.List;

public interface IUserRoleService {

    List<UserRole> queryByUserId(int userId);

}
