package com.ziyao.ideal.uua.controllers;

import com.ziyao.ideal.uua.domain.dto.RestrictionRuleDTO;
import com.ziyao.ideal.uua.domain.entity.RestrictionRule;
import com.ziyao.ideal.uua.service.RestrictionRuleService;
import com.ziyao.ideal.web.base.JpaBaseController;
import com.ziyao.ideal.web.base.PageParams;
import com.ziyao.ideal.web.base.Pages;
import org.springframework.data.domain.Page;
import org.springframework.util.ObjectUtils;
import com.ziyao.ideal.web.exception.ServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

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
    restrictionRuleService.save(entityDTO.of());
    }

    /**
     * 通过主键id进行更新
     */
    @PostMapping("/updateById")
    public void updateById(@RequestBody RestrictionRuleDTO entityDTO) {
        if (ObjectUtils.isEmpty(entityDTO.getId())) {
            throw new ServiceException(400, "主键参数不能为空");
        }
        restrictionRuleService.save(entityDTO.of());
    }

    /**
     * 默认一次插入500条
     */
    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List<RestrictionRuleDTO> entityDTOList) {
        restrictionRuleService.saveBatch(entityDTOList.stream().map(RestrictionRuleDTO::of).collect(Collectors.toList()));
    }

    /**
     * 分页查询
     */
    @PostMapping("/searchSimilar")
    public Page<RestrictionRule> searchSimilar(PageParams<RestrictionRuleDTO> pageParams) {
        return restrictionRuleService.searchSimilar(pageParams.getParams().of(), Pages.initPage(pageParams));
    }
}
