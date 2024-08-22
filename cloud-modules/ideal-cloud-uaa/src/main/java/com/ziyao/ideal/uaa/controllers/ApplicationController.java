package com.ziyao.ideal.uaa.controllers;

import com.ziyao.ideal.uaa.domain.dto.ApplicationDTO;
import com.ziyao.ideal.uaa.domain.entity.Application;
import com.ziyao.ideal.uaa.service.ApplicationService;
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
 * 应用系统 前端控制器
 * </p>
 *
 * @author ziyao
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
public class ApplicationController extends JpaBaseController<ApplicationService, Application, Integer> {

    private final ApplicationService applicationService;

    @PostMapping("/save")
    public void save(@RequestBody ApplicationDTO entityDTO) {
        applicationService.save(entityDTO.convert());
    }

    /**
     * 通过主键id进行更新
     */
    @PostMapping("/updateById")
    public void updateById(@RequestBody ApplicationDTO entityDTO) {
        if (ObjectUtils.isEmpty(entityDTO.getId())) {
            throw new ServiceException(400, "主键参数不能为空");
        }
        applicationService.save(entityDTO.convert());
    }

    /**
    * 通过id删除数据，有逻辑删除按照逻辑删除执行
    * <p>不支持联合主键</p>
    *
    * @param id 主键Id
    */
    @GetMapping("/remove/{id}")
    public void removeById(@PathVariable("id") Integer id) {
        applicationService.deleteById(id);
    }
    /**
     * 默认一次插入500条
     */
    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List<ApplicationDTO> entityDTOList) {
        applicationService.saveBatch(entityDTOList.stream().map(ApplicationDTO::convert).collect(Collectors.toList()));
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    public Page<Application> list(PageParams<ApplicationDTO> pageParams) {
        return applicationService.list(pageParams.getParams().convert(), Pages.initPage(pageParams));
    }
}
