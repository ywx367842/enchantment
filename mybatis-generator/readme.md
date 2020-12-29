官方文档地址： <https://baomidou.com/guide/generator.html>



遇到问题记录：

1 Application.java上的注解 MapperScan不能包含iservice

@Autowired

自动注入会found two



2 生成文件配置和部分规则

数据库：dataSource strategy
<p>
包名模板：packageInfo template
<p>
自定义输出：injectionConfig
<p>
全局配置：globalConfig
文件输出规则、顺序优先级: InjectionConfig.FileOutConfig(模板，路径) > PackageConfig.PathInfo(or 默认)+ TemplateConfig