package ${package.ServiceImpl};

<#if !isJpa>
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package.DTO}.${table.dtoName};
import ${package.Mapper}.${table.mapperName};
</#if>
import ${package.RepositoryJpa}.${table.repositoryName};
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
<#if isJpa>
public class ${table.serviceImplName} extends
        ${superServiceImplClass}<${entity}RepositoryJpa, ${entity},${table.idPropertyType}> implements ${table.serviceName} {

    private final ${table.repositoryName} ${table.repositoryName?uncap_first};
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    private final ${table.mapperName} ${table.mapperName?uncap_first};

    @Override
    public Page<${entity}> page(Page<${entity}> page, ${table.dtoName} ${table.dtoName?uncap_first}) {
        LambdaQueryWrapper<${entity}> wrapper = ${table.dtoName?uncap_first}.initWrapper();
        // TODO 2023/5/6 默认排序字段 sort/sorted(默认是为ASC)值越小、越往前
        return ${table.mapperName?uncap_first}.selectPage(page, wrapper);
    }
</#if>
}