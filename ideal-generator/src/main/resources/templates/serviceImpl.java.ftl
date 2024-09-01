<#assign jpa = "jpa">
<#assign mybatisPlus = "mybatisPlus">
<#assign tkMybatis = "tkMybatis">
<#if persistType=="jpa">
    <#assign persistentName = repositoryName>
<#else>
    <#assign persistentName = mapperName>
</#if>
package ${package.ServiceImpl};

<#if persistType!="jpa">
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package.Dto}.${dtoName};
</#if>
<#if persistType=="jpa">
import ${package.Repository}.${persistentName};
<#else>
import ${package.Mapper}.${persistentName};
</#if>
import org.springframework.data.domain.*;
<#if isSupperClass>
import ${superServiceImplClassPackage};
</#if>
import ${package.Entity}.${entityName};
import ${package.Service}.${serviceName};
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
/**
* <p>
    * ${context.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 */
@Service
@RequiredArgsConstructor
<#if isSupperClass>
    <#if persistType=="jpa">
public class ${serviceImplName} extends
        ${superServiceImplClass}<${repositoryName}, ${entityName},${primaryPropertyType}> implements ${serviceName} {
    <#else>
public class ${serviceImplName} extends ${superServiceImplClass}<${repositoryName}, ${entityName}> implements ${serviceName} {
    </#if>
<#else>
public class ${serviceImplName} implements ${serviceName} {
</#if>

    private final ${persistentName} ${persistentName?uncap_first};
    /**
     * 保存 ${context.comment!}
     *
     * @param ${entityName?uncap_first} 待存储对象
     */
    @Override
    public void save(${entityName} ${entityName?uncap_first}) {
    <#if persistType==jpa>

    <#elseif persistType==mybatisPlus>

    <#elseif persistType==tkMybatis>

    </#if>
    }

    /**
     * 通过主键修改 ${context.comment!}
     *
     * @param ${entityName?uncap_first} 待修改对象
     */
    @Override
    public void updateById(${entityName} ${entityName?uncap_first}) {
    <#if persistType==jpa>

    <#elseif persistType==mybatisPlus>

    <#elseif persistType==tkMybatis>

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

    <#elseif persistType==mybatisPlus>

    <#elseif persistType==tkMybatis>

    </#if>
    }

    /**
     * 分页查询 ${context.comment!}
     *
     * @param ${dtoName?uncap_first} 查询对象
     * @param pageable 分页对象
     * @return 返回分页对象
     */
    @Override
    public Page<${entityName}> page(${dtoName} ${dtoName?uncap_first},Pageable pageable) {
    <#if persistType==jpa>
        ExampleMatcher matcher = ExampleMatcher.matching()
            .withIgnoreNullValues()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // Use CONTAINING for partial matches
        Example<${entityName}> example = Example.of(${dtoName?uncap_first}.toEntity(), matcher);
        return ${persistentName?uncap_first}.findAll(example, pageable);
    <#elseif persistType==mybatisPlus>
        LambdaQueryWrapper<${entityName}> wrapper = ${dtoName?uncap_first}.initWrapper();
        // TODO 2023/5/6 默认排序字段 sort/sorted(默认是为ASC)值越小、越往前
        return ${persistentName?uncap_first}.selectPage(page, wrapper);
    <#elseif persistType==tkMybatis>
        Example example = createExample(${dtoName?uncap_first});
        return ${persistentName?uncap_first}.selectByExample(example);
    </#if>
    }

    <#if persistType==tkMybatis>
    /**
     * 组装查询条件
     */
    private Example createExample(${dtoName} ${dtoName?uncap_first}) {
        Example example = new Example(${entityName}.class);
        Example.Criteria criteria = example.createCriteria();

        // 组装查询条件
    <#list context.fields as field>
        <#if field.propertyType == "boolean">
            <#assign getprefix="is"/>
        <#else>
            <#assign getprefix="get"/>
        </#if>
        <#if !field.primary>
        // ${field.comment}
        ${field.propertyType} ${field.propertyName} = ${dtoName?uncap_first}.${getprefix}${field.capitalName}();
            <#if field.propertyType == "String">
        if (Strings.hasText(${field.propertyName})) {
            criteria.andLike("${field.propertyName}", "%"+${field.propertyName}+"%");
            <#else>
        if (Objects.nonNull(${field.propertyName})) {
            criteria.andEqualTo("${field.propertyName}", ${field.propertyName});
            </#if>
        }
        </#if>
    </#list>
        return criteria;
    }
    </#if>

    <#if persistType=="mybatisPlus">
        /**
         * 组装查询条件，可根据具体情况做出修改
         *
         * @see LambdaQueryWrapper
         */
        public LambdaQueryWrapper<${entityName}> createWrapper() {

        return Wrappers.lambdaQuery(${entityName}.class)
        <#list context.fields as field>
            <#if field.propertyType == "boolean">
                <#assign getprefix="is"/>
            <#else>
                <#assign getprefix="get"/>
            </#if>
            <#if !field.primary>
                // ${field.comment}
                <#if field.propertyType == "String">
                    .likeRight(Strings.hasLength(${field.propertyName}), ${entityName}::${getprefix}${field.capitalName}, ${field.propertyName})
                <#else>
                    .eq(Objects.nonNull(${field.propertyName}), ${entityName}::${getprefix}${field.capitalName}, ${field.propertyName})
                </#if>
            </#if>
        </#list>
        <#list context.fields as field>
            <#if field.propertyName=="sort" || field.propertyName="sorted">
                // ${field.comment}
                .orderByAsc(${entityName}::${getprefix}${field.capitalName})
            </#if>
        </#list>
        ;
        }
    </#if>
}