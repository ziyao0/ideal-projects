package ${package.Entity};


<#list table.importPackages as pkg>
import ${pkg};
</#list>
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

/**
 * <p>
 * ${table.comment!}
 * </p>
 *
 * @author ${author}
 */
@Getter
@Setter
@Builder
@Entity(name = "${table.name}")
@NoArgsConstructor
@AllArgsConstructor
<#if superEntityClass??>
public class ${entity} extends ${superEntityClass} {
<#else>
public class ${entity} implements Serializable {
</#if>


    private static final long serialVersionUID = 1L;

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>

    /**
     * ${field.propertyName}:${field.comment}
     */
     <#-- 主键 -->
     <#if field.keyFlag>
    @Id
     </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->
}
