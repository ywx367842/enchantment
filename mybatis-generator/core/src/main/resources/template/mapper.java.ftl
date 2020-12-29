package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};

/**
 * <p-cesar>
 * ${table.comment!} Mapper 接口
 * </p-cesar>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

}
</#if>
