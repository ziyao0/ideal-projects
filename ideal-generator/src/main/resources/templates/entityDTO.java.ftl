package ${package.Dto};

import ${package.Entity}.${entityName};
import lombok.Data;
import java.util.Objects;
import com.ziyao.ideal.core.Strings;
<#list context.baseImportPackages as pkg>
import ${pkg};
</#list>
<#if persistType=="mybatisPlus">
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
public class ${dtoName} implements Serializable {

<#if serial>
    @Serial
    private static final long serialVersionUID = 1L;
</#if>

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list context.fields as field>
    /**
     * ${field.comment}
     */
    private ${field.propertyType} ${field.propertyName};
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
