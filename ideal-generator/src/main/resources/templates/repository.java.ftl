<#if persistType=="jpa">
package ${package.Repository};
<#else>
package ${package.Mapper};
</#if>
import ${package.Entity}.${entityName};
import ${superRepositoryClassPackage};
<#if persistType=="jpa">
import org.springframework.stereotype.Repository;
<#else>
import ${package.Dto}.${dtoName};
import java.util.List;
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

    /**
     * 分页查询数据
     *
     * @param ${dtoName?uncap_first} 查询条件
     * @return 返回查询结果
     */
    List<${entityName}> selectPage(${dtoName} ${dtoName?uncap_first});

    /**
     * 批量查询
     *
     * @param ${entityName?uncap_first}List 待保存数据
     */
    void batchInsert(List<${entityName}> ${entityName?uncap_first}List);
}
</#if>