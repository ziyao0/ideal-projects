package com.ziyao.ideal.uua.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * 应用系统
 * </p>
 *
 * @author zhangziyao
 */
@Getter
@Setter
@TableName("application")
@Entity(name = "application")
@KeySpace("oauth2:application")
public class Application implements Serializable {

    
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    @org.springframework.data.annotation.Id
    @TableId("app_id")
    private Integer appId;

    /**
     * 应用类型 0内部系统应用 1三方平台应用
     */
    @TableField("app_type")
    private Integer appType;

    /**
     * 客户端允许的授权类型
     */
    @TableField("authorization_grant_types")
    private String authorizationGrantTypes;

    /**
     * 授权范围
     */
    @TableField("scopes")
    private String scopes;

    /**
     * 删除状态 0正常 1失效
     */
    @TableField("state")
    private Integer state;

    /**
     * 颁发时间
     */
    @TableField("issued_at")
    private LocalDateTime issuedAt;

    /**
     * 应用秘钥
     */
    @TableField("app_secret")
    private String appSecret;

    /**
     * 应用秘钥过期时间
     */
    @TableField("app_secret_expires_at")
    private LocalDateTime appSecretExpiresAt;

    /**
     * 应用名称
     */
    @TableField("app_name")
    private String appName;

    /**
     * 系统重定向路径
     */
    @TableField("redirect_uri")
    private String redirectUri;

    @TableField("post_logout_redirect_uri")
    private String postLogoutRedirectUri;

    @TableField("token_settings")
    private String tokenSettings;

    /**
     * 系统简介
     */
    @TableField("remark")
    private String remark;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Application that = (Application) o;
        return getAppId() != null && Objects.equals(getAppId(), that.getAppId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
