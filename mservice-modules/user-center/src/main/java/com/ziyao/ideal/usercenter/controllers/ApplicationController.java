package com.ziyao.ideal.usercenter.controllers;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ziyao.ideal.web.base.BaseController;
import com.ziyao.ideal.web.base.PageParams;
import com.ziyao.ideal.web.base.Pages;
import com.ziyao.ideal.web.exception.Exceptions;
import com.ziyao.ideal.usercenter.domain.dto.ApplicationDTO;
import com.ziyao.ideal.usercenter.domain.entity.Application;
import com.ziyao.ideal.usercenter.service.ApplicationService;
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
 * 应用系统 前端控制器
 * </p>
 *
 * @author zhangziyao
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/usercenter/application")
public class ApplicationController extends BaseController<ApplicationService, Application> {


    private final ApplicationService applicationService;

    @PostMapping("/save")
    public void save(@RequestBody ApplicationDTO entityDTO) {
        super.iService.save(entityDTO.getInstance());
    }

    @PostMapping("/saveOrUpdate")
    public void saveOrUpdate(@RequestBody ApplicationDTO entityDTO) {
        super.iService.saveOrUpdate(entityDTO.getInstance());
    }

    @PostMapping("/updateById")
    public void updateById(@RequestBody ApplicationDTO entityDTO) {
        if (ObjectUtils.isEmpty(entityDTO.getId())) {
            throw Exceptions.createIllegalArgumentException(null);
        }
        super.iService.updateById(entityDTO.getInstance());
    }

    /**
     * 默认一次插入500条
     */
    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List
            <ApplicationDTO> entityDTOList) {
        super.iService.saveBatch(entityDTOList.stream().map(ApplicationDTO::getInstance).collect(Collectors.toList()), 500);
    }

    /**
     * 条件分页查询
     *
     * @param pageQuery 分页参数
     * @return 返回分页查询信息
     */
    @PostMapping("/page/get")
    public Page<Application> getPage(@RequestBody PageParams<ApplicationDTO> pageQuery) {
        Page<Application> page = Pages.initPage(pageQuery, Application.class);
        return applicationService.page(page, pageQuery.getParams());
    }
}
