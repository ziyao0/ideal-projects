package com.ziyao.ideal.uua.controllers;

import com.ziyao.ideal.uua.domain.dto.GlobalLoginRestrictionDTO;
import com.ziyao.ideal.uua.domain.entity.GlobalLoginRestriction;
import com.ziyao.ideal.uua.service.GlobalLoginRestrictionService;
import com.ziyao.ideal.jpa.extension.controllers.JpaBaseController;
import com.ziyao.ideal.web.base.PageParams;
import com.ziyao.ideal.web.base.Pages;
import org.springframework.data.domain.Page;
import org.springframework.util.ObjectUtils;
import com.ziyao.ideal.web.exception.ServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * 全局登录限制表 前端控制器
 * </p>
 *
 * @author ziyao
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/globalLoginRestriction")
public class GlobalLoginRestrictionController extends JpaBaseController<GlobalLoginRestrictionService, GlobalLoginRestriction, Integer> {

    private final GlobalLoginRestrictionService globalLoginRestrictionService;

    @PostMapping("/save")
    public void save(@RequestBody GlobalLoginRestrictionDTO entityDTO) {
        globalLoginRestrictionService.save(entityDTO.convert());
    }

    /**
     * 通过主键id进行更新
     */
    @PostMapping("/updateById")
    public void updateById(@RequestBody GlobalLoginRestrictionDTO entityDTO) {
        if (ObjectUtils.isEmpty(entityDTO.getId())) {
            throw new ServiceException(400, "主键参数不能为空");
        }
        globalLoginRestrictionService.save(entityDTO.convert());
    }

    /**
    * 通过id删除数据，有逻辑删除按照逻辑删除执行
    * <p>不支持联合主键</p>
    *
    * @param id 主键Id
    */
    @GetMapping("/remove/{id}")
    public void removeById(@PathVariable("id") Integer id) {
        globalLoginRestrictionService.deleteById(id);
    }
    /**
     * 默认一次插入500条
     */
    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List<GlobalLoginRestrictionDTO> entityDTOList) {
        globalLoginRestrictionService.saveBatch(entityDTOList.stream().map(GlobalLoginRestrictionDTO::convert).collect(Collectors.toList()));
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    public Page<GlobalLoginRestriction> list(PageParams<GlobalLoginRestrictionDTO> pageParams) {
        return globalLoginRestrictionService.list(pageParams.getParams().convert(), Pages.initPage(pageParams));
    }
}
