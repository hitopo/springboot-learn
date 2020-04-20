package com.hitopo.controller;


import com.hitopo.common.R;
import com.hitopo.common.ResultEnum;
import com.hitopo.entity.User;
import com.hitopo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 前端控制器
 * </p>
 * @author hitopo
 * @since 2020-04-19
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 处理用户登录请求
     */
    @PostMapping("/login")
    @ResponseBody
    public R login(User user, HttpSession session) {
        if (userService.login(user)) {
            // 登录成功
            session.setAttribute("username", user.getUsername());
            return R.success(ResultEnum.SUCCESS);
        }
        return R.error(ResultEnum.BAD_REQUEST);
    }

    /**
     * 处理用户登出请求
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("username");
        return "home/login";
    }

    /**
     * 处理用户注册请求
     */
    @PostMapping("/register")
    @ResponseBody
    public R register(User user) {
        return R.success(ResultEnum.SUCCESS);
    }


    /**
     * 跳转到登录界面
     */
    @GetMapping("/login")
    public String login() {
        return "/home/login";
    }

    /**
     * 跳转到注册界面
     */
    @GetMapping("/register")
    public String register() {
        return "home/register";
    }


}

