package com.lhhh.aspect;

import com.lhhh.bean.ResultLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: lhhh
 * @date: Created in 2020/7/29
 * @description:
 * @version:1.0
 */
@Aspect
@Component
public class LogAspect {
    public Logger logger = LoggerFactory.getLogger(this.getClass());
    @Pointcut("execution(* com.lhhh.controller.*.*(..))")
    public void log(){}

    @Before("log()")
    public void doBefore(JoinPoint jp){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        Signature signature = jp.getSignature();
        String classMethod = signature.getDeclaringTypeName() + "." + signature.getName();
        Object[] args = jp.getArgs();
        ResultLog resultLog = new ResultLog(url,ip,classMethod,args);
        logger.info("--------resultLog : {}",resultLog);
    }

    @After("log()")
    public void doAfter(){
//        System.out.println("doAfter");
    }

    @AfterReturning(pointcut = "log()",returning = "result")
    public void afterReturning(Object result){
        System.out.println(result);
    }
}
