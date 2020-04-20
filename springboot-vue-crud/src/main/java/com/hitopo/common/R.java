package com.hitopo.common;

/**
 * @author hitopo
 * @version v1.0
 * @classname R
 * @time 2020/4/19 19:05
 * @description 统一封装的返回类
 * 统一封装的返回json字符串是：
 * {
 * "code": 200,
 * "msg":"提示信息",
 * "data":{}
 * }
 */
public class R {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 状态数据
     */
    private String msg;
    /**
     * 数据
     */
    private Object data;

    public R() {
    }

    private R(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private R(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static R create(Integer code, String msg) {
        return new R(code, msg);
    }

    public static R create(Integer code, String msg, Object data) {
        return new R(code, msg, data);
    }

    public static R create(ResultEnum re) {
        return new R(re.getCode(), re.getMsg());
    }

    public static R create(ResultEnum re, Object data) {
        return new R(re.getCode(), re.getMsg(), data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
