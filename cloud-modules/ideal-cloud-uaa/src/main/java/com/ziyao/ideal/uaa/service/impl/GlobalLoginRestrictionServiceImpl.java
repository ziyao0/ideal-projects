package com.ziyao.ideal.uaa.service.impl;

import com.ziyao.ideal.uaa.domain.dto.GlobalLoginRestrictionDTO;
import com.ziyao.ideal.uaa.domain.entity.GlobalLoginRestriction;
import com.ziyao.ideal.uaa.repository.jpa.GlobalLoginRestrictionRepositoryJpa;
import com.ziyao.ideal.uaa.service.GlobalLoginRestrictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 全局登录限制表 服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class GlobalLoginRestrictionServiceImpl implements GlobalLoginRestrictionService {


    private final GlobalLoginRestrictionRepositoryJpa globalLoginRestrictionRepositoryJpa;

    /**
     * 保存 全局登录限制表
     *
     * @param globalLoginRestrictionDTO 待存储对象
     */
    @Override
    public void save(GlobalLoginRestrictionDTO globalLoginRestrictionDTO) {
        globalLoginRestrictionRepositoryJpa.save(globalLoginRestrictionDTO.toEntity());
    }

    /**
     * 通过主键修改 全局登录限制表
     *
     * @param globalLoginRestrictionDTO 待修改对象
     */
    @Override
    public void updateById(GlobalLoginRestrictionDTO globalLoginRestrictionDTO) {
        globalLoginRestrictionRepositoryJpa.save(globalLoginRestrictionDTO.toEntity());
    }

    /**
     * 通过主键删除 全局登录限制表
     *
     * @param id 主键id
     */
    @Override
    public void deleteById(Integer id) {
        globalLoginRestrictionRepositoryJpa.deleteById(id);
    }

    /**
     * 分页查询 全局登录限制表
     *
     * @param globalLoginRestrictionDTO 查询对象
     * @param pageable                  分页对象
     * @return 返回分页对象
     */
    @Override
    public Page<GlobalLoginRestriction> page(GlobalLoginRestrictionDTO globalLoginRestrictionDTO, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // Use CONTAINING for partial matches
        Example<GlobalLoginRestriction> example = Example.of(globalLoginRestrictionDTO.toEntity(), matcher);
        return globalLoginRestrictionRepositoryJpa.findAll(example, pageable);
    }
}