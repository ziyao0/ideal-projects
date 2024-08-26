package ${package.Service};

<#if !isJpa>
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package.DTO}.${table.dtoName};
</#if>
import ${package.Entity}.${entity};
import ${superServiceClassPackage};

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 */
<#if isJpa>
public interface ${table.serviceName} extends ${superServiceClass}<${entity},${table.idPropertyType}> {
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
     * 分页查询
     */
    Page<${entity}> page(Page<${entity}> page, ${table.dtoName} ${table.dtoName?uncap_first});

</#if>
}
