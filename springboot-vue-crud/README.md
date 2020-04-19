# 项目简介

该项目是在学习了SpringBoot的简单入门和SpringBoot整合Mybatis、Mybatis-Plus插件之后的练手项目

项目实现了用户登录、用户注册；商品的增删改查，商品照片的上传、回显和展示

项目的技术栈：

* 后端：SpringBoot、Mybatis、Mybatis-Plus
* 前端： Vue + ElementUI



# 准备

## 数据库

创建数据库`springboot-vue-crud`，执行`db`文件夹之下的sql文件创建出数据库和测试数据



##  导入依赖

```xml
 <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <!--mybatis-plus插件-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.3.1</version>
        </dependency>
        <!--mysql连接-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <!--spring aop支持-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
        <!--阿里巴巴druid连接池-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.13</version>
        </dependency>
        <!--开发热部署插件-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
        </dependency>
        <!--引入Thymeleaf模板-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
    </dependencies>
```



## 配置

`application.yml`中添加目前需要的配置

- 服务器端口、项目路径设置
- 数据源设置
- thymeleaf模板设置
- Mybatis-Plus插件全局配置

```yaml
server:
  port: 8080
  servlet:
    context-path: /simple-shop

spring:
  datasource:
    # 基本配置
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: root
    url: jdbc:mysql://127.0.0.1:3306/springboot-vue-crud?characterEncoding=utf8&useUnicode=true
    
  # 模板设置
  thymeleaf:
    prefix: classpath:/templates/
    check-template-location: true
    suffix: .html
    encoding: utf-8
    mode: HTML
    cache: false
    
# mybatis-plus插件配置
mybatis-plus:
  # 输出sql语句
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  mapper-locations: classpath:mapper/**/*.xml
  global-config:
    db-config:
      id-type: auto
      table-prefix: tb_
```



## 代码生成

代码生成是复用上一篇学习Mybatis-Plus那时候的代码，略微做出改动即可使用



## 统一封装返回的数据

项目返回的json数据全部固定为指定格式：

```json
{
    "code":200,
    "msg":请求成功
    data:{}
}
```

在`com.hitopo.common`中创建出统一管理返回码和返回消息的枚举类`ResultEnum.java`

```java
public enum ResultEnum {

    SUCCESS(200, "请求成功"),
    CREATED(201, "创建成功"),
    DELETED(204, "删除成功"),
    BAD_REQUEST(400, "请求地址不存在或者包含不支持的参数"),
    INNER_ERROR(500, "服务器错误");


    /**
     * 状态码
     */
    private Integer code;
    /**
     * 状态参数
     */
    private String msg;


    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
```

为了封装统一的返回值样式，创建出`R.java`固定格式

```java
public class R {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 状态数据
     */
    private String msg;
    /**
     * 数据
     */
    private Object data;

    public R() {
    }

    public R(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public R(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public static R success(ResultEnum re) {
        return new R(re.getCode(), re.getMsg());
    }

    public static R success(ResultEnum re, Object data) {
        return new R(re.getCode(), re.getMsg(), data);
    }

    public static R error(ResultEnum re) {
        return new R(re.getCode(), re.getMsg());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
```







