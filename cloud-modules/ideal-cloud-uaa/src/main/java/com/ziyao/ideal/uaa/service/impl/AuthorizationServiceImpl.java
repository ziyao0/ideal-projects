package com.ziyao.ideal.uaa.service.impl;

import com.ziyao.ideal.uaa.domain.dto.AuthorizationDTO;
import com.ziyao.ideal.uaa.domain.entity.Authorization;
import com.ziyao.ideal.uaa.repository.jpa.AuthorizationRepositoryJpa;
import com.ziyao.ideal.uaa.service.AuthorizationService;
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
public class AuthorizationServiceImpl implements AuthorizationService {


    private final AuthorizationRepositoryJpa authorizationRepositoryJpa;

    /**
     * 保存
     *
     * @param authorizationDTO 待存储对象
     */
    @Override
    public void save(AuthorizationDTO authorizationDTO) {
        authorizationRepositoryJpa.save(authorizationDTO.toEntity());
    }

    /**
     * 通过主键修改
     *
     * @param authorizationDTO 待修改对象
     */
    @Override
    public void updateById(AuthorizationDTO authorizationDTO) {
        authorizationRepositoryJpa.save(authorizationDTO.toEntity());
    }

    /**
     * 通过主键删除
     *
     * @param id 主键id
     */
    @Override
    public void deleteById(Integer id) {
        authorizationRepositoryJpa.deleteById(id);
    }

    /**
     * 分页查询
     *
     * @param authorizationDTO 查询对象
     * @param pageable         分页对象
     * @return 返回分页对象
     */
    @Override
    public Page<Authorization> page(AuthorizationDTO authorizationDTO, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // Use CONTAINING for partial matches
        Example<Authorization> example = Example.of(authorizationDTO.toEntity(), matcher);
        return authorizationRepositoryJpa.findAll(example, pageable);
    }
}