package com.ziyao.ideal.uua.domain.dto;

import com.ziyao.ideal.uua.domain.convertor.GlobalLoginRestrictionConvertor;
import com.ziyao.ideal.uua.domain.entity.GlobalLoginRestriction;
import com.ziyao.ideal.web.orm.EntityDTO;
import lombok.Data;


import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 全局登录限制表
 * </p>
 *
 * @author ziyao
 */
@Data
public class GlobalLoginRestrictionDTO implements EntityDTO<GlobalLoginRestriction>, Serializable {

    
    private static final long serialVersionUID = 1L;

    /**
     * 记录ID
     */
    private Integer id;
    /**
     * 限制规则ID，关联restriction_rules表
     */
    private Integer ruleId;
    /**
     * 限制开始时间
     */
    private LocalDateTime restrictionStart;
    /**
     * 限制结束时间
     */
    private LocalDateTime restrictionEnd;
    /**
     * 限制原因
     */
    private String reason;
    /**
     * 限制状态（例如：ACTIVE, INACTIVE）
     */
    private String status;
    /**
     * 记录创建时间
     */
    private LocalDateTime createdAt;
    /**
     * 记录更新时间
     */
    private LocalDateTime updatedAt;

    public GlobalLoginRestriction convert() {
        return GlobalLoginRestrictionConvertor.INSTANCE.convert(this);
    }
}
