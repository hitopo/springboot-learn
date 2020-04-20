package com.hitopo.service;

import com.hitopo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author hitopo
 * @version v1.0
 * @classname UserServiceTest
 * @time 2020/4/19 21:13
 * @description 用户服务类测试
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    private Logger log = LoggerFactory.getLogger(UserServiceTest.class);
    @Autowired
    private UserService userService;

    @Test
    public void testLogin(){
        User user = new User();
        user.setUsername("ad");
        user.setPassword("asdafs");
    }
}
