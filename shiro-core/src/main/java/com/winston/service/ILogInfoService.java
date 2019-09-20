package com.winston.service;


import com.winston.entity.LogInfo;
import com.winston.utils.result.Result;

/**
 * @ClassName ILogInfoService
 * @Author: Winston
 * @Description: TODO
 * @Date:Create：in  2019/09/09 9:48
 * @Version：
 */
public interface ILogInfoService {

    Result query(LogInfo logInfo, int pag, int length);

    boolean addLogInfo(LogInfo logInfo);

}
