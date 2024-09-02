<#assign jpa = "jpa">
<#assign mybatisPlus = "mybatis-plus">
<#assign tkMybatis = "tk-mybatis">
package ${package.Controller};

<#if persistType==jpa>
import org.springframework.data.domain.Page;
<#elseif persistType==mybatisPlus>
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
</#if>
import ${package.Dto}.${dtoName};
import ${package.Entity}.${entityName};
import ${package.Service}.${serviceName};
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.util.ObjectUtils;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * ${context.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/${entityName?uncap_first}")
public class ${controllerName} {

    private final ${serviceName} ${serviceName?uncap_first};

    @PostMapping("/save")
    public void save(@RequestBody ${dtoName} ${dtoName?uncap_first}) {
        ${serviceName?uncap_first}.save(${dtoName?uncap_first});
    }

    /**
     * 通过主键id进行更新
     */
    @PostMapping("/updateById")
    public void updateById(@RequestBody ${dtoName} ${dtoName?uncap_first}) {
        // TODO 待完善
        <#if persistType=="jpa">
        ${serviceName?uncap_first}.save(${dtoName?uncap_first});
        <#else>
        if (ObjectUtils.isEmpty(${dtoName?uncap_first}.get${context.primary.capitalName}())) {
            throw new RuntimeException("主键参数不能为空");
        }
        ${serviceName?uncap_first}.updateById(${dtoName?uncap_first});
        </#if>
    }

    /**
    * 通过id删除数据，有逻辑删除按照逻辑删除执行
    * <p>不支持联合主键</p>
    *
    * @param id 主键Id
    */
    @GetMapping("/remove/{id}")
    public void removeById(@PathVariable("id") ${primaryPropertyType} id) {
        ${serviceName?uncap_first}.deleteById(id);
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    public Object list(@RequestBody ${dtoName} ${dtoName?uncap_first}) {
        // TODO 由于没有统一的分页处理插件，需要自行在控制层处理接受参数和分页信息
    <#if persistType==jpa>
        return ${serviceName?uncap_first}.list(pageParams.getParams().toEntity(), Pages.initPage(pageParams));
    <#elseif persistType==mybatisPlus>
        Page<${entityName}> page = Pages.initPage(pageQuery, ${entityName}.class);
        return ${serviceName?uncap_first}.page(pageParams.getParams(), page);
    <#elseif persistType==tkMybatis>
        return ${serviceName?uncap_first}.findByPage(${dtoName?uncap_first}, 1, 20);
    </#if>
    }
}