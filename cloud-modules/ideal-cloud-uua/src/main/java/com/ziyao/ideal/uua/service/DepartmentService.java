package com.ziyao.ideal.uua.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ziyao.ideal.uua.domain.dto.DepartmentDTO;
import com.ziyao.ideal.uua.domain.entity.Department;

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
