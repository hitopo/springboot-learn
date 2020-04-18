package com.hitopo.entity;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author hitopo
 * @version v1.0
 * @classname User
 * @time 2020/4/18 10:21
 * @description 用户类
 */
@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private LocalDate createTime;
}
