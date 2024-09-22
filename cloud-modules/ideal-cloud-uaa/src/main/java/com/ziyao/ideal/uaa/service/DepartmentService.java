package com.ziyao.ideal.uaa.service;

import com.ziyao.ideal.uaa.domain.dto.DepartmentDTO;
import com.ziyao.ideal.uaa.domain.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author ziyao
 */
public interface DepartmentService {

    /**
     * 保存 部门表
     *
     * @param departmentDTO 待存储对象
     */
    void save(DepartmentDTO departmentDTO);

    /**
     * 通过主键修改 部门表
     *
     * @param departmentDTO 待修改对象
     */
    void updateById(DepartmentDTO departmentDTO);

    /**
     * 通过主键删除 部门表
     *
     * @param id 主键id
     */
    void deleteById(Integer id);

    /**
     * 分页查询 部门表
     *
     * @param departmentDTO 查询对象
     * @param pageable      分页对象
     * @return 返回分页对象
     */
    Page<Department> page(DepartmentDTO departmentDTO, Pageable pageable);
}
