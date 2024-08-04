package ${repositoryjpa};

import ${package.Entity}.${entity};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
*
* @author ${author}
*/
@Repository
<#list table.fields as field>
    <#if field.keyIdentityFlag>
public interface ${entity}RepositoryJpa extends JpaRepository<${entity}, ${field.propertyType}> {
}
    </#if>
</#list>