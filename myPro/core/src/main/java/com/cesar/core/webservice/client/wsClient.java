/*
package com.cesar.core.webservice.client;

import com.alibaba.fastjson.JSON;
import com.movitech.product.cms.webservice.erp1.ContractERP1;
import com.movitech.product.cms.webservice.erp1.entity.ContractBaseERP1;
import com.movitech.product.cms.webservice.erp1.entity.ContractContentERP1;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import javax.xml.namespace.QName;

public class wsClient {

    public static void main(String[] args) {
        //创建动态客户端
        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        Client client = factory.createClient("http://10.6.14.151:8080/ws/cms/erp1?wsdl");
//        Client client = factory.createClient("http://10.6.15.57:50000/dir/wsdl?p=sa/fc28ad89d1253dd8b6d9086b93f3ef51");
//        Client client = factory.createClient("http://lidappdb1.cofco.com:50000/dir/wsdl?p=sa/fc28ad89d1253dd8b6d9086b93f3ef51");

        // 需要密码的情况需要加上用户名和密码
//        client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME,PASS_WORD));
//        client.getInInterceptors().add(new ClientLoginInterceptor("hd_wanglj","123123"));
//        client.getOutInterceptors().add(new ClientLoginInterceptor("hd_wanglj","123123"));
        HTTPConduit conduit = (HTTPConduit) client.getConduit();

        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
        httpClientPolicy.setConnectionTimeout(2000);  //连接超时
        httpClientPolicy.setAllowChunking(false);    //取消块编码
        httpClientPolicy.setReceiveTimeout(120000);     //响应超时
        conduit.setClient(httpClientPolicy);
        //client.getOutInterceptors().addAll(interceptors);//设置拦截器
        try {
            Object[] objects = new Object[0];
            // invoke("方法名",参数1,参数2,参数3....);

            QName qNam = new QName("http://erp1.webservice.cms.product.movitech.com", "contractData");

            ContractContentERP1 content = new ContractContentERP1();
            ContractBaseERP1 base = new ContractBaseERP1();
            base.setACONTNO("123");
            content.setContractBase(base);
            ContractERP1 contractERP1 = new ContractERP1();
            contractERP1.setDataJson(JSON.toJSONString(content));
            contractERP1.setUserId("admin");
            contractERP1.setDataSource("ERP1001");
            objects = client.invoke("contractData", contractERP1);

            System.out.println("返回数据:" + objects[0]);
            for (Object object : objects) {
                System.out.println(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/
