package com.ziyao.ideal.usercenter.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ziyao.ideal.usercenter.dto.DepartmentDTO;
import com.ziyao.ideal.usercenter.entity.Department;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author zhangziyao
 */
public interface DepartmentService extends IService<Department> {

    /**
     * 分页查询
     */
    Page<Department> page(Page<Department> page, DepartmentDTO departmentDTO);
}
