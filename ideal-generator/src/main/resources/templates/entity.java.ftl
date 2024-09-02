<#assign jpa = "jpa">
<#assign mybatisPlus = "mybatis-plus">
<#assign tkMybatis = "tk-mybatis">
package ${package.Entity};

<#list context.importPackages as pkg>
import ${pkg};
</#list>
<#list context.baseImportPackages as pkg>
import ${pkg};
</#list>
import lombok.*;
<#if serial>
import java.io.Serial;
import java.io.Serializable;
</#if>

/**
 * <p>
 * ${context.comment!}
 * </p>
 *
 * @author ${author}
 */
@Getter
@Setter
@Builder
<#if persistType==jpa>
@Entity(name = "${context.tableName}")
<#elseif persistType==mybatisPlus>
@TableName("${context.tableName}")
<#elseif persistType==tkMybatis>
@Table(name = "${context.tableName}")
</#if>
@NoArgsConstructor
@AllArgsConstructor
<#if superEntityClass??>
public class ${entityName} extends ${superEntityClass} {
<#else>
<#if serial>
public class ${entityName} implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
<#else>
public class ${entityName} {
</#if>
</#if>

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list context.fields as field>
    /**
     * ${field.propertyName}:${field.comment}
     */
     <#-- 主键 -->
     <#if field.primary>
         <#if persistType==mybatisPlus>
    @TableId
         <#else>
    @Id
         </#if>
     </#if>
    <#if persistType==mybatisPlus>
    @TableField("${field.name}")
    <#elseif persistType==tkMybatis>
    @Column(name = "${field.name}")
    </#if>
    private ${field.propertyType} ${field.propertyName};

</#list>
<#------------  END 字段循环遍历  ---------->

    public static class Builder {

        private final ${entityName} ${entityName?uncap_first} = new ${entityName}();

    <#list context.fields as field>
        public Builder ${field.propertyName}(${field.propertyType} ${field.propertyName}) {
            this.${entityName?uncap_first}.${field.propertyName} = ${field.propertyName};
            return this;
        }

    </#list>

        public ${entityName} build(){
            return this.${entityName?uncap_first};
        }
    }
}
