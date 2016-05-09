package com.hs.interceptor;

import com.hs.common.service.impl.BaseService;
import org.aspectj.lang.ProceedingJoinPoint;

import java.text.MessageFormat;

/**
 * Created by work_tl on 2016/4/7.
 */
public class ServiceInterceptor {
    public Object aroundInvoke(ProceedingJoinPoint proceedingJoinPoint) throws Exception{
        if(!BaseService.class.isAssignableFrom(proceedingJoinPoint.getTarget().getClass())) {
            throw new Exception(MessageFormat.format("{0}必须实现自BaseService或其子类!", new Object[]{proceedingJoinPoint.getTarget().getClass().getName()}));
        } else {
            BaseService target = (BaseService)proceedingJoinPoint.getTarget();
            String clazz = target.getClass().getName();
            String method = proceedingJoinPoint.getSignature().getName();
            String status = "成功";
            long start = System.currentTimeMillis();
            String errorInfo = "";
            Object obj = null;

            Object t = null;
            try {
                obj = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
                t = obj;
            } catch (Throwable var24) {
                var24.printStackTrace();
            } finally {
                }

            return t;
            }
    }
}
