package ${package.ServiceImpl};

import ${repositoryJpa}.${entity}RepositoryJpa;
import ${superServiceImplClassPackage};
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
/**
* <p>
    * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 */
@Service
@RequiredArgsConstructor
<#list table.fields as field>
<#if field.keyFlag>
public class ${table.serviceImplName} extends ${superServiceImplClass}< ${entity}RepositoryJpa, ${entity},${field.propertyType}> implements ${table.serviceName} {

    private final ${entity}RepositoryJpa ${entity?uncap_first}RepositoryJpa;

}
</#if>
</#list>