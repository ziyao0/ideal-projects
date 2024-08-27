package ${package.Controller};

<#if !isJpa>
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
<#else>
import org.springframework.data.domain.Page;
</#if>
import ${package.Dto}.${table.dtoName};
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import com.ziyao.ideal.web.base.PageParams;
import com.ziyao.ideal.web.base.Pages;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/${table.entityPath}")
public class ${table.controllerName} {

    private final ${table.serviceName} ${table.serviceName?uncap_first};

    @PostMapping("/save")
    public void save(@RequestBody ${table.dtoName} entityDTO) {
        ${table.serviceName?uncap_first}.save(entityDTO.getEntity());
    }

    /**
     * 通过主键id进行更新
     */
    @PostMapping("/updateById")
    public void updateById(@RequestBody ${table.dtoName} entityDTO) {
        <#if !isJpa>
        if (ObjectUtils.isEmpty(entityDTO.getId())) {
            throw new ServiceException(400, "主键参数不能为空");
        }
        ${table.serviceName?uncap_first}.updateById(entityDTO.getEntity());
        <#else>
        ${table.serviceName?uncap_first}.save(entityDTO.getEntity());
        </#if>
    }

    /**
    * 通过id删除数据，有逻辑删除按照逻辑删除执行
    * <p>不支持联合主键</p>
    *
    * @param id 主键Id
    */
    @GetMapping("/remove/{id}")
    public void removeById(@PathVariable("id") ${table.idPropertyType} id) {
        ${table.serviceName?uncap_first}.deleteById(id);
    }
    /**
     * 默认一次插入500条
     */
    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List<${table.dtoName}> entityDTOList) {

    <#if isJpa>
        ${table.serviceName?uncap_first}.saveBatch(entityDTOList.stream().map(${entity}DTO::getEntity).collect(Collectors.toList()));
    <#else>
        ${table.serviceName?uncap_first}.saveBatch(entityDTOList.stream().map(${table.dtoName}::of).collect(Collectors.toList()), 500);
    </#if>
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    public Page<${entity}> list(PageParams<${table.dtoName}> pageParams) {
    <#if isJpa>
        return ${table.serviceName?uncap_first}.list(pageParams.getParams().getEntity(), Pages.initPage(pageParams));
    <#else>
        Page<${entity}> page = Pages.initPage(pageQuery, ${entity}.class);
        return ${table.serviceName?uncap_first}.page(page, pageParams.getParams());
    </#if>
    }
}