package com.ziyao.ideal.uua.domain.dto;

import com.ziyao.ideal.uua.domain.convertor.RestrictionRuleConvertor;
import com.ziyao.ideal.uua.domain.entity.RestrictionRule;
import com.ziyao.ideal.web.orm.EntityDTO;
import lombok.Data;


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

    public RestrictionRule convert() {
        return RestrictionRuleConvertor.INSTANCE.convert(this);
    }
}
