package com.ziyao.ideal.uaa.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

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
@Getter
@Setter
@Builder
@Entity(name = "authorization")
@NoArgsConstructor
@AllArgsConstructor
public class Authorization implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * id:主键id
     */
    @Id
    private Integer id;

    /**
     * appid:应用系统id
     */
    private Integer appid;

    /**
     * userId:
     */
    private Integer userId;

    /**
     * authorizationGrantType:授权类型
     */
    private String authorizationGrantType;

    /**
     * authorizedScopes:授权范围
     */
    private String authorizedScopes;

    /**
     * attributes:授权附加属性
     */
    private String attributes;

    /**
     * state:状态 1正常 2失效
     */
    private Integer state;

    /**
     * authorizationCodeValue:授权码值
     */
    private String authorizationCodeValue;

    /**
     * authorizationCodeIssuedAt:授权码颁发时间
     */
    private LocalDateTime authorizationCodeIssuedAt;

    /**
     * authorizationCodeExpiresAt:授权码失效时间
     */
    private LocalDateTime authorizationCodeExpiresAt;

    /**
     * authorizationCodeMetadata:授权码元数据信息
     */
    private String authorizationCodeMetadata;

    /**
     * accessTokenValue:认证令牌值
     */
    private String accessTokenValue;

    /**
     * accessTokenIssuedAt:认证令牌颁发时间
     */
    private LocalDateTime accessTokenIssuedAt;

    /**
     * accessTokenExpiresAt:认证临牌失效时间
     */
    private LocalDateTime accessTokenExpiresAt;

    /**
     * accessTokenMetadata:认证令牌元数据信息
     */
    private String accessTokenMetadata;

    /**
     * accessTokenType:认证令牌类型
     */
    private String accessTokenType;

    /**
     * accessTokenScopes:认证令牌范围
     */
    private String accessTokenScopes;

    /**
     * refreshTokenValue:刷新令牌值
     */
    private String refreshTokenValue;

    /**
     * refreshTokenIssuedAt:刷新令牌颁发时间
     */
    private LocalDateTime refreshTokenIssuedAt;

    /**
     * refreshTokenExpiresAt:刷新令牌过期时间
     */
    private LocalDateTime refreshTokenExpiresAt;

    /**
     * refreshTokenMetadata:元数据信息
     */
    private String refreshTokenMetadata;

    /**
     * oidcIdTokenValue:
     */
    private String oidcIdTokenValue;

    /**
     * oidcIdTokenIssuedAt:
     */
    private LocalDateTime oidcIdTokenIssuedAt;

    /**
     * oidcIdTokenExpiresAt:
     */
    private LocalDateTime oidcIdTokenExpiresAt;

    /**
     * oidcIdTokenClaims:
     */
    private String oidcIdTokenClaims;

    /**
     * oidcIdTokenMetadata:
     */
    private String oidcIdTokenMetadata;
}
