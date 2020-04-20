package com.hitopo.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hitopo
 * @version v1.0
 * @classname LoginInterceptor
 * @time 2020/4/19 21:53
 * @description 登录拦截器，用来实现简单的用户是否登录的验证
 */
@Aspect
@Component
public class LoginInterceptor {

    private final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    @Pointcut("within(com.hitopo.controller..*) && !within(com.hitopo.controller.LoginController)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object trackInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String username = (String) request.getSession().getAttribute("username");
        if (username == null) {
            log.info("***************用户未登录***************");
            // 跳转到登录界面
            attributes.getResponse().sendRedirect(request.getContextPath() + "/login");
        } else {
            log.info("***************用户已登录***************");
        }
        return joinPoint.proceed();
    }
}
