/*
package com.cesar.core.webservice.client;

import com.movitech.product.cms.common.utils.xml.XmlUtil;
import com.movitech.product.cms.contract.main.entity.ContractMain;
import com.movitech.product.cms.syslog.entity.ProcLog;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jdom.Document;

import java.nio.charset.Charset;


public class HttpClientCallSoapUtil {
    static int socketTimeout = 30000;// 请求超时时间
    static int connectTimeout = 30000;// 传输超时时间


    */
/**
     * 使用SOAP1.1发送消息
     *
     * @param postUrl
     * @param soapXml
     * @param soapAction
     * @return
     *//*

    public static String doPostSoap1_1(String postUrl, String soapXml,
                                       String soapAction) {
        String retStr = "";
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(postUrl);
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout).build();
        httpPost.setConfig(requestConfig);
        try {
            httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
            httpPost.setHeader("SOAPAction", soapAction);
            StringEntity data = new StringEntity(soapXml,
                    Charset.forName("UTF-8"));
            httpPost.setEntity(data);
            CloseableHttpResponse response = closeableHttpClient
                    .execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                // 打印响应内容
                retStr = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.println("response:" + retStr);
            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            System.out.println("exception in doPostSoap1_1" + e);
        }
        return retStr;
    }


    */
/**
     * 使用SOAP1.2发送消息
     *
     * @param postUrl
     * @param soapXml
     * @param soapAction
     * @return
     *//*

    public static String doPostSoap1_2(String postUrl, String soapXml,
                                       String soapAction) {
        String retStr = "";
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(postUrl);
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout).build();
        httpPost.setConfig(requestConfig);
        try {
            httpPost.setHeader("Content-Type",
                    "application/soap+xml;charset=UTF-8");
            httpPost.setHeader("SOAPAction", soapAction);
            StringEntity data = new StringEntity(soapXml,
                    Charset.forName("UTF-8"));
            httpPost.setEntity(data);
            CloseableHttpResponse response = closeableHttpClient
                    .execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                // 打印响应内容
                retStr = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.println("response:" + retStr);
            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            System.out.println("exception in doPostSoap1_2" + e);
        }
        return retStr;
    }


    public static String doPostSoap1_3(String postUrl, String soapXml, String token, ProcLog erpProcLog) {

        String retStr = "";
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(postUrl);
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout).build();
        httpPost.setConfig(requestConfig);
        try {
            httpPost.setHeader("Content-Type",
                    "application/xml");
            httpPost.setHeader("Authorization", token);
            StringEntity data = new StringEntity(soapXml,
                    Charset.forName("UTF-8"));
            httpPost.setEntity(data);
            CloseableHttpResponse response = closeableHttpClient
                    .execute(httpPost);
            HttpEntity httpEntity = response.getEntity();

            StatusLine statusLine = response.getStatusLine();
            if (statusLine == null) {
                return null;
            } else {
                int statusCode = statusLine.getStatusCode();
                erpProcLog.setStatus(Integer.toString(statusCode));
                System.out.println("响应码statusCode:" + statusCode);
                if (statusCode != 200) {
                    return null;
                }
            }
            if (httpEntity != null) {
                // 打印响应内容
                retStr = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.println("response:" + retStr);
            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            System.out.println("exception in doPostSoap1_3" + e);
        }
        return retStr;
    }


    private String erpApproveRequestXml(String organizationId, String approvalType, String resultCode, String documentId, String documentNature) {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:v1=\"http://interface.hzero.org/v1/\">  \n" +
                "  <soapenv:Header/>  \n" +
                "  <soapenv:Body> \n" +
                "    <v1:invoke> \n" +
                "      <interfaceCode>gjoy-ims.contract-history.submitApproveCallback</interfaceCode>  \n" +
                "      <serverCode>EIMS_CONTRACT_CALLBACKE</serverCode>\n" +
                "      <namespace>HZERO</namespace>  \n" +
                "      <payload> \n" +
                "        <pathVariableMap> \n" +
                "          <dataEntryList key=\"organizationId\">" + organizationId + "</dataEntryList> \n" +
                "        </pathVariableMap>  \n" +
                "        <requestParamMap> \n" +
                "          <dataEntryList key=\"approvalType\">" + approvalType + "</dataEntryList>  \n" +
                "          <dataEntryList key=\"resultCode\">" + resultCode + "</dataEntryList>  \n" +
                "          <dataEntryList key=\"documentId\">" + documentId + "</dataEntryList> \n" +
                "          <dataEntryList key=\"documentNature\">" + documentNature + "</dataEntryList> \n" +
                "        </requestParamMap>  \n" +
                "        <mediaType>application/json</mediaType> \n" +
                "      </payload> \n" +
                "    </v1:invoke> \n" +
                "  </soapenv:Body> \n" +
                "</soapenv:Envelope>\n";
        return xml;
    }

    public static void main(String[] args) throws Exception {
        String token = "5292b536-6ba3-483e-baba-4a1881afdcfb";
        ContractMain contractMain = new ContractMain();

        String postUrl = "http://10.6.14.41:8080/hitf/v1/soap/invoke?wsdl";
        //SOAPAction
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:v1=\"http://interface.hzero.org/v1/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <v1:invoke>\n" +
                "        <interfaceCode>gjoy-ims.contract-history.submitApproveCallback</interfaceCode>\n" +
                "    \t<serverCode>EIMS_CONTRACT_CALLBACKE</serverCode>\n" +
                "    \t<namespace>HZERO</namespace>\n" +
                "        <payload>\n" +
                "    \t\t<pathVariableMap>\n" +
                "    \t\t\t<dataEntryList key=\"organizationId\">0</dataEntryList>\n" +
                "    \t\t</pathVariableMap>\n" +
                "            \t<requestParamMap>\n" +
                "    \t\t\t<dataEntryList key=\"approvalType\">0</dataEntryList>\n" +
                "    \t\t\t\t<dataEntryList key=\"resultCode\">0</dataEntryList>\n" +
                "    \t\t\t\t\t<dataEntryList key=\"documentId\">0</dataEntryList>\n" +
                "    \t\t</requestParamMap>\n" +
                "    \t\t<mediaType>application/json</mediaType>\n" +
                "    \t</payload>\n" +
                "      </v1:invoke>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        String res = doPostSoap1_3(postUrl, xml, token, new ProcLog());
        Document document = XmlUtil.strXmlToDocument(res);
        String payload = XmlUtil.getValueByElementName(document, "payload");
        System.out.println(payload);
    }
}*/
