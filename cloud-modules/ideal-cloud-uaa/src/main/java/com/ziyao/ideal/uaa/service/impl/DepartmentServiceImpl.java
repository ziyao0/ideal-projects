package com.ziyao.ideal.uaa.service.impl;

import com.ziyao.ideal.uaa.domain.dto.DepartmentDTO;
import com.ziyao.ideal.uaa.domain.entity.Department;
import com.ziyao.ideal.uaa.repository.jpa.DepartmentRepositoryJpa;
import com.ziyao.ideal.uaa.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {


    private final DepartmentRepositoryJpa departmentRepositoryJpa;

    /**
     * 保存 部门表
     *
     * @param departmentDTO 待存储对象
     */
    @Override
    public void save(DepartmentDTO departmentDTO) {
        departmentRepositoryJpa.save(departmentDTO.toEntity());
    }

    /**
     * 通过主键修改 部门表
     *
     * @param departmentDTO 待修改对象
     */
    @Override
    public void updateById(DepartmentDTO departmentDTO) {
        departmentRepositoryJpa.save(departmentDTO.toEntity());
    }

    /**
     * 通过主键删除 部门表
     *
     * @param id 主键id
     */
    @Override
    public void deleteById(Integer id) {
        departmentRepositoryJpa.deleteById(id);
    }

    /**
     * 分页查询 部门表
     *
     * @param departmentDTO 查询对象
     * @param pageable      分页对象
     * @return 返回分页对象
     */
    @Override
    public Page<Department> page(DepartmentDTO departmentDTO, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // Use CONTAINING for partial matches
        Example<Department> example = Example.of(departmentDTO.toEntity(), matcher);
        return departmentRepositoryJpa.findAll(example, pageable);
    }
}