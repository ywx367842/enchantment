package ${package_name}.g${module}.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${package_name}.g${module}.entity.${entity_name};
import ${package_name}.g${module}.mapper.${entity_name}Mapper;
import ${package_name}.g${module}.service.${entity_name}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ${entity_name}ServiceImpl extends ServiceImpl<${entity_name}Mapper, ${entity_name}> implements ${entity_name}Service {

    /**
     * ${interface_explain}
     * Created by ${author}
     */
    @Autowired
    private ${entity_name}Mapper ${entity_name_toLowerCase}Mapper;

    /*判断json返回类型*/
<#if param_json == "">
    <#if return_json_type == "1">
    @Override
    public List<${entity_name}> ${interface_name}(${param_json}) {

    <#else>
    @Override
    public ${entity_name} ${interface_name}(${param_json}) {

    </#if>

    return ${entity_name_toLowerCase}Mapper.${interface_name}();
    }
}

<#else>
    <#if return_json_type == "1">
    @Override
    public List<${entity_name}> ${interface_name}(${param_json}){
        ConcurrentHashMap map = new ConcurrentHashMap();

        <#list jsonList as key>
       if (${key} != null && ${key} != "") {
            map.put("${key}", ${key});
        }
        </#list>

    <#else>
    @Override
    public ${entity_name} ${interface_name}(${param_json}){
        ConcurrentHashMap map = new ConcurrentHashMap();

        <#list jsonList as key>
       if (${key} != null && ${key} != "") {
            map.put("${key}", ${key});
        }
        </#list>
    </#if>
        return ${entity_name_toLowerCase}Mapper.${interface_name}(map);
    }
}

</#if>
