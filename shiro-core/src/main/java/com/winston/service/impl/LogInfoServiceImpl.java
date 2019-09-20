package com.winston.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winston.entity.LogInfo;
import com.winston.entity.LogInfoExample;
import com.winston.mapper.LogInfoMapper;
import com.winston.service.ILogInfoService;
import com.winston.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Winston
 * @title: LogInfoServiceImpl
 * @projectName shiro-parent
 * @description:
 * @date 2019/7/23 18:04
 */
@Service
public class LogInfoServiceImpl implements ILogInfoService {

    @Autowired
    private LogInfoMapper mapper;

    @Override
    public Result query(LogInfo logInfo, int page, int length) {
        PageHelper.startPage(page, length);
        LogInfoExample example = new LogInfoExample();
        example.setOrderByClause("create_time desc");
        LogInfoExample.Criteria criteria = example.createCriteria();
        if(logInfo.getUserId() != null){
            criteria.andUserIdEqualTo(logInfo.getUserId());
        }
        if(logInfo.getResultType() != null){
            criteria.andResultTypeEqualTo(logInfo.getResultType());
        }
        if(logInfo.getLogType() != null){
            criteria.andLogTypeEqualTo(logInfo.getLogType());
        }
        List<LogInfo> logInfos = mapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(logInfos);
        return Result.success(logInfos, pageInfo);
    }

    @Override
    public boolean addLogInfo(LogInfo logInfo) {
        return mapper.insertSelective(logInfo)>0;
    }

}
