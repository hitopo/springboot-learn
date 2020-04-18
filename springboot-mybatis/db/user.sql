-- create database springboot_mybatis charset utf8;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `username`    varchar(255) DEFAULT NULL COMMENT '用户名',
    `password`    varchar(255) DEFAULT NULL COMMENT '密码',
    `create_time` date     DEFAULT NULL COMMENT '创建日期',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8;


INSERT INTO `springboot-mybatis`.user (id, username, password, create_time) VALUES (5, '张三', '123', '2020-04-08');
INSERT INTO `springboot-mybatis`.user (id, username, password, create_time) VALUES (6, '李四', 'admin', '2020-04-08');
INSERT INTO `springboot-mybatis`.user (id, username, password, create_time) VALUES (7, '王五', 'root', '2020-04-08');
INSERT INTO `springboot-mybatis`.user (id, username, password, create_time) VALUES (8, '赵六', 'adm', '2020-04-08');
INSERT INTO `springboot-mybatis`.user (id, username, password, create_time) VALUES (9, 'admin', '123', '2020-04-08');
INSERT INTO `springboot-mybatis`.user (id, username, password, create_time) VALUES (10, 'root', '3333', '2020-04-08');
INSERT INTO `springboot-mybatis`.user (id, username, password, create_time) VALUES (11, 'user', '2333', '2020-04-08');