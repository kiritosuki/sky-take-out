package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
public class AutoFillAspect {

    private static final Logger log = LoggerFactory.getLogger(AutoFillAspect.class);

    /**
     * 声名切入点
     * @param autoFill
     */
    @Pointcut("execution(* com.sky.mapper..*(..)) && @annotation(autoFill)")
    public void autoFillPointCut(AutoFill autoFill){
    }

    @Before("autoFillPointCut(autoFill)")
    public void autoFill(JoinPoint joinPoint, AutoFill autoFill) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        log.info("开始进行字段的自动填充...");
        //获取拦截到的方法上的注释value属性枚举类型
        OperationType operationType = autoFill.value();
        //获取拦截到的方法的形参
        Object[] args = joinPoint.getArgs();
        if(args == null || args.length == 0){
            return;
        }
        Object entity = args[0];
        //准备要赋值的数据
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();
        if(operationType == OperationType.INSERT){
            //通过反射获取对象中的方法
            Method setCreateTimeMethod = entity.getClass().getMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
            Method setUpdateTimeMethod = entity.getClass().getMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
            Method setCreateUserMethod = entity.getClass().getMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
            Method setUpdateUserMethod = entity.getClass().getMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
            //调用方法为对象赋值
            setCreateTimeMethod.invoke(entity, now);
            setUpdateTimeMethod.invoke(entity, now);
            setCreateUserMethod.invoke(entity, currentId);
            setUpdateUserMethod.invoke(entity, currentId);
        }else if(operationType == OperationType.UPDATE){
            //通过反射获取对象中的方法
            Method setUpdateTimeMethod = entity.getClass().getMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
            Method setUpdateUserMethod = entity.getClass().getMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
            //调用方法为对象赋值
            setUpdateTimeMethod.invoke(entity, now);
            setUpdateUserMethod.invoke(entity, currentId);
        }
    }
}
