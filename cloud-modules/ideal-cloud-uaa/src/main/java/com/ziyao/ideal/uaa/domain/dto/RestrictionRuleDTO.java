package com.ziyao.ideal.uaa.domain.dto;

import com.ziyao.ideal.uaa.domain.entity.RestrictionRule;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
@Schema(description = "限制规则表")
public class RestrictionRuleDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 规则ID
     */
    @Schema(description = "规则ID")
    private Integer id;

    /**
     * 规则名称
     */
    @Schema(description = "规则名称")
    private String ruleName;

    /**
     * 规则类型（例如：IP_BLOCK, LOGIN_ATTEMPT_LIMIT等）
     */
    @Schema(description = "规则类型（例如：IP_BLOCK, LOGIN_ATTEMPT_LIMIT等）")
    private String ruleType;

    /**
     * 规则参数，存储具体的规则配置，以JSON格式记录
     */
    @Schema(description = "规则参数，存储具体的规则配置，以JSON格式记录")
    private String parameters;

    /**
     * 规则描述
     */
    @Schema(description = "规则描述")
    private String description;

    /**
     * 规则创建时间
     */
    @Schema(description = "规则创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    /**
     * start time for 规则创建时间
     */
    @Schema(description = "规则创建时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startCreatedAt;

    /**
     * end time for 规则创建时间
     */
    @Schema(description = "规则创建时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endCreatedAt;

    /**
     * 规则更新时间
     */
    @Schema(description = "规则更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    /**
     * start time for 规则更新时间
     */
    @Schema(description = "规则更新时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startUpdatedAt;

    /**
     * end time for 规则更新时间
     */
    @Schema(description = "规则更新时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endUpdatedAt;

    public RestrictionRule toEntity() {
        RestrictionRule restrictionRule = new RestrictionRule();
        restrictionRule.setId(this.id);
        restrictionRule.setRuleName(this.ruleName);
        restrictionRule.setRuleType(this.ruleType);
        restrictionRule.setParameters(this.parameters);
        restrictionRule.setDescription(this.description);
        restrictionRule.setCreatedAt(this.createdAt);
        restrictionRule.setUpdatedAt(this.updatedAt);
        return restrictionRule;
    }
}
