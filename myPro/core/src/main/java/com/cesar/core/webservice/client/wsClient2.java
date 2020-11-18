/*
package com.cesar.core.webservice.client;

import com.movitech.product.cms.webservice.client.entity.Erp1StatusSync;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import javax.xml.namespace.QName;
import java.util.*;

public class wsClient2 {

    public static void main(String[] args) {
//        SiGetCrossBordTrackOutbService
        //创建动态客户端
        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
//        Client client = factory.createClient("http://10.6.14.151:8080/ws/cms/erp1?wsdl");
        Client client = factory.createClient("http://10.6.15.57:50000/dir/wsdl?p=sa/fc28ad89d1253dd8b6d9086b93f3ef51");
//        Client client = factory.createClient("http://lidappdb1.cofco.com:50000/dir/wsdl?p=sa/fc28ad89d1253dd8b6d9086b93f3ef51");

//        BindingProvider bp = (BindingProvider) stub;
        Map<String, Object> context = client.getRequestContext();
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        String str = "hd_wanglj:123123";
        byte[] basic = Base64.getEncoder().encode(str.getBytes());

        String basicStr = "Basic aGRfd2FuZ2xqOjEyMzEyMw==";
        headers.put("Authorization", Arrays.asList(basicStr));
        context.put(Message.PROTOCOL_HEADERS, headers);
        context.put("Authorization",basicStr);

//        context.put(BindingProvider.USERNAME_PROPERTY, "hd_wanglj");
//        context.put(BindingProvider.PASSWORD_PROPERTY, "123123");
//        Endpoint endpoint = client.getEndpoint();
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

        AuthorizationPolicy authorizationPolicy = new AuthorizationPolicy();
        authorizationPolicy.setUserName("hd_wanglj");
        authorizationPolicy.setPassword("123123");
        authorizationPolicy.setAuthorizationType("Basic");
        conduit.setAuthorization(authorizationPolicy);

        //client.getOutInterceptors().addAll(interceptors);//设置拦截器
        try {
            Object[] objects = new Object[0];
            // invoke("方法名",参数1,参数2,参数3....);

            */
/*QName qNam = new QName("http://erp1.webservice.cms.product.movitech.com", "contractData");
            ContractContentERP1 content = new ContractContentERP1();
            ContractBaseERP1 base = new ContractBaseERP1();
            base.setACONTNO("123");
            content.setContractBase(base);

            ContractERP1 contractERP1 = new ContractERP1();
            contractERP1.setDataJson(JSON.toJSONString(content));
            contractERP1.setUserId("admin");
            contractERP1.setDataSource("ERP1001");
            objects = client.invoke("contractData", contractERP1);*//*


            Erp1StatusSync statusSync = new Erp1StatusSync();
            statusSync.setMETHODTYPE("01");
            String datajson = "{\"CONTRACTNO\":\"1000000000000007\",\"APPSTATUS\":\"40\",\"APPDATE\":\"20200219\",\"ACONTNO\":\"YFL-20150619110\",\"APPLOGLINK\":\"http://www.hao123.com\",\"ATTLINK\":\"http://www.hao123.com\",\"RETURN_TYPE\":\"01\"}";
            statusSync.setDATAJSON(datajson);
            QName qNam = new QName("urn:cofcogroup.com:I_ECC:CMS", "SI_SOAP2RFC_015_OUT");
            objects = client.invoke(qNam, statusSync);


            System.out.println("返回数据:" + objects[0]);
            for (Object object : objects) {
                System.out.println(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    */
/**
     * 根据给定的参数执行接口程序
     *
     * @param request
     * @return
     *//*

    */
/*public ZPILOT01SSOCMMIF001CONResponse executeService(ZPILOT01SSOCMMIF001CON request) throws Exception {

// 得到本地服务类
        MIZPILOT01SSOCMMIF001CONOutSynService service = new MIZPILOT01SSOCMMIF001CONOutSynService();

// 得到接口的代理
        MIZPILOT01SSOCMMIF001CONOutSyn stub = service.getMIZPILOT01SSOCMMIF001CONOutSynPort();

// 设置访问接口服务器的用户名和密码
        BindingProvider bp = (BindingProvider) stub;
        Map<String, Object> context = bp.getRequestContext();
        context.put(BindingProvider.USERNAME_PROPERTY, XI_USER_NAME);
        context.put(BindingProvider.PASSWORD_PROPERTY, XI_PASSWORD);

// 执行接口
        ZPILOT01SSOCMMIF001CONResponse response = stub.miZPILOT01SSOCMMIF001CONOutSyn(request);

// 返回响应
        return response;
    }*//*

}*/
