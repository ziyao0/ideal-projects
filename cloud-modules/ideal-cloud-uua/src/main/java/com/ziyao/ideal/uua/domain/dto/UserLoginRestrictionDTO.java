package com.ziyao.ideal.uua.domain.dto;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ziyao.ideal.web.orm.EntityDTO;
import com.ziyao.ideal.uua.domain.entity.UserLoginRestriction;
import com.ziyao.ideal.uua.domain.mapstruct.UserLoginRestrictionMapstruct;
import lombok.Data;
import java.util.Objects;
import com.ziyao.ideal.core.Strings;


import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户登录限制表
 * </p>
 *
 * @author ziyao
 */
@Data
public class UserLoginRestrictionDTO implements EntityDTO<UserLoginRestriction>, Serializable {

    
    private static final long serialVersionUID = 1L;

    /**
     * 记录ID
     */
    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 限制规则ID，关联restriction_rules表
     */
    private Integer ruleId;
    /**
     * 限制开始时间
     */
    private LocalDateTime restrictionStart;
    /**
     * 限制结束时间（对于临时限制）
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
    public LambdaQueryWrapper<UserLoginRestriction> initWrapper() {

        return Wrappers.lambdaQuery(UserLoginRestriction.class)
                // 用户ID
                .eq(Objects.nonNull(userId), UserLoginRestriction::getUserId, userId)
                // 限制规则ID，关联restriction_rules表
                .eq(Objects.nonNull(ruleId), UserLoginRestriction::getRuleId, ruleId)
                // 限制开始时间
                .eq(Objects.nonNull(restrictionStart), UserLoginRestriction::getRestrictionStart, restrictionStart)
                // 限制结束时间（对于临时限制）
                .eq(Objects.nonNull(restrictionEnd), UserLoginRestriction::getRestrictionEnd, restrictionEnd)
                // 限制原因
                .likeRight(Strings.hasLength(reason), UserLoginRestriction::getReason, reason)
                // 限制状态（例如：ACTIVE, INACTIVE）
                .likeRight(Strings.hasLength(status), UserLoginRestriction::getStatus, status)
                // 记录创建时间
                .eq(Objects.nonNull(createdAt), UserLoginRestriction::getCreatedAt, createdAt)
                // 记录更新时间
                .eq(Objects.nonNull(updatedAt), UserLoginRestriction::getUpdatedAt, updatedAt)
                ;
    }

    public UserLoginRestriction of() {
        return UserLoginRestrictionMapstruct.INSTANCE.of(this);
    }
}
