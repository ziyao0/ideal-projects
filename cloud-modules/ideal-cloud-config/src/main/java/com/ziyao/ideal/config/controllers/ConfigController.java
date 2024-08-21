package com.ziyao.ideal.config.controllers;

import com.ziyao.ideal.config.domain.dto.ConfigDTO;
import com.ziyao.ideal.config.domain.entity.Config;
import com.ziyao.ideal.config.service.ConfigService;
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
@RequestMapping("/config")
public class ConfigController extends JpaBaseController<ConfigService, Config, Integer> {

    private final ConfigService configService;

    @PostMapping("/save")
    public void save(@RequestBody ConfigDTO entityDTO) {
        configService.save(entityDTO.of());
    }

    @PostMapping("/updateById")
    public void updateById(@RequestBody ConfigDTO entityDTO) {
        if (ObjectUtils.isEmpty(entityDTO.getId())) {
            throw new ServiceException(400, "主键参数不能为空");
        }
        configService.save(entityDTO.of());
    }

    /**
     * 默认一次插入500条
     */
    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List<ConfigDTO> entityDTOList) {
        configService.saveBatch(entityDTOList.stream().map(ConfigDTO::of).collect(Collectors.toList()));
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    public Page<Config> list(PageParams<ConfigDTO> pageParams) {
        return configService.list(pageParams.getParams().of(), Pages.initPage(pageParams));
    }
}
