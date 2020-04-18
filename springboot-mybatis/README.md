# Spring Boot整合Mybatis

## 准备

### 准备数据表

```sql
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
```



### 引入依赖

```xml
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.1.2</version>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```



### 配置参数

在application.yml中配置相关信息

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: root
    url: jdbc:mysql://127.0.0.1/springboot-mybatis?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
```

需要注意的是`driver-class-name`新版的写法是`com.mysql.cj.jdbc.Driver`，而且在url中除了指定`useUnicode`和`charavterEncoding`之外，在SpringBoot2.0之后还必须指定`serverTimeZone`，否则会报错



## 程序中使用

**基本配置**

之前在SSM开发时，会在`MapperScannerConfigurer`中配置：`<property name="basePackage" value="xxx.mapper"/>`用于使用Mybatis的接口代理开发模式（且接口和XML需要名称相同）。那么在SpringBoot整合Mybatis中也要有对应的配置：

* 方式一：在每个interface Mapper前添加`@Mapper`注解

*  方式二：在`Application.java`启动类前添加`@MapperScan("cn.tycoding.mapper")`注解

推荐使用第二种，这种包扫描的方式简化了在每个Mapper类中去配置注解



### 注解的方式

Mybatis提供了一些注解实现快速CRUD，比如：`@Select`,`@Update`,`@Insert`,`@Delete`



#### CRUD

创建Entity实体类

`com.hitopo.entity`中创建User实体类`User.java`

```java
@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private LocalDateTime createTime;
}
```

创建注解版的Mapper

`com.hitopo.mapper`中创建`UserMapperAno.java`

```java
public interface UserMapperAno {
    @Select("SELECT * FROM user")
    @Results({
            @Result(property = "createTime", column = "create_time")
    })
    List<User> findAll();

    @Select("SELECT * FROM user WHERE id = #{id}")
    @Results({
            @Result(property = "createTime", column = "create_time")
    })
    User findOneById(Long id);

    @Insert("INSERT INTO user(username, password, create_time) VALUES (#{username},#{password},#{createTime})")
    void save(User user);

    @Update("UPDATE user SET username=#{username},password=#{password} WHERE id = #{id}")
    void update(User user);

    @Delete("DELETE FROM  user WHERE id = #{id}")
    void delete(Long id);
}
```

当数据库中的字段和Java中的字段的名称不同时，使用`@Result`来指定如何绑定属性



#### 测试

编写测试类`test/com/hitopo/mapper/UserMapperAnoTest.java`

```java
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperAnoTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapperAno userMapperAno;

    @Test
    public void testFindAll() {
        List<User> userList = userMapperAno.findAll();
        userList.forEach(user -> logger.info("userList={}", user));
    }

    @Test
    public void testFindOneById() {
        User user = userMapperAno.findOneById(9L);
        logger.info("user={}", user);
    }

    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("测试用户");
        user.setPassword("123");
        user.setCreateTime(LocalDate.of(2010, 11, 23));
        userMapperAno.save(user);
        testFindAll();
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(8L);
        user.setUsername("测试用户2");
        user.setPassword("123444");
        user.setCreateTime(LocalDate.of(2010, 11, 23));
        userMapperAno.update(user);
        testFindAll();
    }

    @Test
    public void testDelete(){
        userMapperAno.delete(7L);
        testFindAll();
    }
}
```



### XML配置文件的方式

基于XML配置文件的方式和基本相同，和注解版最大的不同就是Dao层，XML版会自动根据Dao层接口的方法名自动映射到XML中同名`id`对应的SQL



#### 配置修改

在`application.yml`配置文件中添加Mybatis的配置

```yaml
#mybatis配置
mybatis:
  mapper-locations: classpath:mapper/**/*.xml
  type-aliases-package: cn.tycoding.entity
  configuration:
    # 使用jdbc的getGeneratedKeys 可以获取数据库自增主键值
    use-generated-keys: true
    # 开启驼峰命名转换，如：Table(create_time) -> Entity(createTime)。不需要我们关心怎么进行字段匹配，mybatis会自动识别`大写字母与下划线`
    map-underscore-to-camel-case: true
```



#### CRUD

`com.hitopo.mapper`包内创建`UserMapperXML.java`

```java
public interface UserMapper {

    List<User> findAll();

    User findOneById(Long id);

    void save(User user);

    void update(User user);

    void delete(Long id);
}
```

在配置文件指定的mapper配置文件目录`resources/mapper`中创建`UserMapper.xml`文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.hitopo.mapper.UserMapperXML">

    <select id="findAll" resultType="com.hitopo.entity.User">
        SELECT *
        FROM user
    </select>

    <select id="findOneById" resultType="com.hitopo.entity.User">
        SELECT *
        FROM user
        WHERE id = #{id}
    </select>

    <insert id="save" parameterType="com.hitopo.entity.User">
        INSERT INTO user(username, password, create_time)
        VALUES (#{username}, #{password}, #{createTime})
    </insert>

    <update id="update" parameterType="com.hitopo.entity.User">
        UPDATE user
        SET username=#{username},
            password=#{password}
        WHERE id = #{id}
    </update>

    <delete id="delete" parameterType="long">
        DELETE
        FROM user
        WHERE id = #{id}
    </delete>

</mapper>
```



#### 测试

```java
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperXMLTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapperXML userMapperXML;

    @Test
    public void testFindAll() {
        List<User> userList = userMapperXML.findAll();
        userList.forEach(user -> logger.info("userList={}", user));
    }

    @Test
    public void testFindOneById() {
        User user = userMapperXML.findOneById(9L);
        logger.info("user={}", user);
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("测试用户");
        user.setPassword("123");
        user.setCreateTime(LocalDate.of(2010, 11, 23));
        userMapperXML.save(user);
        testFindAll();
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(8L);
        user.setUsername("测试用户2");
        user.setPassword("123444");
        user.setCreateTime(LocalDate.of(2010, 11, 23));
        userMapperXML.update(user);
        testFindAll();
    }

    @Test
    public void testDelete() {
        userMapperXML.delete(7L);
        testFindAll();
    }

}
```



## 结束语

Mybatis的基于注解和基于XML文件的配置方式集成到SpringBoot中都很简单，至于选择哪个方法比较好，我认为，简单的CRUD操作可以直接用注解生成，简单快捷。而复杂的动态sql和多表联结查询之类的最好放在XML文件中，方便统一管理

