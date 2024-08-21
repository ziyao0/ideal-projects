package com.ziyao.ideal.uua.domain.dto;

import com.ziyao.ideal.uua.domain.convertor.LoginConfigConvertor;
import com.ziyao.ideal.uua.domain.entity.LoginConfig;
import lombok.Data;

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
public class LoginConfigDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 配置ID
     */
    private Long id;
    /**
     * 登录方法（如：用户名密码，OTP，社交登录等）
     */
    private String loginMethod;
    /**
     * 最大尝试次数
     */
    private Integer maxAttempts;
    /**
     * 锁定时长（分钟）
     */
    private Integer lockoutDuration;
    /**
     * 是否需要验证码
     */
    private Boolean requireCaptcha;
    /**
     * 验证码类型（如：图片验证码，短信验证码等）
     */
    private String captchaType;
    /**
     * 是否启用双因素认证
     */
    private Boolean twoFactorEnabled;
    /**
     * 双因素认证方式（如：短信，邮件等）
     */
    private String twoFactorMethod;
    /**
     * 会话超时时间（分钟）
     */
    private Integer sessionTimeout;
    /**
     * 密码有效期（天）
     */
    private Integer passwordExpiryDays;
    /**
     * 是否允许“记住我”功能
     */
    private Boolean allowRememberMe;
    /**
     * “记住我”功能的时长（天）
     */
    private Integer rememberMeDuration;
    /**
     * 密码复杂度要求（正则表达式）
     */
    private String passwordComplexity;
    /**
     * 记录的密码历史次数，用于避免重复使用旧密码
     */
    private Integer passwordHistoryCount;
    /**
     * 是否要求用户在首次登录时重置密码
     */
    private Boolean passwordResetRequired;
    /**
     * 允许访问的IP白名单（逗号分隔）
     */
    private String ipWhitelist;
    /**
     * 禁止访问的IP黑名单（逗号分隔）
     */
    private String ipBlacklist;
    /**
     * 允许访问的国家白名单（逗号分隔）
     */
    private String countryWhitelist;
    /**
     * 禁止访问的国家黑名单（逗号分隔）
     */
    private String countryBlacklist;
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
    /**
     * 配置描述
     */
    private String description;

    public LoginConfig convert() {
        return LoginConfigConvertor.INSTANCE.convert(this);
    }
}
