package ${package.DTO};

import com.ziyao.ideal.web.orm.EntityDTO;
import ${package.Entity}.${entity};
import lombok.Data;
import java.util.Objects;
import com.ziyao.ideal.core.Strings;

<#--<#list table.importPackages as pkg>-->
<#--import ${pkg};-->
<#--</#list>-->
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * ${table.comment!}
 * </p>
 *
 * @author ${author}
 */
<#if entityLombokModel>
@Data
</#if>
public class ${table.dtoName} implements Serializable {

<#if entitySerialVersionUID>
    @Serial
    private static final long serialVersionUID = 1L;
</#if>

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    /**
     * ${field.comment}
     */
    private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->

    <#if !isJpa>
    /**
     * 组装查询条件，可根据具体情况做出修改
     *
     * @see LambdaQueryWrapper
     */
    public LambdaQueryWrapper<${entity}> initWrapper() {

        return Wrappers.lambdaQuery(${entity}.class)
        <#list table.fields as field>
            <#if field.propertyType == "boolean">
                <#assign getprefix="is"/>
            <#else>
                <#assign getprefix="get"/>
            </#if>
            <#if !field.fill??>
                <#if !field.idKey>
                    // ${field.comment}
                    <#if field.propertyType == "String">
                        .likeRight(Strings.hasLength(${field.propertyName}), ${entity}::${getprefix}${field.capitalName}, ${field.propertyName})
                    <#else>
                        .eq(Objects.nonNull(${field.propertyName}), ${entity}::${getprefix}${field.capitalName}, ${field.propertyName})
                    </#if>
                </#if>
            </#if>
        </#list>
        <#list table.fields as field>
            <#if field.propertyName=="sort" || field.propertyName="sorted">
                // ${field.comment}
                .orderByAsc(${entity}::${getprefix}${field.capitalName})
            </#if>
        </#list>
        ;
    }
    </#if>

    public ${entity} getEntity(){
        ${entity} ${entity?uncap_first} = new ${entity}();
    <#list table.fields as field>
        ${entity?uncap_first}.set${field.capitalName}(this.${field.propertyName});
    </#list>
        return ${entity?uncap_first};
    }
}
