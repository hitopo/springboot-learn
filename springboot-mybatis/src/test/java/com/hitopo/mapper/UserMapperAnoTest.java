package com.hitopo.mapper;

import com.hitopo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

/**
 * @author hitopo
 * @version v1.0
 * @classname UserMapperAnoTest
 * @time 2020/4/18 10:34
 * @description 测试类
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperAnoTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapperAno userMapperAno;

    @Test
    public void testFindAll() {
        List<User> userList = userMapperAno.findAll();
        userList.forEach(user -> {
            logger.info("userList={}", user);
        });
    }

    @Test
    public void testFindOneById() {
        User user = userMapperAno.findOneById(9L);
        logger.info("user={}", user);
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("测试用户");
        user.setPassword("123");
        user.setCreateTime(LocalDate.of(2010, 11, 23));
        userMapperAno.save(user);
        testFindAll();
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(8L);
        user.setUsername("测试用户2");
        user.setPassword("123444");
        user.setCreateTime(LocalDate.of(2010, 11, 23));
        userMapperAno.update(user);
        testFindAll();
    }

    @Test
    public void testDelete() {
        userMapperAno.delete(7L);
        testFindAll();
    }
}
