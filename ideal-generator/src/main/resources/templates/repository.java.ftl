<#if persistType=="jpa">
package ${package.Repository};
<#else>
package ${package.Mapper};
</#if>
import ${package.Entity}.${entityName};
import ${superRepositoryClass};
<#if persistType=="jpa">
import org.springframework.stereotype.Repository;
</#if>
/**
 * <p>
 * ${context.comment!} 持久化接口
 * </p>
*
* @author ${author}
*/
<#if persistType=="jpa">
@Repository
public interface ${repositoryName} extends ${superRepositoryClass}<${entityName}, ${primaryPropertyType}> {
}
<#else>
public interface ${mapperName} extends ${superRepositoryClass}<${entityName}> {

}
</#if>