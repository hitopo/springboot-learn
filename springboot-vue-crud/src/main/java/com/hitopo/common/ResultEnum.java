package com.hitopo.common;

/**
 * @author hitopo
 * @version v1.0
 * @classname ResultEnum
 * @time 2020/4/19 19:35
 * @description 统一封装的返回提示信息和提示码
 */
public enum ResultEnum {

    SUCCESS(200, "请求成功"),
    CREATED(201, "创建成功"),
    DELETED(204, "删除成功"),
    BAD_REQUEST(400, "请求地址不存在或者包含不支持的参数"),
    INNER_ERROR(500, "服务器错误");


    /**
     * 状态码
     */
    private Integer code;
    /**
     * 状态参数
     */
    private String msg;


    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}