package com.winston.service;

import com.winston.entity.RolePermission;

import java.util.List;

public interface IRolePermissionService {

    List<RolePermission> queryByRoleIds(List<Integer> roleIds);

    List<RolePermission> queryByPerIds(Integer perId);

    void addRolePermission(RolePermission rolePermission);

    void deleteByRoleId(Integer roleId);

}
