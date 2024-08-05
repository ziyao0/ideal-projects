package com.ziyao.ideal.config.controllers;

import com.ziyao.ideal.config.domain.dto.ConfigDTO;
import com.ziyao.ideal.config.domain.entity.Config;
import com.ziyao.ideal.config.service.ConfigService;
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
 *  前端控制器
 * </p>
 *
 * @author ziyao
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/config")
public class ConfigController extends JpaBaseController<ConfigService, Config,Integer> {

private final ConfigService configService;

    @PostMapping("/save")
    public void save(@RequestBody ConfigDTO entityDTO) {
    configService.save(entityDTO.getInstance());
    }

    @PostMapping("/updateById")
    public void updateById(@RequestBody ConfigDTO entityDTO) {
        if (ObjectUtils.isEmpty(entityDTO.getId())) {
            throw new ServiceException(400, "主键参数不能为空");
        }
        configService.save(entityDTO.getInstance());
    }

    /**
    * 默认一次插入500条
    */
    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List<ConfigDTO> entityDTOList) {
    configService.saveAll(entityDTOList.stream().map(ConfigDTO::getInstance).collect(Collectors.toList()));
    }

    /**
    * 分页查询
    */
    @PostMapping("/searchSimilar")
    public Page<Config> searchSimilar(PageParams<ConfigDTO> pageParams) {
        return configService.searchSimilar(pageParams.getParams().getInstance(), Pages.initPage(pageParams));
    }
}
