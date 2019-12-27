package com.cesar.api.generator;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Connector
 * @Description: TODO
 * @Author movit
 * @Date 2019/12/25
 * @Version V1.0
 **/
public class Connector {
    public static void main(String[] args) throws Exception {
        Connector conn = new Connector();
        Connection connection = conn.getConnection();
        Statement stmt = null; //数据库更新操作
        stmt=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY); //Statement接口需要通过Connection接口进行实例化操作

        String sql = "select * from dim_java_connector where interface_name = 'getPublicImpProjectList'";
        ResultSet resultSet=stmt.executeQuery(sql);
        resultSet.beforeFirst();
        if (resultSet.next()) {
            System.out.println(resultSet.getString("interface_name"));

            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("interface_name",resultSet.getString("interface_name"));
            dataMap.put("entity_name",resultSet.getString("entity_name"));
            dataMap.put("entity_name_toLowerCase",toLowerCaseFirstOne(resultSet.getString("entity_name")));
            dataMap.put("interface_explain",resultSet.getString("interface_explain"));
        }

    }

    private  static String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }


    public Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String URL = "jdbc:sqlserver://58.210.98.36:31433;databaseName=HNA_BIM_DEV";
        String USER = "sa";
        String PASSWORD = "YZ-it418";
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }
}
