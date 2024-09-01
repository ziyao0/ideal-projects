package ${package.Service};

<#if persistType=="jpa">
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package.Dto}.${dtoName};
</#if>
import ${package.Entity}.${entityName};
<#if superServiceClass??>
import ${superServiceClassPackage};
</#if>
/**
 * <p>
 * ${context.comment!} 服务类
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
     * @param ${entityName?uncap_first} 待存储对象
     */
    void save(${entityName} ${entityName?uncap_first});

    /**
     * 通过主键修改 ${context.comment!}
     *
     * @param ${entityName?uncap_first} 待修改对象
     */
    void updateById(${entityName} ${entityName?uncap_first});

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
<#elseif persistType=="mybatisPlus">
    /**
     * 分页查询 ${context.comment!}
     *
     * @param ${dtoName?uncap_first} 查询对象
     * @param page 分页对象
     * @return 返回分页对象
     */
    Page<${entityName}> page(${dtoName} ${dtoName?uncap_first}, Page<${entityName}> page);
<#elseif persistType=="tkMybatis">

</#if>
}
