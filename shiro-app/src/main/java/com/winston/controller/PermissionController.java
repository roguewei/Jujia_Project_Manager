package com.winston.controller;

import com.winston.entity.Permission;
import com.winston.service.IPermissionService;
import com.winston.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName PermissionController
 * @Author: Winston
 * @Description: TODO
 * @Date:Create：in 2019/9/19 17:01
 * @Version：
 */
@RestController
@RequestMapping("/app/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @GetMapping("/queryByUsername")
    public Result queryByUsername(String username){
        List<Permission> permissions = permissionService.queryByUserName(username);
        return Result.success(permissions);
    }

    @GetMapping("/queryHaveNot")
    public Result queryHaveNot(String username){
        List<Permission> permissions = permissionService.queryHaveNot(username);
        return Result.success(permissions);
    }

}
