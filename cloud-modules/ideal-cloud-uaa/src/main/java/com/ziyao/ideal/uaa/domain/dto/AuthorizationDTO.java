package com.ziyao.ideal.uaa.domain.dto;

import com.ziyao.ideal.uaa.domain.entity.Authorization;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author ziyao
 */
@Data
@Schema(description = "")
public class AuthorizationDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    @Schema(description = "主键id")
    private Integer id;

    /**
     * 应用系统id
     */
    @Schema(description = "应用系统id")
    private Integer appid;

    /**
     *
     */
    @Schema(description = "")
    private Integer userId;

    /**
     * 授权类型
     */
    @Schema(description = "授权类型")
    private String authorizationGrantType;

    /**
     * 授权范围
     */
    @Schema(description = "授权范围")
    private String authorizedScopes;

    /**
     * 授权附加属性
     */
    @Schema(description = "授权附加属性")
    private String attributes;

    /**
     * 状态 1正常 2失效
     */
    @Schema(description = "状态 1正常 2失效")
    private Integer state;

    /**
     * 授权码值
     */
    @Schema(description = "授权码值")
    private String authorizationCodeValue;

    /**
     * 授权码颁发时间
     */
    @Schema(description = "授权码颁发时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime authorizationCodeIssuedAt;
    /**
     * start time for 授权码颁发时间
     */
    @Schema(description = "授权码颁发时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startAuthorizationCodeIssuedAt;

    /**
     * end time for 授权码颁发时间
     */
    @Schema(description = "授权码颁发时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endAuthorizationCodeIssuedAt;

    /**
     * 授权码失效时间
     */
    @Schema(description = "授权码失效时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime authorizationCodeExpiresAt;
    /**
     * start time for 授权码失效时间
     */
    @Schema(description = "授权码失效时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startAuthorizationCodeExpiresAt;

    /**
     * end time for 授权码失效时间
     */
    @Schema(description = "授权码失效时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endAuthorizationCodeExpiresAt;

    /**
     * 授权码元数据信息
     */
    @Schema(description = "授权码元数据信息")
    private String authorizationCodeMetadata;

    /**
     * 认证令牌值
     */
    @Schema(description = "认证令牌值")
    private String accessTokenValue;

    /**
     * 认证令牌颁发时间
     */
    @Schema(description = "认证令牌颁发时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime accessTokenIssuedAt;
    /**
     * start time for 认证令牌颁发时间
     */
    @Schema(description = "认证令牌颁发时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startAccessTokenIssuedAt;

    /**
     * end time for 认证令牌颁发时间
     */
    @Schema(description = "认证令牌颁发时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endAccessTokenIssuedAt;

    /**
     * 认证临牌失效时间
     */
    @Schema(description = "认证临牌失效时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime accessTokenExpiresAt;
    /**
     * start time for 认证临牌失效时间
     */
    @Schema(description = "认证临牌失效时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startAccessTokenExpiresAt;

    /**
     * end time for 认证临牌失效时间
     */
    @Schema(description = "认证临牌失效时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endAccessTokenExpiresAt;

    /**
     * 认证令牌元数据信息
     */
    @Schema(description = "认证令牌元数据信息")
    private String accessTokenMetadata;

    /**
     * 认证令牌类型
     */
    @Schema(description = "认证令牌类型")
    private String accessTokenType;

    /**
     * 认证令牌范围
     */
    @Schema(description = "认证令牌范围")
    private String accessTokenScopes;

    /**
     * 刷新令牌值
     */
    @Schema(description = "刷新令牌值")
    private String refreshTokenValue;

    /**
     * 刷新令牌颁发时间
     */
    @Schema(description = "刷新令牌颁发时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime refreshTokenIssuedAt;
    /**
     * start time for 刷新令牌颁发时间
     */
    @Schema(description = "刷新令牌颁发时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startRefreshTokenIssuedAt;

    /**
     * end time for 刷新令牌颁发时间
     */
    @Schema(description = "刷新令牌颁发时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endRefreshTokenIssuedAt;

    /**
     * 刷新令牌过期时间
     */
    @Schema(description = "刷新令牌过期时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime refreshTokenExpiresAt;
    /**
     * start time for 刷新令牌过期时间
     */
    @Schema(description = "刷新令牌过期时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startRefreshTokenExpiresAt;

    /**
     * end time for 刷新令牌过期时间
     */
    @Schema(description = "刷新令牌过期时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endRefreshTokenExpiresAt;

    /**
     * 元数据信息
     */
    @Schema(description = "元数据信息")
    private String refreshTokenMetadata;

    /**
     *
     */
    @Schema(description = "")
    private String oidcIdTokenValue;

    /**
     *
     */
    @Schema(description = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime oidcIdTokenIssuedAt;
    /**
     * start time for
     */
    @Schema(description = "-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startOidcIdTokenIssuedAt;

    /**
     * end time for
     */
    @Schema(description = "-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endOidcIdTokenIssuedAt;

    /**
     *
     */
    @Schema(description = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime oidcIdTokenExpiresAt;
    /**
     * start time for
     */
    @Schema(description = "-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startOidcIdTokenExpiresAt;

    /**
     * end time for
     */
    @Schema(description = "-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endOidcIdTokenExpiresAt;

    /**
     *
     */
    @Schema(description = "")
    private String oidcIdTokenClaims;

    /**
     *
     */
    @Schema(description = "")
    private String oidcIdTokenMetadata;

    public Authorization toEntity() {
        Authorization authorization = new Authorization();
        authorization.setId(this.id);
        authorization.setAppid(this.appid);
        authorization.setUserId(this.userId);
        authorization.setAuthorizationGrantType(this.authorizationGrantType);
        authorization.setAuthorizedScopes(this.authorizedScopes);
        authorization.setAttributes(this.attributes);
        authorization.setState(this.state);
        authorization.setAuthorizationCodeValue(this.authorizationCodeValue);
        authorization.setAuthorizationCodeIssuedAt(this.authorizationCodeIssuedAt);
        authorization.setAuthorizationCodeExpiresAt(this.authorizationCodeExpiresAt);
        authorization.setAuthorizationCodeMetadata(this.authorizationCodeMetadata);
        authorization.setAccessTokenValue(this.accessTokenValue);
        authorization.setAccessTokenIssuedAt(this.accessTokenIssuedAt);
        authorization.setAccessTokenExpiresAt(this.accessTokenExpiresAt);
        authorization.setAccessTokenMetadata(this.accessTokenMetadata);
        authorization.setAccessTokenType(this.accessTokenType);
        authorization.setAccessTokenScopes(this.accessTokenScopes);
        authorization.setRefreshTokenValue(this.refreshTokenValue);
        authorization.setRefreshTokenIssuedAt(this.refreshTokenIssuedAt);
        authorization.setRefreshTokenExpiresAt(this.refreshTokenExpiresAt);
        authorization.setRefreshTokenMetadata(this.refreshTokenMetadata);
        authorization.setOidcIdTokenValue(this.oidcIdTokenValue);
        authorization.setOidcIdTokenIssuedAt(this.oidcIdTokenIssuedAt);
        authorization.setOidcIdTokenExpiresAt(this.oidcIdTokenExpiresAt);
        authorization.setOidcIdTokenClaims(this.oidcIdTokenClaims);
        authorization.setOidcIdTokenMetadata(this.oidcIdTokenMetadata);
        return authorization;
    }
}
