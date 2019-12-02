package com.cesar.web;

import com.cesar.service.ServiceTest;
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
public class WebTest {

    @GetMapping("showAll")
    public String showAll(){
        ServiceTest serviceTest = new ServiceTest();
        return serviceTest.showEntity()+"我是web";
    }
}
