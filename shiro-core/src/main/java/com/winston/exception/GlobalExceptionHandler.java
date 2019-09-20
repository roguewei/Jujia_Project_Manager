package com.winston.exception;

import com.mysql.jdbc.MysqlDataTruncation;
import com.winston.utils.result.CodeMsg;
import com.winston.utils.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/***
 * 自定义异常
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
      * @Author Winston
      * @Description 捕获权限不足时抛出的异常
      * @Date 11:24 2019/7/13
      * @Param
      * @return
      **/
    @ExceptionHandler(value = AuthorizationException.class)
    Result authorizationException(AuthorizationException errorException){
        return Result.error(CodeMsg.HAS_NOT_PERMISSION);
    }

    /**
     * @auther: Winston
     * @Description: 捕获数据库执行异常
     * @param:
     * @return:
     * @date: 2019/9/16 16:43
     */
    @ExceptionHandler(value = MysqlDataTruncation.class)
    Result mysqlDataTruncation(MysqlDataTruncation errorException){
        return Result.error(CodeMsg.MYSQL_DATA_ERROR);
    }

    @ExceptionHandler(value = ErrorException.class)
    Result errorException(ErrorException errorException){
        CodeMsg codeMsg = new CodeMsg();
        codeMsg.setStatus(errorException.getStatus());
        codeMsg.setMsg(errorException.getMsg());
        return Result.error(codeMsg);
    }

}
