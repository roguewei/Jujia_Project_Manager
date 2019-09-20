package com.winston.service;

import com.winston.entity.GroupRolePermission;

import java.util.List;

public interface IGroupRolePermissionService {

    List<GroupRolePermission> queryByRoleIds(List<Integer> roleIds);

    List<GroupRolePermission> queryByGroupIds(List<Integer> groupIds);

    List<GroupRolePermission> queryByPerIds(Integer perId);

    void addGroupRolePermission(GroupRolePermission rolePermission);

    void deleteByRoleId(Integer roleId);

}
