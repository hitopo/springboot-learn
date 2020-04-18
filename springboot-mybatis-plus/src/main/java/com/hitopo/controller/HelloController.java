package com.hitopo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hitopo
 * @version v1.0
 * @classname HelloController
 * @time 2020/4/18 9:27
 * @description 控制器
 */
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
