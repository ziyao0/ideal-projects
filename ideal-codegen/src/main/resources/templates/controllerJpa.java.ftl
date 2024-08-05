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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
<#if field.keyIdentityFlag>
public class ${table.controllerName} extends ${superControllerClass}<${table.serviceName}, ${entity},${field.propertyType}> {

private final ${table.serviceName} ${table.serviceName?uncap_first};

    @PostMapping("/save")
    public void save(@RequestBody ${entity}DTO entityDTO) {
    ${table.serviceName?uncap_first}.save(entityDTO.getInstance());
    }

    @PostMapping("/updateById")
    public void updateById(@RequestBody ${entity}DTO entityDTO) {
        if (ObjectUtils.isEmpty(entityDTO.getId())) {
            throw new ServiceException(400, "主键参数不能为空");
        }
        ${table.serviceName?uncap_first}.save(entityDTO.getInstance());
    }

    /**
    * 默认一次插入500条
    */
    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List<${entity}DTO> entityDTOList) {
    ${table.serviceName?uncap_first}.saveAll(entityDTOList.stream().map(${entity}DTO::getInstance).collect(Collectors.toList()));
    }

    /**
    * 分页查询
    */
    @PostMapping("/searchSimilar")
    public Page<${entity}> searchSimilar(PageParams<${entity}DTO> pageParams) {
        return ${table.serviceName?uncap_first}.searchSimilar(pageParams.getParams().getInstance(), Pages.initPage(pageParams));
    }
}
</#if>
</#list>