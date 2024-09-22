package com.ziyao.ideal.uaa.service.impl;

import com.ziyao.ideal.uaa.domain.dto.RoleMenuDTO;
import com.ziyao.ideal.uaa.domain.entity.RoleMenu;
import com.ziyao.ideal.uaa.repository.jpa.RoleMenuRepositoryJpa;
import com.ziyao.ideal.uaa.service.RoleMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class RoleMenuServiceImpl implements RoleMenuService {


    private final RoleMenuRepositoryJpa roleMenuRepositoryJpa;

    /**
     * 保存 角色菜单表
     *
     * @param roleMenuDTO 待存储对象
     */
    @Override
    public void save(RoleMenuDTO roleMenuDTO) {
        roleMenuRepositoryJpa.save(roleMenuDTO.toEntity());
    }

    /**
     * 通过主键修改 角色菜单表
     *
     * @param roleMenuDTO 待修改对象
     */
    @Override
    public void updateById(RoleMenuDTO roleMenuDTO) {
        roleMenuRepositoryJpa.save(roleMenuDTO.toEntity());
    }

    /**
     * 通过主键删除 角色菜单表
     *
     * @param id 主键id
     */
    @Override
    public void deleteById(Integer id) {
        roleMenuRepositoryJpa.deleteById(id);
    }

    /**
     * 分页查询 角色菜单表
     *
     * @param roleMenuDTO 查询对象
     * @param pageable    分页对象
     * @return 返回分页对象
     */
    @Override
    public Page<RoleMenu> page(RoleMenuDTO roleMenuDTO, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // Use CONTAINING for partial matches
        Example<RoleMenu> example = Example.of(roleMenuDTO.toEntity(), matcher);
        return roleMenuRepositoryJpa.findAll(example, pageable);
    }
}