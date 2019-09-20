package com.winston.controller;

import com.winston.entity.Dictionary;
import com.winston.service.IDictionaryService;
import com.winston.utils.result.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Winston
 * @title: ParameterController
 * @projectName shiro-parent
 * @description:
 * @date 2019/7/27 14:29
 */
@Api(description = "参数接口")
@Validated
@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

    @Autowired
    private IDictionaryService dictionaryService;

    @ApiOperation(value = "查询字典项", notes = "查询字典项")//接口说明
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dictType", value = "字典类型", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "dictValue", value = "字典值", required = false, dataType = "String", paramType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功请求", response = Result.class),
            @ApiResponse(code = 401, message = "无权限访问"),
            @ApiResponse(code = 500203, message = "未登录")
    })
    @GetMapping
    public Result query(Dictionary dictionary){
        List<Dictionary> query = dictionaryService.query(dictionary);
        return Result.success(query);
    }

    @ApiOperation(value = "新增字典项", notes = "新增字典项")//接口说明
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dictName", value = "字典中文名", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "dictType", value = "字典类型", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "dictValue", value = "字典值", required = false, dataType = "String", paramType = "String"),
            @ApiImplicitParam(name = "dictDesc", value = "字典描述", required = false, dataType = "String", paramType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功请求", response = Result.class),
            @ApiResponse(code = 401, message = "无权限访问"),
            @ApiResponse(code = 500203, message = "未登录")
    })
    @PostMapping("/addDictionary")
    public Result addDictionary(Dictionary dictionary){
        dictionaryService.addDictionary(dictionary);
        return Result.success("新增成功");
    }

}
