package com.hitopo.exception;

import com.hitopo.common.ResultEnum;

/**
 * @author hitopo
 * @version v1.0
 * @classname UserException
 * @time 2020/4/20 10:47
 * @description 自定义用户异常类
 */
public class UserException extends RuntimeException {

    private Integer code;

    public UserException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public UserException(ResultEnum re){
        super(re.getMsg());
        this.code = re.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
