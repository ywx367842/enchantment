<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!-- 配置dao接口路径-->
<mapper namespace="${package_name}.g${module}.mapper.${entity_name}Mapper">

    <resultMap id="${entity_name}Map" type="${entity_name}">
    <#list jsonMap?keys as key>
        <result column="${key}" property="${key}" jdbcType="VARCHAR"/>
    </#list>
    </resultMap>

    <!--${interface_explain}-->
    <select id="${interface_name}" resultMap="${entity_name}Map" parameterType="java.util.Map">
        ${sql_txt}
    </select>

</mapper>



