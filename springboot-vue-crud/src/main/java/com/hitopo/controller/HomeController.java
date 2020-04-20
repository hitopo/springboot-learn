package com.hitopo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author hitopo
 * @version v1.0
 * @classname HomeController
 * @time 2020/4/19 19:54
 * @description 页面跳转控制器
 */
@Controller
public class HomeController {

    /**
     * 跳转到系统首页
     */
    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "home/index";
    }


    /**
     * 跳转到商品列表页
     */
    @GetMapping(value = {"/good"})
    public String user() {
        return "site/goods";
    }


}
