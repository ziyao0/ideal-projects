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
 * 登录配置表
 * </p>
 *
 * @author ziyao
 */
@Getter
@Setter
@Builder
@Entity(name = "login_config")
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "登录配置表")
public class LoginConfig implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id:配置ID
     */
    @Id
    @Schema(description = "配置ID")
    private Long id;

    /**
     * loginMethod:登录方法（如：用户名密码，OTP，社交登录等）
     */
    @Schema(description = "登录方法（如：用户名密码，OTP，社交登录等）")
    private String loginMethod;

    /**
     * maxAttempts:最大尝试次数
     */
    @Schema(description = "最大尝试次数")
    private Integer maxAttempts;

    /**
     * lockoutDuration:锁定时长（分钟）
     */
    @Schema(description = "锁定时长（分钟）")
    private Integer lockoutDuration;

    /**
     * requireCaptcha:是否需要验证码
     */
    @Schema(description = "是否需要验证码")
    private Boolean requireCaptcha;

    /**
     * captchaType:验证码类型（如：图片验证码，短信验证码等）
     */
    @Schema(description = "验证码类型（如：图片验证码，短信验证码等）")
    private String captchaType;

    /**
     * twoFactorEnabled:是否启用双因素认证
     */
    @Schema(description = "是否启用双因素认证")
    private Boolean twoFactorEnabled;

    /**
     * twoFactorMethod:双因素认证方式（如：短信，邮件等）
     */
    @Schema(description = "双因素认证方式（如：短信，邮件等）")
    private String twoFactorMethod;

    /**
     * sessionTimeout:会话超时时间（分钟）
     */
    @Schema(description = "会话超时时间（分钟）")
    private Integer sessionTimeout;

    /**
     * passwordExpiryDays:密码有效期（天）
     */
    @Schema(description = "密码有效期（天）")
    private Integer passwordExpiryDays;

    /**
     * allowRememberMe:是否允许“记住我”功能
     */
    @Schema(description = "是否允许“记住我”功能")
    private Boolean allowRememberMe;

    /**
     * rememberMeDuration:“记住我”功能的时长（天）
     */
    @Schema(description = "“记住我”功能的时长（天）")
    private Integer rememberMeDuration;

    /**
     * passwordComplexity:密码复杂度要求（正则表达式）
     */
    @Schema(description = "密码复杂度要求（正则表达式）")
    private String passwordComplexity;

    /**
     * passwordHistoryCount:记录的密码历史次数，用于避免重复使用旧密码
     */
    @Schema(description = "记录的密码历史次数，用于避免重复使用旧密码")
    private Integer passwordHistoryCount;

    /**
     * passwordResetRequired:是否要求用户在首次登录时重置密码
     */
    @Schema(description = "是否要求用户在首次登录时重置密码")
    private Boolean passwordResetRequired;

    /**
     * ipWhitelist:允许访问的IP白名单（逗号分隔）
     */
    @Schema(description = "允许访问的IP白名单（逗号分隔）")
    private String ipWhitelist;

    /**
     * ipBlacklist:禁止访问的IP黑名单（逗号分隔）
     */
    @Schema(description = "禁止访问的IP黑名单（逗号分隔）")
    private String ipBlacklist;

    /**
     * countryWhitelist:允许访问的国家白名单（逗号分隔）
     */
    @Schema(description = "允许访问的国家白名单（逗号分隔）")
    private String countryWhitelist;

    /**
     * countryBlacklist:禁止访问的国家黑名单（逗号分隔）
     */
    @Schema(description = "禁止访问的国家黑名单（逗号分隔）")
    private String countryBlacklist;

    /**
     * createdAt:创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createdAt;

    /**
     * updatedAt:更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updatedAt;

    /**
     * description:配置描述
     */
    @Schema(description = "配置描述")
    private String description;


    public static class Builder {

        private final LoginConfig loginConfig = new LoginConfig();

        public Builder id(Long id) {
            this.loginConfig.id = id;
            return this;
        }

        public Builder loginMethod(String loginMethod) {
            this.loginConfig.loginMethod = loginMethod;
            return this;
        }

        public Builder maxAttempts(Integer maxAttempts) {
            this.loginConfig.maxAttempts = maxAttempts;
            return this;
        }

        public Builder lockoutDuration(Integer lockoutDuration) {
            this.loginConfig.lockoutDuration = lockoutDuration;
            return this;
        }

        public Builder requireCaptcha(Boolean requireCaptcha) {
            this.loginConfig.requireCaptcha = requireCaptcha;
            return this;
        }

        public Builder captchaType(String captchaType) {
            this.loginConfig.captchaType = captchaType;
            return this;
        }

        public Builder twoFactorEnabled(Boolean twoFactorEnabled) {
            this.loginConfig.twoFactorEnabled = twoFactorEnabled;
            return this;
        }

        public Builder twoFactorMethod(String twoFactorMethod) {
            this.loginConfig.twoFactorMethod = twoFactorMethod;
            return this;
        }

        public Builder sessionTimeout(Integer sessionTimeout) {
            this.loginConfig.sessionTimeout = sessionTimeout;
            return this;
        }

        public Builder passwordExpiryDays(Integer passwordExpiryDays) {
            this.loginConfig.passwordExpiryDays = passwordExpiryDays;
            return this;
        }

        public Builder allowRememberMe(Boolean allowRememberMe) {
            this.loginConfig.allowRememberMe = allowRememberMe;
            return this;
        }

        public Builder rememberMeDuration(Integer rememberMeDuration) {
            this.loginConfig.rememberMeDuration = rememberMeDuration;
            return this;
        }

        public Builder passwordComplexity(String passwordComplexity) {
            this.loginConfig.passwordComplexity = passwordComplexity;
            return this;
        }

        public Builder passwordHistoryCount(Integer passwordHistoryCount) {
            this.loginConfig.passwordHistoryCount = passwordHistoryCount;
            return this;
        }

        public Builder passwordResetRequired(Boolean passwordResetRequired) {
            this.loginConfig.passwordResetRequired = passwordResetRequired;
            return this;
        }

        public Builder ipWhitelist(String ipWhitelist) {
            this.loginConfig.ipWhitelist = ipWhitelist;
            return this;
        }

        public Builder ipBlacklist(String ipBlacklist) {
            this.loginConfig.ipBlacklist = ipBlacklist;
            return this;
        }

        public Builder countryWhitelist(String countryWhitelist) {
            this.loginConfig.countryWhitelist = countryWhitelist;
            return this;
        }

        public Builder countryBlacklist(String countryBlacklist) {
            this.loginConfig.countryBlacklist = countryBlacklist;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.loginConfig.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.loginConfig.updatedAt = updatedAt;
            return this;
        }

        public Builder description(String description) {
            this.loginConfig.description = description;
            return this;
        }


        public LoginConfig build() {
            return this.loginConfig;
        }
    }
}
