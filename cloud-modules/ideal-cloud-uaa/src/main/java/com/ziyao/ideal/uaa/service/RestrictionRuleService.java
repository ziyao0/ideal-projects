package com.ziyao.ideal.uaa.service;

import com.ziyao.ideal.uaa.domain.dto.RestrictionRuleDTO;
import com.ziyao.ideal.uaa.domain.entity.RestrictionRule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * <p>
 * 限制规则表 服务类
 * </p>
 *
 * @author ziyao
 */
public interface RestrictionRuleService {

    /**
     * 保存 限制规则表
     *
     * @param restrictionRuleDTO 待存储对象
     */
    void save(RestrictionRuleDTO restrictionRuleDTO);

    /**
     * 通过主键修改 限制规则表
     *
     * @param restrictionRuleDTO 待修改对象
     */
    void updateById(RestrictionRuleDTO restrictionRuleDTO);

    /**
     * 通过主键删除 限制规则表
     *
     * @param id 主键id
     */
    void deleteById(Integer id);

    /**
     * 分页查询 限制规则表
     *
     * @param restrictionRuleDTO 查询对象
     * @param pageable           分页对象
     * @return 返回分页对象
     */
    Page<RestrictionRule> page(RestrictionRuleDTO restrictionRuleDTO, Pageable pageable);
}
