package com.cesar.mphigh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cesar.mphigh.user.dao")
public class MphighApplication {

    public static void main(String[] args) {
        SpringApplication.run(MphighApplication.class, args);
    }

}
