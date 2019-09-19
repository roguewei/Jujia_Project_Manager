package com.winston.service;

import com.winston.entity.Permission;

import java.util.List;

public interface IPermissionService {

    List<Permission> queryAll();

    List<Permission> queryByUserName(String username);

    void addAllUrl(Permission permission);

    void addPermission(Permission permission);

    void updatePermission(Permission permission);

    void delPermission(Integer id);

}
