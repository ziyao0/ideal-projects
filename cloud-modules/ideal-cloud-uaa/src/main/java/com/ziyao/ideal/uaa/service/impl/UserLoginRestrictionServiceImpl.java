package com.ziyao.ideal.uaa.service.impl;

import com.ziyao.ideal.uaa.domain.dto.UserLoginRestrictionDTO;
import com.ziyao.ideal.uaa.domain.entity.UserLoginRestriction;
import com.ziyao.ideal.uaa.repository.jpa.UserLoginRestrictionRepositoryJpa;
import com.ziyao.ideal.uaa.service.UserLoginRestrictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登录限制表 服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class UserLoginRestrictionServiceImpl implements UserLoginRestrictionService {


    private final UserLoginRestrictionRepositoryJpa userLoginRestrictionRepositoryJpa;

    /**
     * 保存 用户登录限制表
     *
     * @param userLoginRestrictionDTO 待存储对象
     */
    @Override
    public void save(UserLoginRestrictionDTO userLoginRestrictionDTO) {
        userLoginRestrictionRepositoryJpa.save(userLoginRestrictionDTO.toEntity());
    }

    /**
     * 通过主键修改 用户登录限制表
     *
     * @param userLoginRestrictionDTO 待修改对象
     */
    @Override
    public void updateById(UserLoginRestrictionDTO userLoginRestrictionDTO) {
        userLoginRestrictionRepositoryJpa.save(userLoginRestrictionDTO.toEntity());
    }

    /**
     * 通过主键删除 用户登录限制表
     *
     * @param id 主键id
     */
    @Override
    public void deleteById(Integer id) {
        userLoginRestrictionRepositoryJpa.deleteById(id);
    }

    /**
     * 分页查询 用户登录限制表
     *
     * @param userLoginRestrictionDTO 查询对象
     * @param pageable                分页对象
     * @return 返回分页对象
     */
    @Override
    public Page<UserLoginRestriction> page(UserLoginRestrictionDTO userLoginRestrictionDTO, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // Use CONTAINING for partial matches
        Example<UserLoginRestriction> example = Example.of(userLoginRestrictionDTO.toEntity(), matcher);
        return userLoginRestrictionRepositoryJpa.findAll(example, pageable);
    }
}