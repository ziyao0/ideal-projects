package ${dto};

import com.ziyao.ideal.web.orm.EntityDTO;
import ${package.Entity}.${entity};
import ${mapstructPkg}.${entity}Convertor;
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
public class ${entity}DTO implements EntityDTO<${entity}>, Serializable {

<#if entitySerialVersionUID>
    @Serial
    private static final long serialVersionUID = 1L;
</#if>

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    /**
     * ${field.comment}
     */
<#--    <#if field.propertyType&&(field.propertyName?ends_with("id") || field.propertyName?ends_with("Id"))>-->
<#--    private Long ${field.propertyName};-->
<#--    <#else>-->
    private ${field.propertyType} ${field.propertyName};
<#--    </#if>-->
</#list>
<#------------  END 字段循环遍历  ---------->

    public ${entity} convert() {
        return ${entity}Convertor.INSTANCE.convert(this);
    }
}
