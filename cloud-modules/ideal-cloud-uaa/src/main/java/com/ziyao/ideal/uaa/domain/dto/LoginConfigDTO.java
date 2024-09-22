package com.ziyao.ideal.uaa.domain.dto;

import com.ziyao.ideal.uaa.domain.entity.LoginConfig;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
@Data
@Schema(description = "登录配置表")
public class LoginConfigDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 配置ID
     */
    @Schema(description = "配置ID")
    private Long id;

    /**
     * 登录方法（如：用户名密码，OTP，社交登录等）
     */
    @Schema(description = "登录方法（如：用户名密码，OTP，社交登录等）")
    private String loginMethod;

    /**
     * 最大尝试次数
     */
    @Schema(description = "最大尝试次数")
    private Integer maxAttempts;

    /**
     * 锁定时长（分钟）
     */
    @Schema(description = "锁定时长（分钟）")
    private Integer lockoutDuration;

    /**
     * 是否需要验证码
     */
    @Schema(description = "是否需要验证码")
    private Boolean requireCaptcha;

    /**
     * 验证码类型（如：图片验证码，短信验证码等）
     */
    @Schema(description = "验证码类型（如：图片验证码，短信验证码等）")
    private String captchaType;

    /**
     * 是否启用双因素认证
     */
    @Schema(description = "是否启用双因素认证")
    private Boolean twoFactorEnabled;

    /**
     * 双因素认证方式（如：短信，邮件等）
     */
    @Schema(description = "双因素认证方式（如：短信，邮件等）")
    private String twoFactorMethod;

    /**
     * 会话超时时间（分钟）
     */
    @Schema(description = "会话超时时间（分钟）")
    private Integer sessionTimeout;

    /**
     * 密码有效期（天）
     */
    @Schema(description = "密码有效期（天）")
    private Integer passwordExpiryDays;

    /**
     * 是否允许“记住我”功能
     */
    @Schema(description = "是否允许“记住我”功能")
    private Boolean allowRememberMe;

    /**
     * “记住我”功能的时长（天）
     */
    @Schema(description = "“记住我”功能的时长（天）")
    private Integer rememberMeDuration;

    /**
     * 密码复杂度要求（正则表达式）
     */
    @Schema(description = "密码复杂度要求（正则表达式）")
    private String passwordComplexity;

    /**
     * 记录的密码历史次数，用于避免重复使用旧密码
     */
    @Schema(description = "记录的密码历史次数，用于避免重复使用旧密码")
    private Integer passwordHistoryCount;

    /**
     * 是否要求用户在首次登录时重置密码
     */
    @Schema(description = "是否要求用户在首次登录时重置密码")
    private Boolean passwordResetRequired;

    /**
     * 允许访问的IP白名单（逗号分隔）
     */
    @Schema(description = "允许访问的IP白名单（逗号分隔）")
    private String ipWhitelist;

    /**
     * 禁止访问的IP黑名单（逗号分隔）
     */
    @Schema(description = "禁止访问的IP黑名单（逗号分隔）")
    private String ipBlacklist;

    /**
     * 允许访问的国家白名单（逗号分隔）
     */
    @Schema(description = "允许访问的国家白名单（逗号分隔）")
    private String countryWhitelist;

    /**
     * 禁止访问的国家黑名单（逗号分隔）
     */
    @Schema(description = "禁止访问的国家黑名单（逗号分隔）")
    private String countryBlacklist;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    /**
     * start time for 创建时间
     */
    @Schema(description = "创建时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startCreatedAt;

    /**
     * end time for 创建时间
     */
    @Schema(description = "创建时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endCreatedAt;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    /**
     * start time for 更新时间
     */
    @Schema(description = "更新时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startUpdatedAt;

    /**
     * end time for 更新时间
     */
    @Schema(description = "更新时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endUpdatedAt;

    /**
     * 配置描述
     */
    @Schema(description = "配置描述")
    private String description;

    public LoginConfig toEntity() {
        LoginConfig loginConfig = new LoginConfig();
        loginConfig.setId(this.id);
        loginConfig.setLoginMethod(this.loginMethod);
        loginConfig.setMaxAttempts(this.maxAttempts);
        loginConfig.setLockoutDuration(this.lockoutDuration);
        loginConfig.setRequireCaptcha(this.requireCaptcha);
        loginConfig.setCaptchaType(this.captchaType);
        loginConfig.setTwoFactorEnabled(this.twoFactorEnabled);
        loginConfig.setTwoFactorMethod(this.twoFactorMethod);
        loginConfig.setSessionTimeout(this.sessionTimeout);
        loginConfig.setPasswordExpiryDays(this.passwordExpiryDays);
        loginConfig.setAllowRememberMe(this.allowRememberMe);
        loginConfig.setRememberMeDuration(this.rememberMeDuration);
        loginConfig.setPasswordComplexity(this.passwordComplexity);
        loginConfig.setPasswordHistoryCount(this.passwordHistoryCount);
        loginConfig.setPasswordResetRequired(this.passwordResetRequired);
        loginConfig.setIpWhitelist(this.ipWhitelist);
        loginConfig.setIpBlacklist(this.ipBlacklist);
        loginConfig.setCountryWhitelist(this.countryWhitelist);
        loginConfig.setCountryBlacklist(this.countryBlacklist);
        loginConfig.setCreatedAt(this.createdAt);
        loginConfig.setUpdatedAt(this.updatedAt);
        loginConfig.setDescription(this.description);
        return loginConfig;
    }
}
