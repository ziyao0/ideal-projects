package com.ziyao.ideal.config.controllers;

import com.ziyao.ideal.config.domain.dto.ConfigItemDTO;
import com.ziyao.ideal.config.domain.entity.ConfigItem;
import com.ziyao.ideal.config.service.ConfigItemService;
import com.ziyao.ideal.jpa.extension.controllers.JpaBaseController;
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
 * 前端控制器
 * </p>
 *
 * @author ziyao
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/configItem")
public class ConfigItemController extends JpaBaseController<ConfigItemService, ConfigItem, Integer> {

    private final ConfigItemService configItemService;

    @PostMapping("/save")
    public void save(@RequestBody ConfigItemDTO entityDTO) {
        configItemService.save(entityDTO.of());
    }

    @PostMapping("/updateById")
    public void updateById(@RequestBody ConfigItemDTO entityDTO) {
        if (ObjectUtils.isEmpty(entityDTO.getId())) {
            throw new ServiceException(400, "主键参数不能为空");
        }
        configItemService.save(entityDTO.of());
    }

    /**
     * 默认一次插入500条
     */
    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List<ConfigItemDTO> entityDTOList) {
        configItemService.saveBatch(entityDTOList.stream().map(ConfigItemDTO::of).collect(Collectors.toList()));
    }

    /**
     * 分页查询
     */
    @PostMapping("/searchSimilar")
    public Page<ConfigItem> list(PageParams<ConfigItemDTO> pageParams) {
        return configItemService.list(pageParams.getParams().of(), Pages.initPage(pageParams));
    }
}
