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
     * 是否登录成功
     * @param user 用户名和密码数据
     * @return 是否登录成功
     */
    boolean login(User user);
}
