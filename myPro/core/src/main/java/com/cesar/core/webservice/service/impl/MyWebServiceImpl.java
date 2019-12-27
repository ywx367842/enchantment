package com.cesar.core.webservice.service.impl;

import com.cesar.core.webservice.entity.MyWebEntity;
import com.cesar.core.webservice.service.MyWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jws.WebService;


/**
 * @ClassName MyWebServiceImpl
 * @Description: TODO
 * @Author: Cesar
 * @Date: 2019/12/18
 **/
@WebService(serviceName = "myWebService", // 与接口中指定的name一致
        targetNamespace = "http://service.webservice.core.cesar.com", // 与接口中的命名空间一致,一般是接口的包名倒
        endpointInterface = "com.cesar.core.webservice.service.MyWebService"// 接口地址
)
//@Component
public class MyWebServiceImpl implements MyWebService {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String webMethod(MyWebEntity myWebEntity) {
        System.out.println(myWebEntity.getUsername());
        System.out.println(myWebEntity.getPassword());
        return "OK";
    }

}
