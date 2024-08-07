package com.ziyao.ideal.uua.domain.dto;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ziyao.ideal.web.orm.EntityDTO;
import com.ziyao.ideal.uua.domain.entity.GlobalLoginRestriction;
import com.ziyao.ideal.uua.domain.mapstruct.GlobalLoginRestrictionMapstruct;
import lombok.Data;
import java.util.Objects;
import com.ziyao.ideal.core.Strings;

import java.io.Serial;
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

    @Serial
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

    /**
     * 组装查询条件，可根据具体情况做出修改
     *
     * @see LambdaQueryWrapper
     */
    public LambdaQueryWrapper<GlobalLoginRestriction> initWrapper() {

        return Wrappers.lambdaQuery(GlobalLoginRestriction.class)
                // 限制规则ID，关联restriction_rules表
                .eq(Objects.nonNull(ruleId), GlobalLoginRestriction::getRuleId, ruleId)
                // 限制开始时间
                .eq(Objects.nonNull(restrictionStart), GlobalLoginRestriction::getRestrictionStart, restrictionStart)
                // 限制结束时间
                .eq(Objects.nonNull(restrictionEnd), GlobalLoginRestriction::getRestrictionEnd, restrictionEnd)
                // 限制原因
                .likeRight(Strings.hasLength(reason), GlobalLoginRestriction::getReason, reason)
                // 限制状态（例如：ACTIVE, INACTIVE）
                .likeRight(Strings.hasLength(status), GlobalLoginRestriction::getStatus, status)
                // 记录创建时间
                .eq(Objects.nonNull(createdAt), GlobalLoginRestriction::getCreatedAt, createdAt)
                // 记录更新时间
                .eq(Objects.nonNull(updatedAt), GlobalLoginRestriction::getUpdatedAt, updatedAt)
                ;
    }

    public GlobalLoginRestriction of() {
        return GlobalLoginRestrictionMapstruct.INSTANCE.of(this);
    }
}
