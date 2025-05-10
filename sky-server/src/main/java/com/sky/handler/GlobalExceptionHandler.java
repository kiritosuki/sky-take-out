package com.sky.handler;

import com.sky.constant.MessageConstant;
import com.sky.exception.BaseException;
import com.sky.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * sql语句异常
     * @param ex
     * @return
     */
    //Cause: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry 'zhangsan' for key 'employee.idx_username'
    @ExceptionHandler
    public Result sqlIntegrityConstraintViolationExceptionHandler(SQLIntegrityConstraintViolationException ex){
        String errorMsg = ex.getMessage();
        if(errorMsg.contains("Duplicate entry")){
            String message = errorMsg.split(" ")[2];
            return Result.error(message + MessageConstant.ALREADY_EXIST);
        }else{
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
    }
}
