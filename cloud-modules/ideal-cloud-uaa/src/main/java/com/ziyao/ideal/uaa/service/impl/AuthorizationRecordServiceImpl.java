package com.ziyao.ideal.uaa.service.impl;

import com.ziyao.ideal.uaa.domain.dto.AuthorizationRecordDTO;
import com.ziyao.ideal.uaa.domain.entity.AuthorizationRecord;
import com.ziyao.ideal.uaa.repository.jpa.AuthorizationRecordRepositoryJpa;
import com.ziyao.ideal.uaa.service.AuthorizationRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 授权记录表 服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class AuthorizationRecordServiceImpl implements AuthorizationRecordService {


    private final AuthorizationRecordRepositoryJpa authorizationRecordRepositoryJpa;

    /**
     * 保存 授权记录表
     *
     * @param authorizationRecordDTO 待存储对象
     */
    @Override
    public void save(AuthorizationRecordDTO authorizationRecordDTO) {
        authorizationRecordRepositoryJpa.save(authorizationRecordDTO.toEntity());
    }

    /**
     * 通过主键修改 授权记录表
     *
     * @param authorizationRecordDTO 待修改对象
     */
    @Override
    public void updateById(AuthorizationRecordDTO authorizationRecordDTO) {
        authorizationRecordRepositoryJpa.save(authorizationRecordDTO.toEntity());
    }

    /**
     * 通过主键删除 授权记录表
     *
     * @param id 主键id
     */
    @Override
    public void deleteById(Integer id) {
        authorizationRecordRepositoryJpa.deleteById(id);
    }

    /**
     * 分页查询 授权记录表
     *
     * @param authorizationRecordDTO 查询对象
     * @param pageable               分页对象
     * @return 返回分页对象
     */
    @Override
    public Page<AuthorizationRecord> page(AuthorizationRecordDTO authorizationRecordDTO, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // Use CONTAINING for partial matches
        Example<AuthorizationRecord> example = Example.of(authorizationRecordDTO.toEntity(), matcher);
        return authorizationRecordRepositoryJpa.findAll(example, pageable);
    }
}