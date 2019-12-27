package com.movitech.api.util.generate;

import freemarker.template.Template;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName CodeGenerateUtils
 * @Description: 描述：代码生成器
 * @Author: Cesar
 * @Date: 2019/12/26
 **/
@Service
public class CodeGenerateUtils {

    @Value(value = "${spring.datasource.url}")
    private String URL;
    @Value(value = "${spring.datasource.username}")
    private String USER;
    @Value(value = "${spring.datasource.password}")
    private String PASSWORD;
    @Value(value = "${spring.datasource.driver-class-name}")
    private String DRIVER;

    @Value("${generator.author}")
    private String author;
    private final String CURRENT_DATE = getCurrentDate();
    @Value("${generator.diskPath}")
    private String diskPath;
    @Value("${generator.diskPathController}")
    private String diskPathController;
    @Value("${generator.diskPathMapper}")
    private String diskPathMapper;
    @Value(value = "${generator.tableName}")
    private String tableName;
    @Value(value = "${generator.packageName}")
    private String packageName;
    @Value(value = "${generator.packageNameController}")
    private String packageNameController;
    @Value(value = "${generator.tableAnnotation}")
    private String tableAnnotation;
    @Value(value = "${generator.sql}")
    private String sql;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigures() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public Connection getConnection() throws Exception {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }

