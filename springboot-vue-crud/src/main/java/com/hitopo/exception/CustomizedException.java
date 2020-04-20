package com.hitopo.exception;

import com.hitopo.common.ResultEnum;

/**
 * @author hitopo
 * @version v1.0
 * @classname CustomizedException
 * @time 2020/4/20 10:47
 * @description 自定义异常类
 */
public class CustomizedException extends RuntimeException {

    private Integer code;

    public CustomizedException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public CustomizedException(ResultEnum re){
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
