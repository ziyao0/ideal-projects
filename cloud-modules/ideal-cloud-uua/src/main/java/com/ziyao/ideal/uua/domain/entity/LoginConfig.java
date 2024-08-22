package com.ziyao.ideal.uua.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
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
public class LoginConfig implements Serializable {


    private static final long serialVersionUID = 1L;


    /**
     * id:配置ID
     */
    @Id
    private Long id;

    /**
     * loginMethod:登录方法（如：用户名密码，OTP，社交登录等）
     */
    private String loginMethod;

    /**
     * maxAttempts:最大尝试次数
     */
    private Integer maxAttempts;

    /**
     * lockoutDuration:锁定时长（分钟）
     */
    private Integer lockoutDuration;

    /**
     * requireCaptcha:是否需要验证码
     */
    private Boolean requireCaptcha;

    /**
     * captchaType:验证码类型（如：图片验证码，短信验证码等）
     */
    private String captchaType;

    /**
     * twoFactorEnabled:是否启用双因素认证
     */
    private Boolean twoFactorEnabled;

    /**
     * twoFactorMethod:双因素认证方式（如：短信，邮件等）
     */
    private String twoFactorMethod;

    /**
     * sessionTimeout:会话超时时间（分钟）
     */
    private Integer sessionTimeout;

    /**
     * passwordExpiryDays:密码有效期（天）
     */
    private Integer passwordExpiryDays;

    /**
     * allowRememberMe:是否允许“记住我”功能
     */
    private Boolean allowRememberMe;

    /**
     * rememberMeDuration:“记住我”功能的时长（天）
     */
    private Integer rememberMeDuration;

    /**
     * passwordComplexity:密码复杂度要求（正则表达式）
     */
    private String passwordComplexity;

    /**
     * passwordHistoryCount:记录的密码历史次数，用于避免重复使用旧密码
     */
    private Integer passwordHistoryCount;

    /**
     * passwordResetRequired:是否要求用户在首次登录时重置密码
     */
    private Boolean passwordResetRequired;

    /**
     * ipWhitelist:允许访问的IP白名单（逗号分隔）
     */
    private String ipWhitelist;

    /**
     * ipBlacklist:禁止访问的IP黑名单（逗号分隔）
     */
    private String ipBlacklist;

    /**
     * countryWhitelist:允许访问的国家白名单（逗号分隔）
     */
    private String countryWhitelist;

    /**
     * countryBlacklist:禁止访问的国家黑名单（逗号分隔）
     */
    private String countryBlacklist;

    /**
     * createdAt:创建时间
     */
    private LocalDateTime createdAt;

    /**
     * updatedAt:更新时间
     */
    private LocalDateTime updatedAt;

    /**
     * description:配置描述
     */
    private String description;
}
