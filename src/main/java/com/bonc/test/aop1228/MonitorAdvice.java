package com.bonc.test.aop1228;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author : xuelc
 * @Description:
 * @Date : Created in 15:00 2018/12/28.
 * @Modified By:
 */
//@Aspect
@Component
public class MonitorAdvice {
    //@Pointcut("execution(* com.bonc.test.*(..))")
    public void pointcut(){

    }
    //@Around("pointcut()")
    public void around(ProceedingJoinPoint pjp) throws Throwable{

        MonitionSession.begin(pjp.getSignature().getName());
        pjp.proceed();
        MonitionSession.end();

    }
}
