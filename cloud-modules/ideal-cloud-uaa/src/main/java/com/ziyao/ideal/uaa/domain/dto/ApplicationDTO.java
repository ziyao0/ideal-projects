package com.ziyao.ideal.uaa.domain.dto;

import com.ziyao.ideal.uaa.domain.entity.Application;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 应用系统
 * </p>
 *
 * @author ziyao
 */
@Data
@Schema(description = "应用系统")
public class ApplicationDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    @Schema(description = "主键id")
    private Integer appId;

    /**
     * 应用类型 0内部系统应用 1三方平台应用
     */
    @Schema(description = "应用类型 0内部系统应用 1三方平台应用 ", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer appType;

    /**
     * 客户端允许的授权类型
     */
    @Schema(description = "客户端允许的授权类型")
    private String authorizationGrantTypes;

    /**
     * 授权范围
     */
    @Schema(description = "授权范围", requiredMode = Schema.RequiredMode.REQUIRED)
    private String scopes;

    /**
     * 删除状态 0正常 1失效
     */
    @Schema(description = "删除状态 0正常 1失效")
    private Integer state;

    /**
     * 颁发时间
     */
    @Schema(description = "颁发时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime issuedAt;
    /**
     * start time for 颁发时间
     */
    @Schema(description = "颁发时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startIssuedAt;

    /**
     * end time for 颁发时间
     */
    @Schema(description = "颁发时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endIssuedAt;

    /**
     * 应用秘钥
     */
    @Schema(description = "应用秘钥")
    private String appSecret;

    /**
     * 应用秘钥过期时间
     */
    @Schema(description = "应用秘钥过期时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime appSecretExpiresAt;
    /**
     * start time for 应用秘钥过期时间
     */
    @Schema(description = "应用秘钥过期时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startAppSecretExpiresAt;

    /**
     * end time for 应用秘钥过期时间
     */
    @Schema(description = "应用秘钥过期时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endAppSecretExpiresAt;

    /**
     * 应用名称
     */
    @Schema(description = "应用名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String appName;

    /**
     * 系统重定向路径
     */
    @Schema(description = "系统重定向路径", requiredMode = Schema.RequiredMode.REQUIRED)
    private String redirectUri;

    /**
     *
     */
    @Schema(description = "退出后重定向uri")
    private String postLogoutRedirectUri;

    /**
     *
     */
    @Schema(description = "令牌配置")
    private String tokenSettings;

    /**
     * 系统简介
     */
    @Schema(description = "系统简介")
    private String remark;

    public Application toEntity() {
        Application application = new Application();
        application.setAppId(this.appId);
        application.setAppType(this.appType);
        application.setAuthorizationGrantTypes(this.authorizationGrantTypes);
        application.setScopes(this.scopes);
        application.setState(this.state);
        application.setIssuedAt(this.issuedAt);
        application.setAppSecret(this.appSecret);
        application.setAppSecretExpiresAt(this.appSecretExpiresAt);
        application.setAppName(this.appName);
        application.setRedirectUri(this.redirectUri);
        application.setPostLogoutRedirectUri(this.postLogoutRedirectUri);
        application.setTokenSettings(this.tokenSettings);
        application.setRemark(this.remark);
        return application;
    }
}
