package com.cesar.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.cesar")
//@MapperScan("com.cesar.core.*.dao")
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
//        Endpoint.publish("http://localhost:9999/webService/userService/", new CommonServiceImpl());
    }

}
