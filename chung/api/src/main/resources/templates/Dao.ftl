package ${package_name}.g${module}.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${package_name}.g${module}.entity.${entity_name};

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

<#if param_json == "">
public interface ${entity_name}Mapper extends BaseMapper<${entity_name}>{

    /**
     * ${interface_explain}
     * Created by ${author}
     */
<#if return_json_type == "1">
    List<${entity_name}> ${interface_name}();
<#else>
    ${entity_name} ${interface_name}();
</#if>

}
<#else>
public interface ${entity_name}Mapper extends BaseMapper<${entity_name}>{

    /**
     * ${interface_explain}
     * Created by ${author}
     */
<#if return_json_type == "1">
    List<${entity_name}> ${interface_name}(ConcurrentHashMap map);
<#else>
    ${entity_name} ${interface_name}(ConcurrentHashMap map);
</#if>

}
</#if>