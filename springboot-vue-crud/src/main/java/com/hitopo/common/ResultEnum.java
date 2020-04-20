package com.hitopo.common;

/**
 * @author hitopo
 * @version v1.0
 * @classname ResultEnum
 * @time 2020/4/19 19:35
 * @description 统一封装的返回提示信息和提示码
 */
public enum ResultEnum {

    // 通用返回格式
    SUCCESS(200, "请求成功"),
    CREATED(201, "创建成功"),
    DELETED(204, "删除成功"),
    BAD_REQUEST(400, "请求地址不存在或者包含不支持的参数"),
    INNER_ERROR(500, "服务器错误"),

    // 自定义返回的错误格式
    // 用户部分
    NOT_EXIST_USER_OR_ERROR_PASSWORD(10001, "用户名或者密码错误！"),
    USERNAME_ALREADY_EXIST(10002, "用户名已经存在！"),

    // 文件上传部分
    FILE_IS_EMPTY(20001, "上传的文件不能为空！"),
    FILE_UPLOAD_ERROR(20002, "文件上传失败！");


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