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
<#if springdoc>
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
</#if>
<#if swagger>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
</#if>
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
<#if springdoc>
@Tag(name = "${context.comment!}", description = "${context.comment!}")
</#if>
<#if swagger>
@Api(value = "${context.comment!}")
</#if>
public class ${controllerName} {

    private final ${serviceName} ${serviceName?uncap_first};
    /**
     * 保存
     */
    @PostMapping("/save")
<#if springdoc>
    @Operation(summary = "保存${context.comment!}", description = "保存${context.comment!}")
</#if>
<#if swagger>
    @ApiOperation(value = "保存${context.comment!}", notes = "保存${context.comment!}")
</#if>
    public void save(@RequestBody ${dtoName} ${dtoName?uncap_first}) {
        ${serviceName?uncap_first}.save(${dtoName?uncap_first});
    }

    /**
     * 通过主键id进行更新
     */
    @PostMapping("/updateById")
<#if springdoc>
    @Operation(summary = "通过主键ID进行更新", description = "通过主键ID进行更新")
</#if>
<#if swagger>
    @ApiOperation(value = "通过主键ID进行更新", notes = "通过主键ID进行更新")
</#if>
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
<#if springdoc>
    @Operation(summary = "通过主键进行删除", description = "通过主键进行删除")
</#if>
<#if swagger>
    @ApiOperation(value = "通过主键进行删除", notes = "通过主键进行删除")
</#if>
    public void removeById(@PathVariable("id") ${primaryPropertyType} id) {
        ${serviceName?uncap_first}.deleteById(id);
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
<#if springdoc>
    @Operation(summary = "分页查询数据", description = "分页查询数据")
</#if>
<#if swagger>
    @ApiOperation(value = "分页查询数据", notes = "分页查询数据")
</#if>
    public Object list(@RequestBody ${dtoName} ${dtoName?uncap_first}) {
        // TODO 由于没有统一的分页处理插件，需要自行在控制层处理接受参数和分页信息
    <#if persistType==jpa>
        return ${serviceName?uncap_first}.list(${dtoName?uncap_first}.toEntity(), PageRequest.of(1, 20));
    <#elseif persistType==mybatisPlus>
        Page<${entityName}> page = Pages.initPage(pageQuery, ${entityName}.class);
        return ${serviceName?uncap_first}.page(${dtoName?uncap_first}.toEntity, page);
    <#elseif persistType==tkMybatis>
        return ${serviceName?uncap_first}.findByPage(${dtoName?uncap_first}, 1, 20);
    </#if>
    }
}