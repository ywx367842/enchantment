package ${package_name}.g${module}.service;

import ${package_name}.g${module}.entity.${entity_name};

import java.util.List;

public interface ${entity_name}Service {

    /**
     * ${interface_explain}
     * Created by ${author}
     */
    <#if return_json_type == "1">
    List<${entity_name}> ${interface_name}(${param_json});
    <#else>
    ${entity_name} ${interface_name}(${param_json});
    </#if>

}