package com.itwu.aop;

import com.itwu.mapper.OperateLogMapper;
import com.itwu.pojo.OperateLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
@Component
@Aspect
public class LogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    // Spring Boot自带的Jackson ObjectMapper
    @Autowired
    private ObjectMapper objectMapper;

    @Around("@annotation(com.itwu.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1. 获取操作人ID
        Integer operateEmpId = com.itwu.utils.BaseContext.getCurrentId();

        // 2. 获取操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        // 3. 获取操作类名
        String className = joinPoint.getTarget().getClass().getName();

        // 4. 获取操作方法名
        String methodName = joinPoint.getSignature().getName();

        // 5. 获取操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        long begin = System.currentTimeMillis();
        // 6. 调用原始目标方法运行
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

        // 7. 获取方法返回值
        String returnValue = "";
        try {
            returnValue = objectMapper.writeValueAsString(result);
        } catch (Exception e) {
            returnValue = "无法序列化返回值: " + e.getMessage();
        }

        // 8. 获取操作耗时
        Long costTime = end - begin;

        // 9. 记录操作日志
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(operateEmpId);
        operateLog.setOperateTime(operateTime);
        operateLog.setClassName(className);
        operateLog.setMethodName(methodName);
        operateLog.setMethodParams(methodParams);
        operateLog.setReturnValue(returnValue);
        operateLog.setCostTime(costTime);

        operateLogMapper.insert(operateLog);

        log.info("AOP记录操作日志: {}", operateLog);

        return result;
    }
}
