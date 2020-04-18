package com.hitopo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * @author hitopo
 * @version v1.0
 * @classname Employee
 * @time 2020/4/18 14:50
 * @description 职工类
 */
@Data
@TableName("mp_employee")
public class Employee {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 名称
     */
    @TableField("last_name")
    private String lastName;

    /**
     * Email邮箱
     */
    private String email;

    /**
     * 性别：0-男，1-女
     */
    private String gender;

    /**
     * 名称
     */
    private Integer age;
}