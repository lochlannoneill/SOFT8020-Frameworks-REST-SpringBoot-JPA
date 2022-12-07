package com.lochlann.assignment2.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.cache.interceptor.CacheOperationInvoker;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Slf4j
@Component
@Profile("dev")
public class DepartmentRepoAspect {

    @Pointcut("execution(* com.lochlann.assignment2.dao.DepartmentRepo.*(..))")
    public void departmentRepoMethods() {
    }

    @Before("departmentRepoMethods()")
    public void theBeforeAction(JoinPoint joinPoint) throws Throwable {
        log.info(joinPoint.getSignature().toShortString() + " with arguments " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "departmentRepoMethods()", returning = "returnValue")
    public void theAfterReturningAction(JoinPoint joinPoint, Object returnValue) throws Throwable {
        log.info(joinPoint.getSignature().toShortString() + "\tReturn Value = " + returnValue);
    }

}
