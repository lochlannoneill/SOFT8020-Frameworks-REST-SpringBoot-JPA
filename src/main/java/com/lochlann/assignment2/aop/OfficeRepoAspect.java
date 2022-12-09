package com.lochlann.assignment2.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Slf4j
@Component
@Profile("dev")
public class OfficeRepoAspect {

    @Pointcut("execution(* com.lochlann.assignment2.dao.OfficeRepo.*(..))")
    public void officeRepoMethods() {
    }

    @Before("officeRepoMethods()")
    public void theBeforeAction(JoinPoint joinPoint) throws Throwable {
        log.info(joinPoint.getSignature().toShortString() + " with arguments " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "officeRepoMethods()", returning = "returnValue")
    public void theAfterReturningAction(JoinPoint joinPoint, Object returnValue) throws Throwable {
        log.info(joinPoint.getSignature().toShortString() + "\tReturn Value = " + returnValue);
    }

}
