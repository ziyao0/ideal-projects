package com.ziyao.ideal.uua.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.keyvalue.annotation.KeySpace;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>
 * 登录配置表
 * </p>
 *
 * @author ziyao
 */
@Getter
@Setter
@TableName("login_config")
@Entity(name = "login_config")
@KeySpace("system:login:config")
public class LoginConfig implements Serializable {

    
    private static final long serialVersionUID = 1L;

    /**
     * 配置ID
     */
    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 登录方法（如：用户名密码，OTP，社交登录等）
     */
    @org.springframework.data.annotation.Id
    @TableField("login_method")
    private String loginMethod;

    /**
     * 最大尝试次数
     */
    @TableField("max_attempts")
    private Integer maxAttempts;

    /**
     * 锁定时长（分钟）
     */
    @TableField("lockout_duration")
    private Integer lockoutDuration;

    /**
     * 是否需要验证码
     */
    @TableField("require_captcha")
    private Boolean requireCaptcha;

    /**
     * 验证码类型（如：图片验证码，短信验证码等）
     */
    @TableField("captcha_type")
    private String captchaType;

    /**
     * 是否启用双因素认证
     */
    @TableField("two_factor_enabled")
    private Boolean twoFactorEnabled;

    /**
     * 双因素认证方式（如：短信，邮件等）
     */
    @TableField("two_factor_method")
    private String twoFactorMethod;

    /**
     * 会话超时时间（分钟）
     */
    @TableField("session_timeout")
    private Integer sessionTimeout;

    /**
     * 密码有效期（天）
     */
    @TableField("password_expiry_days")
    private Integer passwordExpiryDays;

    /**
     * 是否允许“记住我”功能
     */
    @TableField("allow_remember_me")
    private Boolean allowRememberMe;

    /**
     * “记住我”功能的时长（天）
     */
    @TableField("remember_me_duration")
    private Integer rememberMeDuration;

    /**
     * 密码复杂度要求（正则表达式）
     */
    @TableField("password_complexity")
    private String passwordComplexity;

    /**
     * 记录的密码历史次数，用于避免重复使用旧密码
     */
    @TableField("password_history_count")
    private Integer passwordHistoryCount;

    /**
     * 是否要求用户在首次登录时重置密码
     */
    @TableField("password_reset_required")
    private Boolean passwordResetRequired;

    /**
     * 允许访问的IP白名单（逗号分隔）
     */
    @TableField("ip_whitelist")
    private String ipWhitelist;

    /**
     * 禁止访问的IP黑名单（逗号分隔）
     */
    @TableField("ip_blacklist")
    private String ipBlacklist;

    /**
     * 允许访问的国家白名单（逗号分隔）
     */
    @TableField("country_whitelist")
    private String countryWhitelist;

    /**
     * 禁止访问的国家黑名单（逗号分隔）
     */
    @TableField("country_blacklist")
    private String countryBlacklist;

    /**
     * 创建时间
     */
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @TableField("updated_at")
    private LocalDateTime updatedAt;

    /**
     * 配置描述
     */
    @TableField("description")
    private String description;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        LoginConfig that = (LoginConfig) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
