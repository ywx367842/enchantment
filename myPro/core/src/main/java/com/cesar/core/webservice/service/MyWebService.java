package com.cesar.core.webservice.service;

import com.cesar.core.webservice.entity.MyWebEntity;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * 文件类型说明：
 * 初始作者：Jason.Cong
 * 创建日期：2019/10/14 18:42
 * 功能说明：
 */
@WebService(name = "myWebService", // 暴露服务名称
        targetNamespace = "http://service.webservice.core.cesar.com"// 命名空间,一般是接口的包名倒序
)
public interface MyWebService {
    @WebMethod
    public String webMethod(MyWebEntity myWebEntity);

}
