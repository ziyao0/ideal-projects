package ${package.Controller};

import ${dto}.${entity}DTO;
<#if superControllerClassPackage??>
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import ${superControllerClassPackage};
</#if>
import com.ziyao.ideal.web.base.PageParams;
import com.ziyao.ideal.web.base.Pages;
import org.springframework.data.domain.Page;
import org.springframework.util.ObjectUtils;
import com.ziyao.ideal.web.exception.ServiceException;
import org.springframework.web.bind.annotation.*;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>

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
<#list table.fields as field>
<#if field.keyFlag>
public class ${table.controllerName} extends ${superControllerClass}<${table.serviceName}, ${entity}, ${field.propertyType}> {

    private final ${table.serviceName} ${table.serviceName?uncap_first};

    @PostMapping("/save")
    public void save(@RequestBody ${entity}DTO entityDTO) {
        ${table.serviceName?uncap_first}.save(entityDTO.convert());
    }

    /**
     * 通过主键id进行更新
     */
    @PostMapping("/updateById")
    public void updateById(@RequestBody ${entity}DTO entityDTO) {
        if (ObjectUtils.isEmpty(entityDTO.getId())) {
            throw new ServiceException(400, "主键参数不能为空");
        }
        ${table.serviceName?uncap_first}.save(entityDTO.convert());
    }

    /**
    * 通过id删除数据，有逻辑删除按照逻辑删除执行
    * <p>不支持联合主键</p>
    *
    * @param id 主键Id
    */
    @GetMapping("/remove/{id}")
    public void removeById(@PathVariable("id") ${field.propertyType} id) {
        ${table.serviceName?uncap_first}.deleteById(id);
    }
    /**
     * 默认一次插入500条
     */
    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List<${entity}DTO> entityDTOList) {
        ${table.serviceName?uncap_first}.saveBatch(entityDTOList.stream().map(${entity}DTO::convert).collect(Collectors.toList()));
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    public Page<${entity}> list(PageParams<${entity}DTO> pageParams) {
        return ${table.serviceName?uncap_first}.list(pageParams.getParams().convert(), Pages.initPage(pageParams));
    }
}
</#if>
</#list>