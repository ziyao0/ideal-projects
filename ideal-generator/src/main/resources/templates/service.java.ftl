<#assign jpa = "jpa">
<#assign mybatisPlus = "mybatis-plus">
<#assign tkMybatis = "tk-mybatis">
package ${package.Service};

import ${package.Dto}.${dtoName};
import ${package.Entity}.${entityName};
<#if superServiceClass??>
import ${superServiceClassPackage};
</#if>
<#if persistType==jpa>
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
<#elseif persistType==mybatisPlus>
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
<#elseif persistType==tkMybatis>
import com.github.pagehelper.PageInfo;
</#if>

/**
 * <p>
 *${context.comment!} 服务类
 * </p>
 *
 * @author ${author}
 */
<#if superServiceClass??>
<#if persistType=="jpa">
public interface ${serviceName} extends ${superServiceClass}<${entityName},${primaryPropertyType}> {
<#else>
public interface ${serviceName} extends ${superServiceClass}<${entityName}> {
</#if>
<#else>
public interface ${serviceName} {

    /**
     * 保存 ${context.comment!}
     *
     * @param ${dtoName?uncap_first} 待存储对象
     */
    void save(${dtoName} ${dtoName?uncap_first});

    /**
     * 通过主键修改 ${context.comment!}
     *
     * @param ${dtoName?uncap_first} 待修改对象
     */
    void updateById(${dtoName} ${dtoName?uncap_first});

    /**
     * 通过主键删除 ${context.comment!}
     *
     * @param id 主键id
     */
    void deleteById(${primaryPropertyType} id);
</#if>

<#if persistType=="jpa">
    /**
     * 分页查询 ${context.comment!}
     *
     * @param ${dtoName?uncap_first} 查询对象
     * @param pageable 分页对象
     * @return 返回分页对象
     */
    Page<${entityName}> page(${dtoName} ${dtoName?uncap_first}, Pageable pageable);
<#elseif persistType=="mybatis-plus">
    /**
     * 分页查询 ${context.comment!}
     *
     * @param ${dtoName?uncap_first} 查询对象
     * @param page 分页对象
     * @return 返回分页对象
     */
    Page<${entityName}> page(${dtoName} ${dtoName?uncap_first}, Page<${entityName}> page);
<#elseif persistType=="tk-mybatis">
    /**
     * 分页查询 ${context.comment!}
     *
     * @param ${dtoName?uncap_first} 查询对象
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 返回分页对象
     */
    PageInfo<${entityName}> findByPage(${dtoName} ${dtoName?uncap_first}, int pageNum, int pageSize);
</#if>
}
