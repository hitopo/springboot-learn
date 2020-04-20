package com.hitopo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hitopo.entity.User;

/**
 * <p>
 * 服务类
 * </p>
 * @author hitopo
 * @since 2020-04-19
 */
public interface UserService extends IService<User> {

    /**
     * 用户登录
     * @param user 用户名和密码数据
     */
    void login(User user);

    /**
     * 注册用户
     * @param user 用户信息
     */
    void register(User user);
}
