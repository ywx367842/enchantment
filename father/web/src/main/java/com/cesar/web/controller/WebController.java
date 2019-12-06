package com.cesar.web.controller;

import com.cesar.service.ServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName WebTest
 * @Description: TODO
 * @Author movit
 * @Date 2019/12/2
 * @Version V1.0
 **/

@RestController
@RequestMapping("test")
public class WebController {

//    @Autowired
//    private ServiceTest
    @GetMapping("showAll")
    public String showAll(){
        ServiceTest serviceTest = new ServiceTest();
        return serviceTest.showEntity()+"我是web";
    }
}
