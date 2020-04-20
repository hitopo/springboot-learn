package com.hitopo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hitopo.common.ResultEnum;
import com.hitopo.entity.User;
import com.hitopo.exception.CustomizedException;
import com.hitopo.mapper.UserMapper;
import com.hitopo.service.UserService;
import com.hitopo.util.PasswordUtil;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 * @author hitopo
 * @since 2020-04-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public void login(User user) {
        String encryptedPassword = PasswordUtil.encryptPassword(user.getPassword());
        User loginUser = baseMapper.selectOne(new QueryWrapper<User>()
                .eq("username", user.getUsername())
                .eq("password", encryptedPassword));
        if (loginUser == null) {
            throw new CustomizedException(ResultEnum.NOT_EXIST_USER_OR_ERROR_PASSWORD);
        }
    }

    @Override
    public void register(User user) {
        String username = user.getUsername().trim();
        User dbUser = baseMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        if (dbUser != null) {
            // 用户名已经存在
            throw new CustomizedException(ResultEnum.USERNAME_ALREADY_EXIST);
        }
        // 保存数据，记得密码要加密
        user.setPassword(PasswordUtil.encryptPassword(user.getPassword()));
        int insertResult = baseMapper.insert(user);
        if (insertResult != 1) {
            throw new RuntimeException();
        }
    }
}
