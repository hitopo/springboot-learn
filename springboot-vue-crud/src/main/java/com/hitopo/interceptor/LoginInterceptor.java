package com.hitopo.interceptor;

import com.hitopo.entity.User;
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

    private Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    @Pointcut("within(com.hitopo.controller.GoodController..*)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object trackInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            log.info("***************用户未登录***************");
            // 跳转到登录界面
            attributes.getResponse().sendRedirect("/login");
        }
        log.info("***************用户已登录***************");
        return joinPoint.proceed();
    }
}
