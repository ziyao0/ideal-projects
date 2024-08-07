package com.ziyao.ideal.uua.domain.dto;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ziyao.ideal.web.orm.EntityDTO;
import com.ziyao.ideal.uua.domain.entity.RestrictionRule;
import com.ziyao.ideal.uua.domain.mapstruct.RestrictionRuleMapstruct;
import lombok.Data;
import java.util.Objects;
import com.ziyao.ideal.core.Strings;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 限制规则表
 * </p>
 *
 * @author ziyao
 */
@Data
public class RestrictionRuleDTO implements EntityDTO<RestrictionRule>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 规则ID
     */
    private Integer id;
    /**
     * 规则名称
     */
    private String ruleName;
    /**
     * 规则类型（例如：IP_BLOCK, LOGIN_ATTEMPT_LIMIT等）
     */
    private String ruleType;
    /**
     * 规则参数，存储具体的规则配置，以JSON格式记录
     */
    private String parameters;
    /**
     * 规则描述
     */
    private String description;
    /**
     * 规则创建时间
     */
    private LocalDateTime createdAt;
    /**
     * 规则更新时间
     */
    private LocalDateTime updatedAt;

    /**
     * 组装查询条件，可根据具体情况做出修改
     *
     * @see LambdaQueryWrapper
     */
    public LambdaQueryWrapper<RestrictionRule> initWrapper() {

        return Wrappers.lambdaQuery(RestrictionRule.class)
                // 规则名称
                .likeRight(Strings.hasLength(ruleName), RestrictionRule::getRuleName, ruleName)
                // 规则类型（例如：IP_BLOCK, LOGIN_ATTEMPT_LIMIT等）
                .likeRight(Strings.hasLength(ruleType), RestrictionRule::getRuleType, ruleType)
                // 规则参数，存储具体的规则配置，以JSON格式记录
                .likeRight(Strings.hasLength(parameters), RestrictionRule::getParameters, parameters)
                // 规则描述
                .likeRight(Strings.hasLength(description), RestrictionRule::getDescription, description)
                // 规则创建时间
                .eq(Objects.nonNull(createdAt), RestrictionRule::getCreatedAt, createdAt)
                // 规则更新时间
                .eq(Objects.nonNull(updatedAt), RestrictionRule::getUpdatedAt, updatedAt)
                ;
    }

    public RestrictionRule of() {
        return RestrictionRuleMapstruct.INSTANCE.of(this);
    }
}
