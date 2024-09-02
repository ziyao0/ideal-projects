<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${mapperName}">
<#if baseResultMap??>
        <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="${package.Entity}.${entityName}">
     <#list context.fields as field>
        <#if field.primary>
        <id column="${field.name}" property="${field.propertyName}"/>
        </#if>
     </#list>
            <#--<#list table.commonFields as field>--><#--生成公共字段 -->
            <#--        <result column="${field.name}" property="${field.propertyName}" />-->
            <#--</#list>-->
     <#list context.fields as field>
        <#if !field.primary>
        <result column="${field.name}" property="${field.propertyName}"/>
        </#if>
     </#list>
    </resultMap>
    </#if>

    <#if context.fieldNamesForBr??>
        <!-- 通用查询结果列 -->
    <sql id="Base_Column_List">
            <#--<#list table.commonFields as field>-->
            <#--        ${field.columnName},-->
            <#--</#list>-->
        ${context.fieldNamesForBr}
    </sql>
    </#if>

    <select id="selectPage" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ${context.tableName}
        <where>
        <#list context.fields as field>
            <#if !field.primary>
                <#if field.propertyType == "String">
            <if test="${field.propertyName} != null and ${field.propertyName} != ''">
                        <#assign fieldName = field.propertyName />
                and ${field.name} like <#noparse>concat('%', concat(#{</#noparse>${fieldName}<#noparse>,jdbcType=VARCHAR}, '%'))</#noparse>
            </if>
                <#-- 时间范围查询处理 -->
                <#elseif field.propertyType=="LocalDateTime" || field.propertyType=="Date">
                    <#assign starttime="start"+field.capitalName/>
                    <#assign endtime="end"+field.capitalName/>
            <if test="${starttime} != null and ${starttime} != '' and ${endtime} != null and ${endtime} != ''">
                and ${field.name} between <#noparse>#{</#noparse>${starttime}<#noparse>}</#noparse> and <#noparse>#{</#noparse>${endtime}<#noparse>}</#noparse>
            </if>
                <#else>
            <if test="${field.propertyName} != null">
                and ${field.name} = <#noparse>#{</#noparse>${field.propertyName}<#noparse>}</#noparse>
            </if>
                </#if>
            </#if>
        </#list>
        </where>
    </select>

    <insert id="batchInsert">
       INSERT INTO ${context.tableName} (${context.fieldNames})
       VALUES
       <foreach collection="list" item="${entityName?uncap_first}" separator=",">
        <#list context.fields as field>
            <#if field_index==0>
           <#noparse>(#{</#noparse>${entityName?uncap_first}.${field.propertyName}<#noparse>}</#noparse><#if field_has_next>, </#if>
            <#elseif !field_has_next>
            <#noparse>#{</#noparse>${entityName?uncap_first}.${field.propertyName}<#noparse>})</#noparse>
            <#else>
            <#noparse>#{</#noparse>${entityName?uncap_first}.${field.propertyName}<#noparse>}</#noparse><#if field_has_next>, </#if>
            </#if>
        </#list>
            </foreach>
    </insert>
</mapper>
