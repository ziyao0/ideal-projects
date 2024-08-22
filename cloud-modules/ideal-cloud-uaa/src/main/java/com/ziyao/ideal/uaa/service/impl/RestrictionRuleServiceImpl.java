package com.ziyao.ideal.uaa.service.impl;

import com.ziyao.ideal.uaa.repository.jpa.RestrictionRuleRepositoryJpa;
import com.ziyao.ideal.jpa.extension.service.impl.JapServiceImpl;
import com.ziyao.ideal.uaa.domain.entity.RestrictionRule;
import com.ziyao.ideal.uaa.service.RestrictionRuleService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
/**
* <p>
    * 限制规则表 服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class RestrictionRuleServiceImpl extends
    JapServiceImpl< RestrictionRuleRepositoryJpa, RestrictionRule,Integer> implements RestrictionRuleService {

    private final RestrictionRuleRepositoryJpa restrictionRuleRepositoryJpa;

}
