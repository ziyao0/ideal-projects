package com.ziyao.ideal.uua.controllers;

import com.ziyao.ideal.uua.domain.dto.LoginConfigDTO;
import com.ziyao.ideal.uua.domain.entity.LoginConfig;
import com.ziyao.ideal.uua.service.LoginConfigService;
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
 * 登录配置表 前端控制器
 * </p>
 *
 * @author ziyao
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/loginConfig")
public class LoginConfigController extends JpaBaseController<LoginConfigService, LoginConfig, Long> {

    private final LoginConfigService loginConfigService;

    @PostMapping("/save")
    public void save(@RequestBody LoginConfigDTO entityDTO) {
        loginConfigService.save(entityDTO.of());
    }

    /**
     * 通过主键id进行更新
     */
    @PostMapping("/updateById")
    public void updateById(@RequestBody LoginConfigDTO entityDTO) {
        if (ObjectUtils.isEmpty(entityDTO.getId())) {
            throw new ServiceException(400, "主键参数不能为空");
        }
        loginConfigService.save(entityDTO.of());
    }

    /**
     * 默认一次插入500条
     */
    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List<LoginConfigDTO> entityDTOList) {
        loginConfigService.saveBatch(entityDTOList.stream().map(LoginConfigDTO::of).collect(Collectors.toList()));
    }

    /**
     * 分页查询
     */
    @PostMapping("/searchSimilar")
    public Page<LoginConfig> searchSimilar(PageParams<LoginConfigDTO> pageParams) {
        return loginConfigService.searchSimilar(pageParams.getParams().of(), Pages.initPage(pageParams));
    }
}
