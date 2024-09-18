package com.banmenit.libcore.web.aspect;

import com.banmenit.libcore.web.annotation.WebLog;
import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author lizrd
 * @date 2022/12/16 17:24
 */
@Aspect
@Component
public class WebLogAspect {

    private final static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
    /**
     * 换行符
     */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Value("${web.log.logLevel:debug}")
    private String logLevel;

    /**
     * 以自定义 @WebLog 注解为切点
     */
    @Pointcut("@annotation(com.banmenit.libcore.web.annotation.WebLog)")
    public void webLog() {
    }

    /**
     * 环绕
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        // 获取 @WebLog 注解的描述信息
        String methodDescription = getAspectLogDescription(proceedingJoinPoint);
        // 打印请求相关参数
        logger.info("========================================== Start ==========================================");
        // 打印请求 url   描述信息   Http method    调用 controller 的全路径以及执行方法    请求的 IP
        logger.info("URL  : {}  Description  : {}   HTTP Method  : {}   Class Method   : {}.{}    IP     : {}", request.getRequestURL().toString(), methodDescription,
                request.getMethod(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(),
                request.getRemoteAddr());
        // 打印请求入参
        if (logLevel.equals("info")) {
            logger.info("Request Args   : {}", new Gson().toJson(proceedingJoinPoint.getArgs()));
        } else {
            logger.debug("Request Args   : {}", new Gson().toJson(proceedingJoinPoint.getArgs()));
        }
        long startTime = System.currentTimeMillis();
        Object result;
        try {
            result = proceedingJoinPoint.proceed();
            // 打印出参
            if (logLevel.equals("info")) {
                logger.info("Response Args  : {}", new Gson().toJson(result));
            } else {
                logger.debug("Response Args  : {}", new Gson().toJson(result));
            }
        } finally {
            // 接口结束后换行，方便分割查看
            logger.info("Time-Consuming : {} ms =========================================== End ===========================================" + LINE_SEPARATOR, System.currentTimeMillis() - startTime);
        }
        return result;
    }


    /**
     * 获取切面注解的描述
     *
     * @param joinPoint 切点
     * @return 描述信息
     * @throws Exception
     */
    public String getAspectLogDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    WebLog annotation = method.getAnnotation(WebLog.class);
                    if (annotation != null) {
                        description.append(annotation.description());
                    }
                    break;
                }
            }
        }
        return description.toString();
    }

}