package com.demo.demoapply.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName DemoController
 * @Description: TODO
 * @Author movit
 * @Date 2020/12/23
 * @Version V1.0
 **/
@RestController
@RequestMapping("demo")
public class DemoController {

    @GetMapping("test")
    public String test() {
        return "helle demo";
    }
}
