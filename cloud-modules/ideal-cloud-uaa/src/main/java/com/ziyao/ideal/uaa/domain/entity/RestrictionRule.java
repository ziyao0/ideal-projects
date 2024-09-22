package com.ziyao.ideal.uaa.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

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
@Getter
@Setter
@Builder
@Entity(name = "restriction_rule")
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "限制规则表")
public class RestrictionRule implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id:规则ID
     */
    @Id
    @Schema(description = "规则ID")
    private Integer id;

    /**
     * ruleName:规则名称
     */
    @Schema(description = "规则名称")
    private String ruleName;

    /**
     * ruleType:规则类型（例如：IP_BLOCK, LOGIN_ATTEMPT_LIMIT等）
     */
    @Schema(description = "规则类型（例如：IP_BLOCK, LOGIN_ATTEMPT_LIMIT等）")
    private String ruleType;

    /**
     * parameters:规则参数，存储具体的规则配置，以JSON格式记录
     */
    @Schema(description = "规则参数，存储具体的规则配置，以JSON格式记录")
    private String parameters;

    /**
     * description:规则描述
     */
    @Schema(description = "规则描述")
    private String description;

    /**
     * createdAt:规则创建时间
     */
    @Schema(description = "规则创建时间")
    private LocalDateTime createdAt;

    /**
     * updatedAt:规则更新时间
     */
    @Schema(description = "规则更新时间")
    private LocalDateTime updatedAt;


    public static class Builder {

        private final RestrictionRule restrictionRule = new RestrictionRule();

        public Builder id(Integer id) {
            this.restrictionRule.id = id;
            return this;
        }

        public Builder ruleName(String ruleName) {
            this.restrictionRule.ruleName = ruleName;
            return this;
        }

        public Builder ruleType(String ruleType) {
            this.restrictionRule.ruleType = ruleType;
            return this;
        }

        public Builder parameters(String parameters) {
            this.restrictionRule.parameters = parameters;
            return this;
        }

        public Builder description(String description) {
            this.restrictionRule.description = description;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.restrictionRule.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.restrictionRule.updatedAt = updatedAt;
            return this;
        }


        public RestrictionRule build() {
            return this.restrictionRule;
        }
    }
}
