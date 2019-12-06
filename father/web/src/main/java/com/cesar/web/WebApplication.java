package com.cesar.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.cesar")
@MapperScan("com.cesar.dao.mapper")
public class WebApplication {

    public static void main(String[] args) {
        
        SpringApplication.run(WebApplication.class, args);
    }

}
