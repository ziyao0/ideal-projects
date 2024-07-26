package com.ziyao.ideal.usercenter.controllers;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ziyao.ideal.web.base.BaseController;
import com.ziyao.ideal.web.base.PageParams;
import com.ziyao.ideal.web.base.Pages;
import com.ziyao.ideal.web.exception.Exceptions;
import com.ziyao.ideal.usercenter.domain.dto.DepartmentDTO;
import com.ziyao.ideal.usercenter.domain.entity.Department;
import com.ziyao.ideal.usercenter.service.DepartmentService;
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
 * 部门表 前端控制器
 * </p>
 *
 * @author zhangziyao
 */
@RestController
@RequestMapping("/usercenter/department")
@RequiredArgsConstructor
public class DepartmentController extends BaseController<DepartmentService, Department> {

    private final DepartmentService departmentService;

    @PostMapping("/save")
    public void save(@RequestBody DepartmentDTO entityDTO) {
        super.iService.save(entityDTO.getInstance());
    }

    @PostMapping("/saveOrUpdate")
    public void saveOrUpdate(@RequestBody DepartmentDTO entityDTO) {
        super.iService.saveOrUpdate(entityDTO.getInstance());
    }

    @PostMapping("/updateById")
    public void updateById(@RequestBody DepartmentDTO entityDTO) {
        if (ObjectUtils.isEmpty(entityDTO.getId())) {
            throw Exceptions.createIllegalArgumentException(null);
        }
        super.iService.updateById(entityDTO.getInstance());
    }

    /**
     * 默认一次插入500条
     */
    @PostMapping("/saveBatch")
    public void saveBatch(@RequestBody List<DepartmentDTO> entityDTOList) {
        super.iService.saveBatch(entityDTOList.stream().map(DepartmentDTO::getInstance).collect(Collectors.toList()), 500);
    }

    /**
     * 条件分页查询
     *
     * @param pageParams 分页参数
     * @return 返回分页查询信息
     */
    @PostMapping("/page/get")
    public Page<Department> getPage(@RequestBody PageParams<DepartmentDTO> pageParams) {
        Page<Department> page = Pages.initPage(pageParams, Department.class);
        return departmentService.page(page, pageParams.getParams());
    }
}
