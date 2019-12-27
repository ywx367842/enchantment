
package com.cesar.api.config;

import com.cesar.core.webservice.service.MyWebService;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.xml.ws.Endpoint;

//@Configuration
public class CxfCustomConfig {

//    @Bean
//    public ServletRegistrationBean dispatcherServlet() {
//        return new ServletRegistrationBean(new CXFServlet(), "/soap/*");
//    }

    /**
     * 添加普通的controller处理
     *
     * @return
     */
    /*@Bean
    public ServletRegistrationBean dispatcherRestServlet() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        //替换成自己的controller包路径
        context.scan("com.cesar.api.controller");
        DispatcherServlet disp = new DispatcherServlet(context);
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(disp);
        registrationBean.setLoadOnStartup(1);
        //映射路径自定义,必须设置一个不重复的名称
        registrationBean.addUrlMappings("/*");
        registrationBean.setName("rest");
        return registrationBean;
    }*/
    /*@Bean(name = "multipartResolver")
    public CommonsMultipartResolver getCommonsMultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");
        multipartResolver.setResolveLazily(true);
        //        multipartResolver.setMaxUploadSize(104857600);   //104857600等于100M
        //        multipartResolver.setMaxInMemorySize(40960);
        multipartResolver.setMaxUploadSize(104857600);//104857600等于100M
        multipartResolver.setMaxInMemorySize(4096);
        return multipartResolver;
    }*/

//    @Autowired
//    private Bus bus;

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Autowired
    private MyWebService myWebService;
//    @Bean
//    public MyWebService myWebService() {
//        return new MyWebServiceImpl();
//    }

    @Bean
    public Endpoint endpoint1() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), myWebService);
        endpoint.publish("/webService");
        return endpoint;
    }

}
