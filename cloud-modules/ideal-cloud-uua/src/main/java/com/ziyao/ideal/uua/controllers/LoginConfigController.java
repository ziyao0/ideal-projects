package com.ziyao.ideal.uua.controllers;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ziyao.ideal.uua.domain.dto.LoginConfigDTO;
import com.ziyao.ideal.uua.domain.entity.LoginConfig;
import com.ziyao.ideal.uua.service.LoginConfigService;
import com.ziyao.ideal.web.base.BaseController;
import com.ziyao.ideal.web.base.PageParams;
import com.ziyao.ideal.web.base.Pages;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 登录配置表 前端控制器
 * </p>
 *
 * @author ziyao
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/login-config")
public class LoginConfigController extends BaseController<LoginConfigService, LoginConfig> {

    private final LoginConfigService loginConfigService;

    @PostMapping("/save")
    public void save(@RequestBody LoginConfigDTO entityDTO) {
        super.iService.save(entityDTO.getInstance());
    }

    @PostMapping("/saveOrUpdate")
    public void saveOrUpdate(@RequestBody LoginConfigDTO entityDTO) {
        super.iService.saveOrUpdate(entityDTO.getInstance());
    }

    @PostMapping("/updateById")
    public void updateById(@RequestBody LoginConfigDTO entityDTO) {
        if (ObjectUtils.isEmpty(entityDTO.getId())) {
//            throw Exceptions.createIllegalArgumentException(null);
        }
        super.iService.updateById(entityDTO.getInstance());
    }

    /**
     * 默认一次插入500条
     */
    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List
            <LoginConfigDTO> entityDTOList) {
        super.iService.saveBatch(entityDTOList.stream().map(LoginConfigDTO::getInstance).collect(Collectors.toList()), 500);
    }

    /**
     * 条件分页查询
     *
     * @param pageQuery 分页参数
     * @return 返回分页查询信息
     */
    @PostMapping("/page/get")
    public Page<LoginConfig> getPage(@RequestBody PageParams<LoginConfigDTO> pageQuery) {
        Page<LoginConfig> page = Pages.initPage(pageQuery, LoginConfig.class);
        return loginConfigService.page(page, pageQuery.getParams());
    }
}
