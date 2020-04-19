package com.hitopo.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hitopo
 * @version v1.0
 * @classname PasswordUtilTest
 * @time 2020/4/19 21:04
 * @description 测试密码工具类
 */
public class PasswordUtilTest {

    private final Logger log = LoggerFactory.getLogger(PasswordUtilTest.class);

    @Test
    public void testEncrypt() {
        log.info("hitopo加密:{}", PasswordUtil.encryptPassword("hitopo"));
        log.info("admin加密:{}", PasswordUtil.encryptPassword("admin"));
    }
}
