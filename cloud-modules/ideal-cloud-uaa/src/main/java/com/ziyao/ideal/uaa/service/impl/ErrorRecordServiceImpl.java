package com.ziyao.ideal.uaa.service.impl;

import com.ziyao.ideal.uaa.domain.dto.ErrorRecordDTO;
import com.ziyao.ideal.uaa.domain.entity.ErrorRecord;
import com.ziyao.ideal.uaa.repository.jpa.ErrorRecordRepositoryJpa;
import com.ziyao.ideal.uaa.service.ErrorRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 异常信息记录表 服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class ErrorRecordServiceImpl implements ErrorRecordService {


    private final ErrorRecordRepositoryJpa errorRecordRepositoryJpa;

    /**
     * 保存 异常信息记录表
     *
     * @param errorRecordDTO 待存储对象
     */
    @Override
    public void save(ErrorRecordDTO errorRecordDTO) {
        errorRecordRepositoryJpa.save(errorRecordDTO.toEntity());
    }

    /**
     * 通过主键修改 异常信息记录表
     *
     * @param errorRecordDTO 待修改对象
     */
    @Override
    public void updateById(ErrorRecordDTO errorRecordDTO) {
        errorRecordRepositoryJpa.save(errorRecordDTO.toEntity());
    }

    /**
     * 通过主键删除 异常信息记录表
     *
     * @param id 主键id
     */
    @Override
    public void deleteById(Long id) {
        errorRecordRepositoryJpa.deleteById(id);
    }

    /**
     * 分页查询 异常信息记录表
     *
     * @param errorRecordDTO 查询对象
     * @param pageable       分页对象
     * @return 返回分页对象
     */
    @Override
    public Page<ErrorRecord> page(ErrorRecordDTO errorRecordDTO, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // Use CONTAINING for partial matches
        Example<ErrorRecord> example = Example.of(errorRecordDTO.toEntity(), matcher);
        return errorRecordRepositoryJpa.findAll(example, pageable);
    }
}