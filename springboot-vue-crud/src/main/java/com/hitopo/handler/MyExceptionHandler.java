package com.hitopo.handler;

import com.hitopo.common.R;
import com.hitopo.common.ResultEnum;
import com.hitopo.exception.CustomizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author hitopo
 * @version v1.0
 * @classname MyExceptionHandler
 * @time 2020/4/20 10:50
 * @description 统一异常处理类
 */
@RestControllerAdvice
public class MyExceptionHandler {

    /**
     * 处理用户异常
     * @param e 自定义异常
     */
    @ExceptionHandler(CustomizedException.class)
    public R handleUserException(CustomizedException e) {
        // 封装异常信息成R
        return R.create(e.getCode(), e.getMessage());
    }

    /**
     * 通用异常处理方法
     * @return
     */
    @ExceptionHandler(Exception.class)
    public R handleException() {
        //返回服务器内部错误
        return R.create(ResultEnum.INNER_ERROR);
    }
}
