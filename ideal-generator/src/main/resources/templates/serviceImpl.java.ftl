<#assign jpa = "jpa">
<#assign mybatisPlus = "mybatis-plus">
<#assign tkMybatis = "tk-mybatis">
<#if persistType=="jpa">
    <#assign persistentName = repositoryName>
<#else>
    <#assign persistentName = mapperName>
</#if>
package ${package.ServiceImpl};

import ${package.Dto}.${dtoName};
import ${package.Entity}.${entityName};
import ${package.Service}.${serviceName};
<#if persistType=="jpa">
import ${package.Repository}.${persistentName};
import org.springframework.data.domain.*;
<#elseif persistType==mybatisPlus>
import ${package.Mapper}.${persistentName};
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
<#elseif persistType==tkMybatis>
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ${package.Mapper}.${persistentName};
</#if>

<#if superServiceImplClassPackage??>
import ${superServiceImplClassPackage};
</#if>
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;
/**
 * <p>
 * ${context.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 */
@Service
@RequiredArgsConstructor
<#if superServiceImplClassPackage??>
    <#if persistType=="jpa">
public class ${serviceImplName} extends
        ${superServiceImplClass}<${repositoryName}, ${entityName},${primaryPropertyType}> implements ${serviceName} {
    <#else>
public class ${serviceImplName} extends ${superServiceImplClass}<${repositoryName}, ${entityName}> implements ${serviceName} {
    </#if>
<#else>
public class ${serviceImplName} implements ${serviceName} {


    private final ${persistentName} ${persistentName?uncap_first};

    /**
     * 保存 ${context.comment!}
     *
     * @param ${dtoName?uncap_first} 待存储对象
     */
    @Override
    public void save(${dtoName} ${dtoName?uncap_first}) {
    <#if persistType==jpa>
        ${persistentName?uncap_first}.save(${dtoName?uncap_first}.toEntity());
    <#elseif persistType==mybatisPlus>
        ${persistentName?uncap_first}.save(${dtoName?uncap_first}.toEntity());
    <#elseif persistType==tkMybatis>
        ${persistentName?uncap_first}.insertSelective(${dtoName?uncap_first}.toEntity());
    </#if>
    }

    /**
     * 通过主键修改 ${context.comment!}
     *
     * @param ${dtoName?uncap_first} 待修改对象
     */
    @Override
    public void updateById(${dtoName} ${dtoName?uncap_first}) {
    <#if persistType==jpa>
        ${persistentName?uncap_first}.save(${dtoName?uncap_first}.toEntity());
    <#elseif persistType==mybatisPlus>

    <#elseif persistType==tkMybatis>
        ${persistentName?uncap_first}.updateByPrimaryKeySelective(${dtoName?uncap_first}.toEntity());
    </#if>
    }

    /**
     * 通过主键删除 ${context.comment!}
     *
     * @param id 主键id
     */
    @Override
    public void deleteById(${primaryPropertyType} id) {
    <#if persistType==jpa>
        ${persistentName?uncap_first}.deleteById(id);
    <#elseif persistType==mybatisPlus>

    <#elseif persistType==tkMybatis>
        ${persistentName?uncap_first}.deleteByPrimaryKey(id);
    </#if>
    }
</#if>
    <#if persistType==jpa>
    /**
     * 分页查询 ${context.comment!}
     *
     * @param ${dtoName?uncap_first} 查询对象
     * @param pageable 分页对象
     * @return 返回分页对象
     */
    @Override
    public Page<${entityName}> page(${dtoName} ${dtoName?uncap_first},Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
            .withIgnoreNullValues()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // Use CONTAINING for partial matches
        Example<${entityName}> example = Example.of(${dtoName?uncap_first}.toEntity(), matcher);
        return ${persistentName?uncap_first}.findAll(example, pageable);
    <#elseif persistType==mybatisPlus>
    /**
     * 分页查询 ${context.comment!}
     *
     * @param ${dtoName?uncap_first} 查询对象
     * @param pageable 分页对象
     * @return 返回分页对象
     */
    @Override
    public Page<${entityName}> page(${dtoName} ${dtoName?uncap_first}, Pageable pageable) {
        LambdaQueryWrapper<${entityName}> wrapper = ${dtoName?uncap_first}.initWrapper();
        // TODO 2023/5/6 默认排序字段 sort/sorted(默认是为ASC)值越小、越往前
        return ${persistentName?uncap_first}.selectPage(page, wrapper);
    <#elseif persistType==tkMybatis>
    /**
     * 分页查询 ${context.comment!}
     *
     * @param ${dtoName?uncap_first} 查询对象
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 返回分页对象
     */
    @Override
    public PageInfo<${entityName}> findByPage(${dtoName} ${dtoName?uncap_first}, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<${entityName}> ${entityName?uncap_first}List = ${persistentName?uncap_first}.selectPage(${dtoName?uncap_first});
        return new PageInfo<>(${entityName?uncap_first}List);
    </#if>
    }
}