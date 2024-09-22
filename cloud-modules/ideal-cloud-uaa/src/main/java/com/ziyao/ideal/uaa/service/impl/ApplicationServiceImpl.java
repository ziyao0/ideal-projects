package com.ziyao.ideal.uaa.service.impl;

import com.ziyao.ideal.uaa.domain.dto.ApplicationDTO;
import com.ziyao.ideal.uaa.domain.entity.Application;
import com.ziyao.ideal.uaa.repository.jpa.ApplicationRepositoryJpa;
import com.ziyao.ideal.uaa.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 应用系统 服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {


    private final ApplicationRepositoryJpa applicationRepositoryJpa;

    /**
     * 保存 应用系统
     *
     * @param applicationDTO 待存储对象
     */
    @Override
    public void save(ApplicationDTO applicationDTO) {
        applicationRepositoryJpa.save(applicationDTO.toEntity());
    }

    /**
     * 通过主键修改 应用系统
     *
     * @param applicationDTO 待修改对象
     */
    @Override
    public void updateById(ApplicationDTO applicationDTO) {
        applicationRepositoryJpa.save(applicationDTO.toEntity());
    }

    /**
     * 通过主键删除 应用系统
     *
     * @param id 主键id
     */
    @Override
    public void deleteById(Integer id) {
        applicationRepositoryJpa.deleteById(id);
    }

    /**
     * 分页查询 应用系统
     *
     * @param applicationDTO 查询对象
     * @param pageable       分页对象
     * @return 返回分页对象
     */
    @Override
    public Page<Application> page(ApplicationDTO applicationDTO, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // Use CONTAINING for partial matches
        Example<Application> example = Example.of(applicationDTO.toEntity(), matcher);
        return applicationRepositoryJpa.findAll(example, pageable);
    }
}