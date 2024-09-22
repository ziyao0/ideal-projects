package com.ziyao.ideal.uaa.service.impl;

import com.ziyao.ideal.uaa.domain.dto.RoleDTO;
import com.ziyao.ideal.uaa.domain.entity.Role;
import com.ziyao.ideal.uaa.repository.jpa.RoleRepositoryJpa;
import com.ziyao.ideal.uaa.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {


    private final RoleRepositoryJpa roleRepositoryJpa;

    /**
     * 保存 角色表
     *
     * @param roleDTO 待存储对象
     */
    @Override
    public void save(RoleDTO roleDTO) {
        roleRepositoryJpa.save(roleDTO.toEntity());
    }

    /**
     * 通过主键修改 角色表
     *
     * @param roleDTO 待修改对象
     */
    @Override
    public void updateById(RoleDTO roleDTO) {
        roleRepositoryJpa.save(roleDTO.toEntity());
    }

    /**
     * 通过主键删除 角色表
     *
     * @param id 主键id
     */
    @Override
    public void deleteById(Integer id) {
        roleRepositoryJpa.deleteById(id);
    }

    /**
     * 分页查询 角色表
     *
     * @param roleDTO  查询对象
     * @param pageable 分页对象
     * @return 返回分页对象
     */
    @Override
    public Page<Role> page(RoleDTO roleDTO, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // Use CONTAINING for partial matches
        Example<Role> example = Example.of(roleDTO.toEntity(), matcher);
        return roleRepositoryJpa.findAll(example, pageable);
    }
}