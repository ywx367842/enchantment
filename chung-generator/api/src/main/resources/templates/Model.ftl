package ${package_name}.g${module}.entity;

import lombok.Data;

/**
 * ${interface_explain}
 * Created by ${author}
 */

@Data
public class ${entity_name} {
<#list jsonMap?keys as key>

    /**
     * ${jsonMap[key]}
     */
    private String ${key};
</#list>

}
