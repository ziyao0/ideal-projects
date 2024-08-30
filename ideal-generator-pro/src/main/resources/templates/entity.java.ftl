package ${package.Entity};

import java.io.Serial;
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

    @Serial
    private static final long serialVersionUID = 1L;

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>

    /**
     * ${field.propertyName}:${field.comment}
     */
     <#-- 主键 -->
     <#if field.idKey>
    @Id
     </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->

    public static class Builder {
        private final ${entity} ${entity} ${entity?uncap_first} = new ${entity}();
    <#list table.fields as field>
        public Builder ${field.propertyName}(${field.propertyType} ${field.propertyName}){
            this.${entity?uncap_first}.${field.propertyName} = ${field.propertyName};
            return this;
        }
    </#list>

        public ${entity} build(){
            return this.${entity?uncap_first};
        }
    }
}
