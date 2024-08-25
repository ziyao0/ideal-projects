package ${package.Controller};

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ziyao.core.error.Exceptions;
import ${package.DTO}.${table.dtoName};
<#if superControllerClassPackage??>
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import ${superControllerClassPackage};
</#if>
import com.ziyao.ideal.web.base.PageParams;
import com.ziyao.ideal.web.base.Pages;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/${package.ModuleName}")
public class ${table.controllerName} extends ${superControllerClass}<${table.serviceName}, ${entity}> {

    private final ${table.serviceName} ${table.serviceName?uncap_first};

    @PostMapping("/save")
    public void save(@RequestBody ${table.dtoName} entityDTO) {
        super.iService.save(entityDTO.of());
    }

    @PostMapping("/saveOrUpdate")
    public void saveOrUpdate(@RequestBody ${table.dtoName} entityDTO) {
        super.iService.saveOrUpdate(entityDTO.of());
    }

    @PostMapping("/updateById")
    public void updateById(@RequestBody ${table.dtoName} entityDTO) {
        if (ObjectUtils.isEmpty(entityDTO.getId())) {
            throw Exceptions.createIllegalArgumentException(null);
        }
        super.iService.updateById(entityDTO.of());
}

    /**
    * 默认一次插入500条
    */
    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List<${table.dtoName}> entityDTOList) {
        super.iService.saveBatch(entityDTOList.stream().map(${table.dtoName}::of).collect(Collectors.toList()), 500);
    }

    /**
     * 条件分页查询
     *
     * @param pageParams 分页参数
     * @return 返回分页查询信息
     */
    @PostMapping("/page/get")
    public Page<${entity}> getPage(@RequestBody PageParams<${table.dtoName}> pageParams) {
        Page<${entity}> page = Pages.initPage(pageQuery, ${entity}.class);
        return ${table.serviceName?uncap_first}.page(page, pageParams.getParams());
    }
}
