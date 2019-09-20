package com.winston.controller;

import com.winston.entity.ProGroup;
import com.winston.service.IGroupService;
import com.winston.utils.result.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.Map;

/**
 * @ClassName GroupController
 * @Author: Winston
 * @Description: TODO
 * @Date:Create：in 2019/9/19 14:10
 * @Version：
 */
@Api(description = "组织相关接口")
@RestController
@RequestMapping("/app/group")
public class GroupController {

    @Autowired
    private IGroupService groupService;

    @ApiOperation(value = "查询组织", notes = "查询组织")//接口说明
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupName", value = "组织名", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "groupKeywork", value = "组织关键字", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "groupDesc", value = "组织描述", required = false, dataType = "String", paramType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功请求", response = Result.class),
            @ApiResponse(code = 500400, message = "无权限访问"),
            @ApiResponse(code = 500105, message = "未登录")
    })
    @GetMapping
    public Result queryAll(ProGroup group){
        List<Map<String, Object>> groups = groupService.queryAll(group);
        return Result.success(groups);
    }

    @ApiOperation(value = "根据id查询组织", notes = "根据id查询组织")//接口说明
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "组织id", required = false, dataType = "String", paramType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功请求", response = Result.class),
            @ApiResponse(code = 500400, message = "无权限访问"),
            @ApiResponse(code = 500105, message = "未登录")
    })
    @GetMapping("/queryById")
    public Result queryById(Integer id){
        Map<String, Object> group = groupService.queryById(id);
        return Result.success(group);
    }

    @ApiOperation(value = "新增组织", notes = "新增组织")//接口说明
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupName", value = "组织名", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "groupKeywork", value = "组织关键字", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "groupDesc", value = "组织描述", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "groupIcon", value = "组织图标", required = false, dataType = "MultipartFile", paramType = "MultipartFile"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功请求", response = Result.class),
            @ApiResponse(code = 500400, message = "无权限访问"),
            @ApiResponse(code = 500105, message = "未登录")
    })
    @PostMapping("/add")
    public Result add(MultipartHttpServletRequest request){
        groupService.addGroup(request);
        return Result.success("添加组织成功");
    }

    @ApiOperation(value = "修改组织", notes = "修改组织")//接口说明
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "组织编号", required = true, dataType = "Integer", paramType = "Integer"),
            @ApiImplicitParam(name = "groupName", value = "组织名", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "groupKeywork", value = "组织关键字", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "groupDesc", value = "组织描述", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "groupIcon", value = "组织图标", required = false, dataType = "MultipartFile", paramType = "MultipartFile"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功请求", response = Result.class),
            @ApiResponse(code = 500400, message = "无权限访问"),
            @ApiResponse(code = 500105, message = "未登录")
    })
    @PostMapping("/udpate")
    public Result update(MultipartHttpServletRequest request){
        groupService.updateGroup(request);
        return Result.success("修改组织成功");
    }

    @GetMapping("/delById")
    public Result delById(Integer id){
       groupService.delGroup(id);
        return Result.success("删除成功");
    }

}
