package com.ziyao.ideal.uaa.service.impl;

import com.ziyao.ideal.uaa.domain.dto.LoginRecordDTO;
import com.ziyao.ideal.uaa.domain.entity.LoginRecord;
import com.ziyao.ideal.uaa.repository.jpa.LoginRecordRepositoryJpa;
import com.ziyao.ideal.uaa.service.LoginRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 登录记录表 服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class LoginRecordServiceImpl implements LoginRecordService {


    private final LoginRecordRepositoryJpa loginRecordRepositoryJpa;

    /**
     * 保存 登录记录表
     *
     * @param loginRecordDTO 待存储对象
     */
    @Override
    public void save(LoginRecordDTO loginRecordDTO) {
        loginRecordRepositoryJpa.save(loginRecordDTO.toEntity());
    }

    /**
     * 通过主键修改 登录记录表
     *
     * @param loginRecordDTO 待修改对象
     */
    @Override
    public void updateById(LoginRecordDTO loginRecordDTO) {
        loginRecordRepositoryJpa.save(loginRecordDTO.toEntity());
    }

    /**
     * 通过主键删除 登录记录表
     *
     * @param id 主键id
     */
    @Override
    public void deleteById(Integer id) {
        loginRecordRepositoryJpa.deleteById(id);
    }

    /**
     * 分页查询 登录记录表
     *
     * @param loginRecordDTO 查询对象
     * @param pageable       分页对象
     * @return 返回分页对象
     */
    @Override
    public Page<LoginRecord> page(LoginRecordDTO loginRecordDTO, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // Use CONTAINING for partial matches
        Example<LoginRecord> example = Example.of(loginRecordDTO.toEntity(), matcher);
        return loginRecordRepositoryJpa.findAll(example, pageable);
    }
}