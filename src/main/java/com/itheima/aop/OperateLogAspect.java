package com.itheima.aop;

import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.util.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class OperateLogAspect {

    // 注入 Mapper
    @Autowired
    private OperateLogMapper operateLogMapper;

    // 定义切点：controller包下的所有方法 或 使用了@LogOperation注解的方法
    @Pointcut("@annotation(com.itheima.anno.Log)")
    public void logPointCut() {}

    // 环绕通知
    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        // 获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // 获取目标类和方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = method.getName();

        // 获取参数名和参数值
        Object[] args = joinPoint.getArgs();
        String params = Arrays.stream(args)
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        Object result = null;
        try {
            result = joinPoint.proceed(); // 执行原方法
        } catch (Exception e) {
            throw e;
        } finally {
            long costTime = System.currentTimeMillis() - startTime;

            // 构建操作日志对象
            OperateLog operateLog = new OperateLog();
            operateLog.setOperateEmpId(getCurrentUserId()); // 从 ThreadLocal 或 SecurityContext 获取当前用户ID
            operateLog.setOperateTime(LocalDateTime.now());
            operateLog.setClassName(className);
            operateLog.setMethodName(methodName);
            operateLog.setMethodParams(params);
            operateLog.setReturnValue(result != null ? result.toString() : "null");
            operateLog.setCostTime(costTime);

            // 插入日志
            log.info("记录操作日志：{}", operateLog);
            try {
                operateLogMapper.insert(operateLog);
            } catch (Exception ex) {
                log.error("插入操作日志失败", ex);
            }
        }

        return result;
    }

    // 示例方法：获取当前登录用户的ID（根据你的项目实际情况修改）
    private Integer getCurrentUserId() {
        return CurrentHolder.getCurrentId(); // TODO: 替换为真实用户ID 完成
    }
}