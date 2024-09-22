package com.ziyao.ideal.uaa.service.impl;

import com.ziyao.ideal.uaa.domain.dto.UserRoleDTO;
import com.ziyao.ideal.uaa.domain.entity.UserRole;
import com.ziyao.ideal.uaa.repository.jpa.UserRoleRepositoryJpa;
import com.ziyao.ideal.uaa.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {


    private final UserRoleRepositoryJpa userRoleRepositoryJpa;

    /**
     * 保存
     *
     * @param userRoleDTO 待存储对象
     */
    @Override
    public void save(UserRoleDTO userRoleDTO) {
        userRoleRepositoryJpa.save(userRoleDTO.toEntity());
    }

    /**
     * 通过主键修改
     *
     * @param userRoleDTO 待修改对象
     */
    @Override
    public void updateById(UserRoleDTO userRoleDTO) {
        userRoleRepositoryJpa.save(userRoleDTO.toEntity());
    }

    /**
     * 通过主键删除
     *
     * @param id 主键id
     */
    @Override
    public void deleteById(Integer id) {
        userRoleRepositoryJpa.deleteById(id);
    }

    /**
     * 分页查询
     *
     * @param userRoleDTO 查询对象
     * @param pageable    分页对象
     * @return 返回分页对象
     */
    @Override
    public Page<UserRole> page(UserRoleDTO userRoleDTO, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // Use CONTAINING for partial matches
        Example<UserRole> example = Example.of(userRoleDTO.toEntity(), matcher);
        return userRoleRepositoryJpa.findAll(example, pageable);
    }
}