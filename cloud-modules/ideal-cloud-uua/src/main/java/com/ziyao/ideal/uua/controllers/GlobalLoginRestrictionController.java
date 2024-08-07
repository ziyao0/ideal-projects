package com.ziyao.ideal.uua.controllers;

import com.ziyao.ideal.uua.domain.dto.GlobalLoginRestrictionDTO;
import com.ziyao.ideal.uua.domain.entity.GlobalLoginRestriction;
import com.ziyao.ideal.uua.service.GlobalLoginRestrictionService;
import com.ziyao.ideal.web.base.JpaBaseController;
import com.ziyao.ideal.web.base.PageParams;
import com.ziyao.ideal.web.base.Pages;
import com.ziyao.ideal.web.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
        globalLoginRestrictionService.save(entityDTO.of());
    }

    /**
     * 通过主键id进行更新
     */
    @PostMapping("/updateById")
    public void updateById(@RequestBody GlobalLoginRestrictionDTO entityDTO) {
        if (ObjectUtils.isEmpty(entityDTO.getId())) {
            throw new ServiceException(400, "主键参数不能为空");
        }
        globalLoginRestrictionService.save(entityDTO.of());
    }

    /**
     * 默认一次插入500条
     */
    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List<GlobalLoginRestrictionDTO> entityDTOList) {
        globalLoginRestrictionService.saveBatch(entityDTOList.stream().map(GlobalLoginRestrictionDTO::of).collect(Collectors.toList()));
    }

    /**
     * 分页查询
     */
    @PostMapping("/searchSimilar")
    public Page<GlobalLoginRestriction> searchSimilar(PageParams<GlobalLoginRestrictionDTO> pageParams) {
        return globalLoginRestrictionService.searchSimilar(pageParams.getParams().of(), Pages.initPage(pageParams));
    }
}
