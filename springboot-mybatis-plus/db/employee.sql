-- create database springboot_mybatis charset utf8;

# 创建数据表
DROP TABLE IF EXISTS `mp_employee`;

CREATE TABLE `mp_employee`
(
    `id`        bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    `last_name` varchar(50) COMMENT '名称',
    `email`     varchar(50) COMMENT 'Email邮箱',
    `gender`    char(1) COMMENT '性别：0-男，1-女',
    `age`       int COMMENT '年龄'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

#  插入测试数据
INSERT INTO mp_employee(last_name, email, gender, age)
VALUES ('Tom', 'tom@qq.com', 0, 22);

INSERT INTO mp_employee(last_name, email, gender, age)
VALUES ('James', 'james@qq.com', 0, 18);

INSERT INTO mp_employee(last_name, email, gender, age)
VALUES ('july', 'july@qq.com', 1, 29);

INSERT INTO mp_employee(last_name, email, gender, age)
VALUES ('jane', 'jane@qq.com', 1, 16);

INSERT INTO mp_employee(last_name, email, gender, age)
VALUES ('black', 'black@qq.com', 0, 25);

INSERT INTO mp_employee(last_name, email, gender, age)
VALUES ('white', 'white@qq.com', 1, 24);

