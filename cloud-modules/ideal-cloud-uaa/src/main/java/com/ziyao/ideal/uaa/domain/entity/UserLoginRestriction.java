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
 * 用户登录限制表
 * </p>
 *
 * @author ziyao
 */
@Getter
@Setter
@Builder
@Entity(name = "user_login_restriction")
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "用户登录限制表")
public class UserLoginRestriction implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id:记录ID
     */
    @Id
    @Schema(description = "记录ID")
    private Integer id;

    /**
     * userId:用户ID
     */
    @Schema(description = "用户ID")
    private Integer userId;

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
     * restrictionEnd:限制结束时间（对于临时限制）
     */
    @Schema(description = "限制结束时间（对于临时限制）")
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

        private final UserLoginRestriction userLoginRestriction = new UserLoginRestriction();

        public Builder id(Integer id) {
            this.userLoginRestriction.id = id;
            return this;
        }

        public Builder userId(Integer userId) {
            this.userLoginRestriction.userId = userId;
            return this;
        }

        public Builder ruleId(Integer ruleId) {
            this.userLoginRestriction.ruleId = ruleId;
            return this;
        }

        public Builder restrictionStart(LocalDateTime restrictionStart) {
            this.userLoginRestriction.restrictionStart = restrictionStart;
            return this;
        }

        public Builder restrictionEnd(LocalDateTime restrictionEnd) {
            this.userLoginRestriction.restrictionEnd = restrictionEnd;
            return this;
        }

        public Builder reason(String reason) {
            this.userLoginRestriction.reason = reason;
            return this;
        }

        public Builder status(String status) {
            this.userLoginRestriction.status = status;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.userLoginRestriction.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.userLoginRestriction.updatedAt = updatedAt;
            return this;
        }


        public UserLoginRestriction build() {
            return this.userLoginRestriction;
        }
    }
}
