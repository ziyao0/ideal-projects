package com.ziyao.harbor.usercenter.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ziyao.harbor.usercenter.dto.DepartmentDTO;
import com.ziyao.harbor.usercenter.entity.Department;

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
