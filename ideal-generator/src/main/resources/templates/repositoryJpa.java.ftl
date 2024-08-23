package ${repositoryJpa};

import ${package.Entity}.${entity};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
*
* @author ${author}
*/
@Repository
<#list table.fields as field>
    <#if field.keyFlag>
public interface ${entity}RepositoryJpa extends JpaRepository<${entity}, ${field.propertyType}> {
}
    </#if>
</#list>
