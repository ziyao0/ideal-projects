package com.ziyao.ideal.uua.domain.dto;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ziyao.ideal.web.orm.EntityDTO;
import com.ziyao.ideal.uua.domain.entity.LoginConfig;
import com.ziyao.ideal.uua.domain.mapstruct.LoginConfigMapstruct;
import lombok.Data;
import java.util.Objects;
import com.ziyao.ideal.core.Strings;

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
public class LoginConfigDTO implements EntityDTO<LoginConfig>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 配置ID
     */
    private Integer id;
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

    /**
     * 组装查询条件，可根据具体情况做出修改
     *
     * @see LambdaQueryWrapper
     */
    public LambdaQueryWrapper<LoginConfig> initWrapper() {

        return Wrappers.lambdaQuery(LoginConfig.class)
                // 登录方法（如：用户名密码，OTP，社交登录等）
                .likeRight(Strings.hasLength(loginMethod), LoginConfig::getLoginMethod, loginMethod)
                // 最大尝试次数
                .eq(Objects.nonNull(maxAttempts), LoginConfig::getMaxAttempts, maxAttempts)
                // 锁定时长（分钟）
                .eq(Objects.nonNull(lockoutDuration), LoginConfig::getLockoutDuration, lockoutDuration)
                // 是否需要验证码
                .eq(Objects.nonNull(requireCaptcha), LoginConfig::getRequireCaptcha, requireCaptcha)
                // 验证码类型（如：图片验证码，短信验证码等）
                .likeRight(Strings.hasLength(captchaType), LoginConfig::getCaptchaType, captchaType)
                // 是否启用双因素认证
                .eq(Objects.nonNull(twoFactorEnabled), LoginConfig::getTwoFactorEnabled, twoFactorEnabled)
                // 双因素认证方式（如：短信，邮件等）
                .likeRight(Strings.hasLength(twoFactorMethod), LoginConfig::getTwoFactorMethod, twoFactorMethod)
                // 会话超时时间（分钟）
                .eq(Objects.nonNull(sessionTimeout), LoginConfig::getSessionTimeout, sessionTimeout)
                // 密码有效期（天）
                .eq(Objects.nonNull(passwordExpiryDays), LoginConfig::getPasswordExpiryDays, passwordExpiryDays)
                // 是否允许“记住我”功能
                .eq(Objects.nonNull(allowRememberMe), LoginConfig::getAllowRememberMe, allowRememberMe)
                // “记住我”功能的时长（天）
                .eq(Objects.nonNull(rememberMeDuration), LoginConfig::getRememberMeDuration, rememberMeDuration)
                // 密码复杂度要求（正则表达式）
                .likeRight(Strings.hasLength(passwordComplexity), LoginConfig::getPasswordComplexity, passwordComplexity)
                // 记录的密码历史次数，用于避免重复使用旧密码
                .eq(Objects.nonNull(passwordHistoryCount), LoginConfig::getPasswordHistoryCount, passwordHistoryCount)
                // 是否要求用户在首次登录时重置密码
                .eq(Objects.nonNull(passwordResetRequired), LoginConfig::getPasswordResetRequired, passwordResetRequired)
                // 允许访问的IP白名单（逗号分隔）
                .likeRight(Strings.hasLength(ipWhitelist), LoginConfig::getIpWhitelist, ipWhitelist)
                // 禁止访问的IP黑名单（逗号分隔）
                .likeRight(Strings.hasLength(ipBlacklist), LoginConfig::getIpBlacklist, ipBlacklist)
                // 允许访问的国家白名单（逗号分隔）
                .likeRight(Strings.hasLength(countryWhitelist), LoginConfig::getCountryWhitelist, countryWhitelist)
                // 禁止访问的国家黑名单（逗号分隔）
                .likeRight(Strings.hasLength(countryBlacklist), LoginConfig::getCountryBlacklist, countryBlacklist)
                // 创建时间
                .eq(Objects.nonNull(createdAt), LoginConfig::getCreatedAt, createdAt)
                // 更新时间
                .eq(Objects.nonNull(updatedAt), LoginConfig::getUpdatedAt, updatedAt)
                // 配置描述
                .likeRight(Strings.hasLength(description), LoginConfig::getDescription, description)
                ;
    }

    public LoginConfig of() {
        return LoginConfigMapstruct.INSTANCE.of(this);
    }
}
