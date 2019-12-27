package ${package_name_controller}.controller.g${module};

import com.movitech.api.util.common.Response;
import ${package_name}.g${module}.entity.${entity_name};
import ${package_name}.g${module}.service.${entity_name}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

<#if param_json == "">
@RestController
@Api(value = "${interface_explain}", description = "${interface_explain}")
@RequestMapping(value="/${theme}/${module}/")
public class ${entity_name}Controller {

    @Autowired
    private ${entity_name}Service ${entity_name_toLowerCase}Service;

    /**
     * ${interface_explain}
     * Created by ${author}
     */
	@ApiOperation(value = "${interface_explain}", notes = "${interface_explain}")
    @GetMapping("/${interface_name}")
    public Response ${interface_name}(${param_json}) {

        <#if return_json_type == "1">
              List<${entity_name}> ${entity_name_toLowerCase} = ${entity_name_toLowerCase}Service.${interface_name}(${param});
        <#else>
              ${entity_name} ${entity_name_toLowerCase} = ${entity_name_toLowerCase}Service.${interface_name}(${param});
        </#if>

        return Response.succeed(${entity_name_toLowerCase});
    }
}
<#else>
@RestController
@Api(value = "${interface_explain}", description = "${interface_explain}")
@RequestMapping(value="/${theme}/${module}/")
public class ${entity_name}Controller {

    @Autowired
    private ${entity_name}Service ${entity_name_toLowerCase}Service;

    /**
     * ${interface_explain}
     * Created by ${author}
     */
	@ApiOperation(value = "${interface_explain}", notes = "${interface_explain}")
    @GetMapping("/${interface_name}")
    public Response ${interface_name}(${param_json}) {

    <#if return_json_type == "1">
              List<${entity_name}> ${entity_name_toLowerCase} = ${entity_name_toLowerCase}Service.${interface_name}(${param});
    <#else>
        ${entity_name} ${entity_name_toLowerCase} = ${entity_name_toLowerCase}Service.${interface_name}(${param});
    </#if>

    return Response.succeed(${entity_name_toLowerCase});
    }
}
</#if>