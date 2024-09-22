package com.ziyao.ideal.uaa.domain.dto;

import com.ziyao.ideal.uaa.domain.entity.GlobalLoginRestriction;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
@Schema(description = "全局登录限制表")
public class GlobalLoginRestrictionDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    @Schema(description = "记录ID")
    private Integer id;

    /**
     * 限制规则ID，关联restriction_rules表
     */
    @Schema(description = "限制规则ID，关联restriction_rules表")
    private Integer ruleId;

    /**
     * 限制开始时间
     */
    @Schema(description = "限制开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime restrictionStart;
    /**
     * start time for 限制开始时间
     */
    @Schema(description = "限制开始时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startRestrictionStart;

    /**
     * end time for 限制开始时间
     */
    @Schema(description = "限制开始时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endRestrictionStart;

    /**
     * 限制结束时间
     */
    @Schema(description = "限制结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime restrictionEnd;
    /**
     * start time for 限制结束时间
     */
    @Schema(description = "限制结束时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startRestrictionEnd;

    /**
     * end time for 限制结束时间
     */
    @Schema(description = "限制结束时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endRestrictionEnd;

    /**
     * 限制原因
     */
    @Schema(description = "限制原因")
    private String reason;

    /**
     * 限制状态（例如：ACTIVE, INACTIVE）
     */
    @Schema(description = "限制状态（例如：ACTIVE, INACTIVE）")
    private String status;

    /**
     * 记录创建时间
     */
    @Schema(description = "记录创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    /**
     * start time for 记录创建时间
     */
    @Schema(description = "记录创建时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startCreatedAt;

    /**
     * end time for 记录创建时间
     */
    @Schema(description = "记录创建时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endCreatedAt;

    /**
     * 记录更新时间
     */
    @Schema(description = "记录更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    /**
     * start time for 记录更新时间
     */
    @Schema(description = "记录更新时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startUpdatedAt;

    /**
     * end time for 记录更新时间
     */
    @Schema(description = "记录更新时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endUpdatedAt;

    public GlobalLoginRestriction toEntity() {
        GlobalLoginRestriction globalLoginRestriction = new GlobalLoginRestriction();
        globalLoginRestriction.setId(this.id);
        globalLoginRestriction.setRuleId(this.ruleId);
        globalLoginRestriction.setRestrictionStart(this.restrictionStart);
        globalLoginRestriction.setRestrictionEnd(this.restrictionEnd);
        globalLoginRestriction.setReason(this.reason);
        globalLoginRestriction.setStatus(this.status);
        globalLoginRestriction.setCreatedAt(this.createdAt);
        globalLoginRestriction.setUpdatedAt(this.updatedAt);
        return globalLoginRestriction;
    }
}
