package com.ziyao.ideal.uaa.service.impl;

import com.ziyao.ideal.uaa.domain.dto.RestrictionRuleDTO;
import com.ziyao.ideal.uaa.domain.entity.RestrictionRule;
import com.ziyao.ideal.uaa.repository.jpa.RestrictionRuleRepositoryJpa;
import com.ziyao.ideal.uaa.service.RestrictionRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 限制规则表 服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class RestrictionRuleServiceImpl implements RestrictionRuleService {


    private final RestrictionRuleRepositoryJpa restrictionRuleRepositoryJpa;

    /**
     * 保存 限制规则表
     *
     * @param restrictionRuleDTO 待存储对象
     */
    @Override
    public void save(RestrictionRuleDTO restrictionRuleDTO) {
        restrictionRuleRepositoryJpa.save(restrictionRuleDTO.toEntity());
    }

    /**
     * 通过主键修改 限制规则表
     *
     * @param restrictionRuleDTO 待修改对象
     */
    @Override
    public void updateById(RestrictionRuleDTO restrictionRuleDTO) {
        restrictionRuleRepositoryJpa.save(restrictionRuleDTO.toEntity());
    }

    /**
     * 通过主键删除 限制规则表
     *
     * @param id 主键id
     */
    @Override
    public void deleteById(Integer id) {
        restrictionRuleRepositoryJpa.deleteById(id);
    }

    /**
     * 分页查询 限制规则表
     *
     * @param restrictionRuleDTO 查询对象
     * @param pageable           分页对象
     * @return 返回分页对象
     */
    @Override
    public Page<RestrictionRule> page(RestrictionRuleDTO restrictionRuleDTO, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // Use CONTAINING for partial matches
        Example<RestrictionRule> example = Example.of(restrictionRuleDTO.toEntity(), matcher);
        return restrictionRuleRepositoryJpa.findAll(example, pageable);
    }
}