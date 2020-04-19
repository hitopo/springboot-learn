package com.hitopo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hitopo.mapper")
public class SpringbootVueCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootVueCrudApplication.class, args);
    }

}
