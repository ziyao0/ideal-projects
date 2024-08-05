package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

/**
* <p>
    * ${table.comment!} 服务类
    * </p>
*
* @author ${author}
*/
<#list table.fields as field>
<#if field.keyIdentityFlag>
public interface ${table.serviceName} extends ${superServiceClass}<${entity},${field.propertyType}> {

}
</#if>
</#list>
