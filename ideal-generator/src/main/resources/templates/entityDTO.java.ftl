package ${package.Dto};

import ${package.Entity}.${entityName};
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
<#list context.baseImportPackages as pkg>
import ${pkg};
</#list>
<#if persistType=="mybatis-plus">
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
</#if>
<#if springdoc>
import io.swagger.v3.oas.annotations.media.Schema;
</#if>
<#if swagger>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * ${context.comment!}
 * </p>
 *
 * @author ${author}
 */
@Data
<#if springdoc>
@Schema(description = "${context.comment!}")
</#if>
<#if swagger>
@ApiModel(description = "${context.comment!}")
</#if>
public class ${dtoName} implements Serializable {

<#if serial>
    @Serial
    private static final long serialVersionUID = 1L;
</#if>

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list context.fields as field>

    <#if field.propertyType=="LocalDateTime" || field.propertyType=="Date">
    /**
     * ${field.comment}
     */
        <#if springdoc>
    @Schema(description = "${field.comment}")
        </#if>
        <#if swagger>
    @ApiModelProperty("${field.comment}")
        </#if>
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private ${field.propertyType} ${field.propertyName};
    /**
     * start time for ${field.comment}
     */
        <#if springdoc>
    @Schema(description = "${field.comment}-开始时间")
        </#if>
        <#if swagger>
    @ApiModelProperty("${field.comment}-开始时间")
        </#if>
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private ${field.propertyType} start${field.capitalName};

     /**
      * end time for ${field.comment}
      */
        <#if springdoc>
     @Schema(description = "${field.comment}-结束时间")
        </#if>
        <#if swagger>
     @ApiModelProperty("${field.comment}-结束时间")
        </#if>
     @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
     private ${field.propertyType} end${field.capitalName};
    <#else>
     /**
      * ${field.comment}
      */
        <#if springdoc>
     @Schema(description = "${field.comment}")
        </#if>
        <#if swagger>
     @ApiModelProperty("${field.comment}")
        </#if>
     private ${field.propertyType} ${field.propertyName};
    </#if>
</#list>
<#------------  END 字段循环遍历  ---------->

    public ${entityName} toEntity(){
        ${entityName} ${entityName?uncap_first} = new ${entityName}();
    <#list context.fields as field>
        ${entityName?uncap_first}.set${field.capitalName}(this.${field.propertyName});
    </#list>
        return ${entityName?uncap_first};
    }
}
