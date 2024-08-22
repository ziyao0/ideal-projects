package com.ziyao.ideal.uua.controllers;

import com.ziyao.ideal.jpa.extension.controllers.JpaBaseController;
import com.ziyao.ideal.uua.domain.dto.RestrictionRuleDTO;
import com.ziyao.ideal.uua.domain.entity.RestrictionRule;
import com.ziyao.ideal.uua.service.RestrictionRuleService;
import com.ziyao.ideal.web.base.PageParams;
import com.ziyao.ideal.web.base.Pages;
import com.ziyao.ideal.web.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 限制规则表 前端控制器
 * </p>
 *
 * @author ziyao
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/restrictionRule")
public class RestrictionRuleController extends JpaBaseController<RestrictionRuleService, RestrictionRule, Integer> {

    private final RestrictionRuleService restrictionRuleService;

    @PostMapping("/save")
    public void save(@RequestBody RestrictionRuleDTO entityDTO) {
        restrictionRuleService.save(entityDTO.convert());
    }

    /**
     * 通过主键id进行更新
     */
    @PostMapping("/updateById")
    public void updateById(@RequestBody RestrictionRuleDTO entityDTO) {
        if (ObjectUtils.isEmpty(entityDTO.getId())) {
            throw new ServiceException(400, "主键参数不能为空");
        }
        restrictionRuleService.save(entityDTO.convert());
    }

    /**
     * 通过id删除数据，有逻辑删除按照逻辑删除执行
     * <p>不支持联合主键</p>
     *
     * @param id 主键Id
     */
    @GetMapping("/remove/{id}")
    public void removeById(@PathVariable("id") Integer id) {
        restrictionRuleService.deleteById(id);
    }

    /**
     * 默认一次插入500条
     */
    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List<RestrictionRuleDTO> entityDTOList) {
        restrictionRuleService.saveBatch(entityDTOList.stream().map(RestrictionRuleDTO::convert).collect(Collectors.toList()));
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    public Page<RestrictionRule> list(PageParams<RestrictionRuleDTO> pageParams) {
        return restrictionRuleService.list(pageParams.getParams().convert(), Pages.initPage(pageParams));
    }
}
