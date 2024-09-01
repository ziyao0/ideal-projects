<#assign jpa = "jpa">
<#assign mybatisPlus = "mybatisPlus">
<#assign tkMybatis = "tkMybatis">
package ${package.Controller};

<#if persistType=="jpa">
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
<#else>
import org.springframework.data.domain.Page;
</#if>
import ${package.Dto}.${dtoName};
import ${package.Entity}.${entityName};
import ${package.Service}.${serviceName};
import com.ziyao.ideal.web.base.PageParams;
import com.ziyao.ideal.web.base.Pages;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
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
@RequestMapping("/${entityName}")
public class ${controllerName} {

    private final ${serviceName} ${serviceName?uncap_first};

    @PostMapping("/save")
    public void save(@RequestBody ${dtoName} ${dtoName?uncap_first}) {
        ${serviceName?uncap_first}.save(${dtoName?uncap_first}.toEntity());
    }

    /**
     * 通过主键id进行更新
     */
    @PostMapping("/updateById")
    public void updateById(@RequestBody ${dtoName} ${dtoName?uncap_first}) {
        <#if persistType=="jpa">
        ${serviceName?uncap_first}.save(${dtoName?uncap_first}.toEntity());
        <#else>
        if (ObjectUtils.isEmpty(${dtoName?uncap_first}.getId())) {
            throw new ServiceException(400, "主键参数不能为空");
        }
        ${serviceName?uncap_first}.updateById(${dtoName?uncap_first}.toEntity());
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
    public Page<${entityName}> list(PageParams<${dtoName}> pageParams) {
    <#if persistType==jpa>
        return ${serviceName?uncap_first}.list(pageParams.getParams().toEntity(), Pages.initPage(pageParams));
    <#elseif persistType==mybatisPlus>
        Page<${entityName}> page = Pages.initPage(pageQuery, ${entityName}.class);
        return ${serviceName?uncap_first}.page(pageParams.getParams(), page);
    <#elseif persistType==tkMybatis>

    </#if>
    }
}