package com.hitopo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hitopo.entity.User;
import com.hitopo.mapper.UserMapper;
import com.hitopo.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hitopo
 * @since 2020-04-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
