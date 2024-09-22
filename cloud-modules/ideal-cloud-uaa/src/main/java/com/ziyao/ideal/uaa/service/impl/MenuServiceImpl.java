package com.ziyao.ideal.uaa.service.impl;

import com.ziyao.ideal.uaa.domain.dto.MenuDTO;
import com.ziyao.ideal.uaa.domain.entity.Menu;
import com.ziyao.ideal.uaa.repository.jpa.MenuRepositoryJpa;
import com.ziyao.ideal.uaa.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单资源表 服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {


    private final MenuRepositoryJpa menuRepositoryJpa;

    /**
     * 保存 菜单资源表
     *
     * @param menuDTO 待存储对象
     */
    @Override
    public void save(MenuDTO menuDTO) {
        menuRepositoryJpa.save(menuDTO.toEntity());
    }

    /**
     * 通过主键修改 菜单资源表
     *
     * @param menuDTO 待修改对象
     */
    @Override
    public void updateById(MenuDTO menuDTO) {
        menuRepositoryJpa.save(menuDTO.toEntity());
    }

    /**
     * 通过主键删除 菜单资源表
     *
     * @param id 主键id
     */
    @Override
    public void deleteById(Integer id) {
        menuRepositoryJpa.deleteById(id);
    }

    /**
     * 分页查询 菜单资源表
     *
     * @param menuDTO  查询对象
     * @param pageable 分页对象
     * @return 返回分页对象
     */
    @Override
    public Page<Menu> page(MenuDTO menuDTO, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // Use CONTAINING for partial matches
        Example<Menu> example = Example.of(menuDTO.toEntity(), matcher);
        return menuRepositoryJpa.findAll(example, pageable);
    }
}