    public void generate() throws Exception {
        try {
            Connection connection = getConnection();
            Statement stmt = null; //数据库更新操作
            stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); //Statement接口需要通过Connection接口进行实例化操作
            ResultSet resultSet = stmt.executeQuery(sql);

            //生成Model文件
            generateModelFile(resultSet);
            //生成Dao文件
            generateDaoFile(resultSet);
            //生成服务层接口文件
            generateServiceFile(resultSet);
            //生成服务实现层文件
            generateServiceImplFile(resultSet);
            //生成Controller层文件
            generateControllerFile(resultSet);
            //生成Mapper文件
            generateMapperFile(resultSet);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
        }
    }

    private void generateServiceFile(ResultSet resultSet) throws Exception {
        final String suffix = "Service.java";
        final String templateName = "Service.ftl";
        Map<String, Object> dataMap = new HashMap<>();
        resultSet.beforeFirst();
        if (resultSet.next()) {
            do {
                String path = diskPath + "g" + resultSet.getString("module") + "\\service\\" + resultSet.getString("entity_name") + suffix;
                File mapperFile = new File(path);
                File fileParent = mapperFile.getParentFile();
                //判断是否存在
                if (!fileParent.exists()) {
                    //创建父目录文件
                    fileParent.mkdirs();
                }
                dataMap.put("interface_name", resultSet.getString("interface_name"));
                dataMap.put("entity_name", resultSet.getString("entity_name"));
                dataMap.put("interface_explain", resultSet.getString("interface_explain"));
                dataMap.put("module", resultSet.getString("module"));
                String json = resultSet.getString("param_json");
                if (json.isEmpty()) {
                    dataMap.put("param_json", "");
                } else {
                    String jsonStr = jsonToParamString(json);
                    dataMap.put("param_json", jsonStr);
                }
                dataMap.put("author", author);
                dataMap.put("CURRENT_DATE", CURRENT_DATE);
                dataMap.put("return_json_type", resultSet.getString("return_json_type"));
                generateFileByTemplate(templateName, mapperFile, dataMap);
            } while (resultSet.next());
        }
    }

    private void generateMapperFile(ResultSet resultSet) throws Exception {
        final String suffix = "Mapper.xml";
        final String templateName = "Mapper.ftl";
        Map<String, Object> dataMap = new HashMap<>();
        resultSet.beforeFirst();
        if (resultSet.next()) {
            //有的话 先获取第一条记录
            do {
                String path = diskPathMapper + resultSet.getString("entity_name") + suffix;
                File mapperFile = new File(path);
                File fileParent = mapperFile.getParentFile();
                //判断是否存在
                if (!fileParent.exists()) {
                    //创建父目录文件
                    fileParent.mkdirs();
                }
                dataMap.put("sql_txt", resultSet.getString("sql_txt"));
                dataMap.put("interface_name", resultSet.getString("interface_name"));
                dataMap.put("entity_name", resultSet.getString("entity_name"));
                dataMap.put("module", resultSet.getString("module"));
                dataMap.put("interface_explain", resultSet.getString("interface_explain"));
                dataMap.put("packageName", packageName);
                String jsonAnnotation = resultSet.getString("annotation_json");
                Map<String, String> jsonMap = jsonStringToValueMap(jsonAnnotation);
                dataMap.put("jsonMap", jsonMap);
                generateFileByTemplate(templateName, mapperFile, dataMap);
            } while (resultSet.next());
        }
    }

    private void generateModelFile(ResultSet resultSet) throws Exception {
        final String suffix = ".java";
        final String templateName = "Model.ftl";
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, String> jsonMap = new HashMap<String, String>();
        resultSet.beforeFirst();
        if (resultSet.next()) {
            do {
//                String path = diskPath + "model\\"  +  resultSet.getString("entity_name") + suffix;
                String entityPathParent = diskPath + "g" + resultSet.getString("module") + "\\entity\\";
                File director = new File(entityPathParent);
                if (!director.exists()) {
                    director.mkdirs();
                }
                String path = entityPathParent + resultSet.getString("entity_name") + suffix;
                File mapperFile = new File(path);
                dataMap.put("interface_name", resultSet.getString("interface_name"));
                dataMap.put("entity_name", resultSet.getString("entity_name"));
                dataMap.put("interface_explain", resultSet.getString("interface_explain"));
                dataMap.put("module", resultSet.getString("module"));
                dataMap.put("author", author);
//                dataMap.put("CURRENT_DATE",CURRENT_DATE);
                String jsonAnnotation = resultSet.getString("annotation_json");
                jsonMap = jsonStringToValueMap(jsonAnnotation);
                dataMap.put("jsonMap", jsonMap);
                generateFileByTemplate(templateName, mapperFile, dataMap);
            } while (resultSet.next());
        }
    }


    private void generateServiceImplFile(ResultSet resultSet) throws Exception {
        final String suffix = "ServiceImpl.java";
        final String templateName = "ServiceImpl.ftl";
        Map<String, Object> dataMap = new HashMap<>();
        List<String> jsonList = new ArrayList<String>();
        resultSet.beforeFirst();
        if (resultSet.next()) {
            do {
//                String path = diskPath + "service\\impl\\"  + resultSet.getString("entity_name") + suffix;
                String path = diskPath + "g" + resultSet.getString("module") + "\\service\\impl\\" + resultSet.getString("entity_name") + suffix;
                File mapperFile = new File(path);

                File fileParent = mapperFile.getParentFile();
                //判断是否存在
                if (!fileParent.exists()) {
                    //创建父目录文件
                    fileParent.mkdirs();
                }

                dataMap.put("interface_name", resultSet.getString("interface_name"));
                dataMap.put("entity_name", resultSet.getString("entity_name"));
                dataMap.put("interface_explain", resultSet.getString("interface_explain"));
                dataMap.put("module", resultSet.getString("module"));
                dataMap.put("entity_name_toLowerCase", toLowerCaseFirstOne(resultSet.getString("entity_name")));
                String json = resultSet.getString("param_json");
                if (json.isEmpty()) {
                    dataMap.put("param_json", "");
                    jsonList.add("");
                    dataMap.put("jsonList", jsonList);
                } else {
                    String jsonStr = jsonToParamString(json);
                    dataMap.put("param_json", jsonStr);
                    jsonList = jsonStringToKeyList(json);
                    dataMap.put("jsonList", jsonList);
                }

                dataMap.put("author", author);
                dataMap.put("return_json_type", resultSet.getString("return_json_type"));
                generateFileByTemplate(templateName, mapperFile, dataMap);
            } while (resultSet.next());
        }
    }

    private void generateControllerFile(ResultSet resultSet) throws Exception {
        final String suffix = "Controller.java";
        final String templateName = "Controller.ftl";
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, String> jsonMap = new HashMap<String, String>();
        resultSet.beforeFirst();
        if (resultSet.next()) {
            //有的话 先获取第一条记录
            do {
                String path = diskPathController + "controller\\g" + resultSet.getString("module") + "\\" + resultSet.getString("entity_name") + suffix;
                File mapperFile = new File(path);
                File fileParent = mapperFile.getParentFile();
                //判断是否存在
                if (!fileParent.exists()) {
                    //创建父目录文件
                    fileParent.mkdirs();
                }
                dataMap.put("interface_name", resultSet.getString("interface_name"));
                dataMap.put("entity_name", resultSet.getString("entity_name"));
                dataMap.put("entity_name_toLowerCase", toLowerCaseFirstOne(resultSet.getString("entity_name")));
                dataMap.put("theme", resultSet.getString("theme"));
                dataMap.put("module", resultSet.getString("module"));
                dataMap.put("interface_explain", resultSet.getString("interface_explain"));
                String json = resultSet.getString("param_json");
                if (json.isEmpty()) {
                    dataMap.put("param_json", "");
                    dataMap.put("param", "");
                    dataMap.put("jsonMap", "");
                } else {
                    String param_json = jsonToParamStringController(json);
                    dataMap.put("param_json", param_json);
                    String param = jsonToString(json);
                    dataMap.put("param", param);
                    String jsonAnnotation = resultSet.getString("param_json");
                    jsonMap = jsonStringToValueMap(jsonAnnotation);
                    dataMap.put("jsonMap", jsonMap);
                }
                dataMap.put("author", author);
//                dataMap.put("CURRENT_DATE",CURRENT_DATE);
                dataMap.put("return_json_type", resultSet.getString("return_json_type"));
                generateFileByTemplate(templateName, mapperFile, dataMap);
            } while (resultSet.next());
        }
    }


    private void generateDaoFile(ResultSet resultSet) throws Exception {
        final String suffix = "Mapper.java";
        final String templateName = "Dao.ftl";
        Map<String, Object> dataMap = new HashMap<>();
        resultSet.beforeFirst();
        if (resultSet.next()) {
            //有的话 先获取第一条记录
            do {
                String directoryStr = diskPath + "g" + resultSet.getString("module") + "\\mapper\\";
                String path = directoryStr + resultSet.getString("entity_name") + suffix;
                File directory = new File(directoryStr);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                File mapperFile = new File(path);
                dataMap.put("interface_name", resultSet.getString("interface_name"));
                dataMap.put("entity_name", resultSet.getString("entity_name"));
                dataMap.put("entity_name_toLowerCase", toLowerCaseFirstOne(resultSet.getString("entity_name")));
                dataMap.put("interface_explain", resultSet.getString("interface_explain"));
                dataMap.put("module", resultSet.getString("module"));
                dataMap.put("author", author);
//                dataMap.put("CURRENT_DATE",CURRENT_DATE);
                String json = resultSet.getString("param_json");
                dataMap.put("param_json", json);
                dataMap.put("return_json_type", resultSet.getString("return_json_type"));
                generateFileByTemplate(templateName, mapperFile, dataMap);
            } while (resultSet.next());
        }
    }

    /*生成文件模板*/
    private void generateFileByTemplate(final String templateName, File file, Map<String, Object> dataMap) throws Exception {
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
        FileOutputStream fos = new FileOutputStream(file);
        dataMap.put("table_name_small", tableName);
//        dataMap.put("table_name", changeTableName);
        dataMap.put("author", author);
        dataMap.put("date", CURRENT_DATE);
        dataMap.put("package_name", packageName);
        dataMap.put("package_name_controller", packageNameController);
        dataMap.put("table_annotation", tableAnnotation);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
        template.process(dataMap, out);
    }

    /**/
    public String replaceUnderLineAndUpperCase(String str) {
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        int count = sb.indexOf("_");
        while (count != 0) {
            int num = sb.indexOf("_", count);
            count = num + 1;
            if (num != -1) {
                char ss = sb.charAt(count);
                char ia = (char) (ss - 32);
                sb.replace(count, count + 1, ia + "");
            }
        }
        String result = sb.toString().replaceAll("_", "");
        return StringUtils.capitalize(result);
    }

    private static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    /*获取json中的key并返回list数组*/
    private static List<String> jsonStringToKeyList(String rsContent) {
        List<String> list = new ArrayList<String>();
        JSONObject jsonObject = JSONObject.fromObject(rsContent);
        Iterator it = jsonObject.keys();
        while (it.hasNext()) {
            String key = (String) it.next();
            list.add(key);
        }
        return list;
    }

    /*获取json中的value,返回List数组*/
    private static List<String> jsonStringToValueList(String rsContent) {
        List<String> list = new ArrayList<String>();
        JSONObject jsonObject = JSONObject.fromObject(rsContent);
        Iterator it = jsonObject.keys();
        while (it.hasNext()) {
            String key = (String) it.next();
            String value = jsonObject.get(key).toString();
            list.add(value);
        }
        return list;
    }

    /*获取json中的key--value,返回Map数组*/
    private static Map<String, String> jsonStringToValueMap(String rsContent) {
        Map<String, String> jsonMap = new HashMap<String, String>();
        JSONObject jsonObject = JSONObject.fromObject(rsContent);
        Iterator it = jsonObject.keys();
        while (it.hasNext()) {
            String key = (String) it.next();
            String value = jsonObject.get(key).toString();
            jsonMap.put(key, value);
        }
        return jsonMap;
    }

    /*获取json中的key,  */
    private static String jsonToParamString(String rsContent) {
        if (rsContent == null) {
            return rsContent;
        }
        JSONObject jsonObject = JSONObject.fromObject(rsContent);
        Iterator it = jsonObject.keys();

        String str = "";
        while (it.hasNext()) {
            String key = it.next().toString();
//            String  value = jsonObject.get(key).toString();
            str += "String " + key + ", ";
        }
        str = str.substring(0, str.length() - 2);
        return str;
    }

    /*获取json中的key,  */
    private static String jsonToParamStringController(String rsContent) {
        if (rsContent == null) {
            return rsContent;
        }
        JSONObject jsonObject = JSONObject.fromObject(rsContent);
        Iterator it = jsonObject.keys();

        String str = "";
        while (it.hasNext()) {
            String key = it.next().toString();
//            String  value = jsonObject.get(key).toString();
            str += "@RequestParam String " + key + ", ";
        }
        str = str.substring(0, str.length() - 2);
        return str;
    }

    /*获取param_json中的value,  */
    private static String jsonToParamStringAnnotation(String rsContent) {
        System.out.println("rsContent" + rsContent);
        JSONObject jsonObject = JSONObject.fromObject(rsContent);
        System.out.println("jsonObject" + jsonObject);
        Iterator it = jsonObject.keys();

        String str = "";
        while (it.hasNext()) {
            String key = it.next().toString();
            String value = jsonObject.get(key).toString();
            str += "String " + value + ",";
        }
        str = str.substring(0, str.length() - 1);
        return str;
    }

    /*获取json中的key, 为xxxServiceImpl参数提供*/
    private static String jsonToString(String rsContent) {
        JSONObject jsonObject = JSONObject.fromObject(rsContent);
        Iterator it = jsonObject.keys();

        String str = "";
        while (it.hasNext()) {
            String key = it.next().toString();
//            String  value = jsonObject.get(key).toString();
            str += key + ",";
        }
        str = str.substring(0, str.length() - 1);
        return str;
    }

    /*获取当前日期*/
    public String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        String currentDate = sdf.format(date);
        return currentDate;
    }
}

