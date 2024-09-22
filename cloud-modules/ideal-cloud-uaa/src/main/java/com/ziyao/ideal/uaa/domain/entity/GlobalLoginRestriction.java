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
 * 全局登录限制表
 * </p>
 *
 * @author ziyao
 */
@Getter
@Setter
@Builder
@Entity(name = "global_login_restriction")
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "全局登录限制表")
public class GlobalLoginRestriction implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id:记录ID
     */
    @Id
    @Schema(description = "记录ID")
    private Integer id;

    /**
     * ruleId:限制规则ID，关联restriction_rules表
     */
    @Schema(description = "限制规则ID，关联restriction_rules表")
    private Integer ruleId;

    /**
     * restrictionStart:限制开始时间
     */
    @Schema(description = "限制开始时间")
    private LocalDateTime restrictionStart;

    /**
     * restrictionEnd:限制结束时间
     */
    @Schema(description = "限制结束时间")
    private LocalDateTime restrictionEnd;

    /**
     * reason:限制原因
     */
    @Schema(description = "限制原因")
    private String reason;

    /**
     * status:限制状态（例如：ACTIVE, INACTIVE）
     */
    @Schema(description = "限制状态（例如：ACTIVE, INACTIVE）")
    private String status;

    /**
     * createdAt:记录创建时间
     */
    @Schema(description = "记录创建时间")
    private LocalDateTime createdAt;

    /**
     * updatedAt:记录更新时间
     */
    @Schema(description = "记录更新时间")
    private LocalDateTime updatedAt;


    public static class Builder {

        private final GlobalLoginRestriction globalLoginRestriction = new GlobalLoginRestriction();

        public Builder id(Integer id) {
            this.globalLoginRestriction.id = id;
            return this;
        }

        public Builder ruleId(Integer ruleId) {
            this.globalLoginRestriction.ruleId = ruleId;
            return this;
        }

        public Builder restrictionStart(LocalDateTime restrictionStart) {
            this.globalLoginRestriction.restrictionStart = restrictionStart;
            return this;
        }

        public Builder restrictionEnd(LocalDateTime restrictionEnd) {
            this.globalLoginRestriction.restrictionEnd = restrictionEnd;
            return this;
        }

        public Builder reason(String reason) {
            this.globalLoginRestriction.reason = reason;
            return this;
        }

        public Builder status(String status) {
            this.globalLoginRestriction.status = status;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.globalLoginRestriction.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.globalLoginRestriction.updatedAt = updatedAt;
            return this;
        }


        public GlobalLoginRestriction build() {
            return this.globalLoginRestriction;
        }
    }
}
