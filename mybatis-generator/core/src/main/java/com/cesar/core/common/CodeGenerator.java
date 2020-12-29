package com.cesar.core.common;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.*;

// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * 数据库：dataSource strategy
     * <p>
     * 包名模板：packageInfo template
     * <p>
     * 自定义输出：injectionConfig
     * <p>
     * 全局配置：globalConfig
     * 文件输出规则、顺序优先级: InjectionConfig.FileOutConfig(模板，路径) > PackageConfig.PathInfo(or 默认)+ TemplateConfig
     *
     * @param args
     */

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        String projectPath = System.getProperty("user.dir");


        //1 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/shinsun?useSSL=false&serverTimezone=GMT%2B8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbQuery(new MySqlQuery() {
            @Override
            public String tableFieldsSql() {
                return "show full fields from `%s`\n";
//                       + "where field = 'clause_name'";
            }

            /*@Override
            public String[] fieldCustom() {
                return new String[]{"Field","NULL", "privileges"};
            }*/
        });
//        dsc.setDbType(DbType.MYSQL);
//        dsc.setSchemaName()
        dsc.setTypeConvert(new ITypeConvert() {
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {

                String t = fieldType.toLowerCase();
                if (t.contains("datetime")) {
                    return DbColumnType.DATE;
                }
                //其它字段采用默认转换（非mysql数据库可以使用其它默认的数据库转换器）
                return new MySqlTypeConvert().processTypeConvert(globalConfig, fieldType);
            }
        });

        mpg.setDataSource(dsc);

        //2 数据库表策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass("com.cesar.core.common.CommonEntity");
//        strategy.setSuperEntityColumns(new String[]{"id", "createdDate", "createdBy", "updatedDate", "updatedBy", "version", "delFlag"});

        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);

        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id", "created_date", "created_by", "updated_date", "updated_by", "version", "del_flag");
        // 公共父类
//        strategy.setSuperControllerClass("com.cesar.core.common.CommonEntity");
        strategy.setControllerMappingHyphenStyle(true);

        //表
        strategy.setInclude("cms_clause");
//        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setTablePrefix("cms_");

        strategy.setCapitalMode(true);
        strategy.setSkipView(true);

//        strategy.setExclude();
        /*TableFill tf = new TableFill("remark", FieldFill.INSERT);
        List<TableFill> tfs = new ArrayList<>();
        tfs.add(tf);
        strategy.setTableFillList(tfs);*/

//        strategy.setChainModel(false);
//        strategy.setTableFillList()
        mpg.setStrategy(strategy);


        //3 包配置  import parent.modelName.componentName
        //默认路径 OutputDir + parent + modelName + componentName
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.cesar");
        String modelName = scanner("模块名");
        pc.setModuleName(modelName);
        pc.setEntity("entity");
//        pc.setXml("/core/src/main/resources");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");

        //设置自定义输出目录（分布式项目使用）
        Map<String, String> pathInfo = new HashMap<>();

        String parentdir = "/com/cesar/";
//        String xmldir = projectPath + "/core/src/main/resources/mapper/" + modelName;
        String controllerdir = projectPath + "/api-demo/src/main/java" + parentdir + modelName + "/controller";

        String pojodir = projectPath + "/core/src/main/java" + parentdir + modelName + "/entity";
        String servicedir = projectPath + "/core/src/main/java" + parentdir + modelName + "/service";
        String serviceImpldir = projectPath + "/core/src/main/java" + parentdir + modelName + "/service/impl";
        String mapperdir = projectPath + "/core/src/main/java" + parentdir + modelName + "/mapper";

        pathInfo.put(ConstVal.CONTROLLER_PATH, controllerdir);
        pathInfo.put(ConstVal.SERVICE_PATH, servicedir);
        pathInfo.put(ConstVal.SERVICE_IMPL_PATH, serviceImpldir);
        pathInfo.put(ConstVal.ENTITY_PATH, pojodir);
        pathInfo.put(ConstVal.MAPPER_PATH, mapperdir);
//        pathInfo.put(ConstVal.XML_PATH, xmldir);
        pc.setPathInfo(pathInfo);
        mpg.setPackageInfo(pc);

        //4 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setController("template2/controller.java");
        templateConfig.setService("template/service.java");
        templateConfig.setServiceImpl("template/serviceImpl.java");
        //没设置的默认取mp包中的模板
//        templateConfig.setMapper("template/mapper.java");
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);


        //5 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        String controllerPath = "/template/controller.java.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";


        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        FileOutConfig fileOutConfig = new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
//                System.out.println(JSON.toJSONString(tableInfo));
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/core/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        };
        focList.add(fileOutConfig);
        /*FileOutConfig fileOutConfig2 = new FileOutConfig(controllerPath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return controllerdir
                        + "/" + tableInfo.getEntityName() + "Controller" + StringPool.DOT_JAVA;
            }
        };
        focList.add(fileOutConfig2);*/
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        //6 全局配置
        GlobalConfig gc = new GlobalConfig();
        System.out.println(projectPath);
        //包配置中pathinfo生效时不使用outputDir
        gc.setOutputDir(projectPath + "/core/src/main/java/default1");
        gc.setAuthor("cesar");
        //生成后是否打开目录
        gc.setOpen(false);

        //todo 重要，不要轻易覆盖
//        gc.setFileOverride(true);

        //实体属性 Swagger2 注解
//         gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}