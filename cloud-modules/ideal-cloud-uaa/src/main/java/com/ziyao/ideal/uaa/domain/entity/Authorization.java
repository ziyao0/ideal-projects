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
@Schema(description = "授权详情表")
public class Authorization implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id:主键id
     */
    @Id
    @Schema(description = "主键id")
    private Integer id;

    /**
     * appid:应用系统id
     */
    @Schema(description = "应用系统id")
    private Integer appid;

    /**
     * userId:
     */
    @Schema(description = "用户id")
    private Integer userId;

    /**
     * authorizationGrantType:授权类型
     */
    @Schema(description = "授权类型")
    private String authorizationGrantType;

    /**
     * authorizedScopes:授权范围
     */
    @Schema(description = "授权范围")
    private String authorizedScopes;

    /**
     * attributes:授权附加属性
     */
    @Schema(description = "授权附加属性")
    private String attributes;

    /**
     * state:状态 1正常 2失效
     */
    @Schema(description = "状态 1正常 2失效")
    private Integer state;

    /**
     * authorizationCodeValue:授权码值
     */
    @Schema(description = "授权码值")
    private String authorizationCodeValue;

    /**
     * authorizationCodeIssuedAt:授权码颁发时间
     */
    @Schema(description = "授权码颁发时间")
    private LocalDateTime authorizationCodeIssuedAt;

    /**
     * authorizationCodeExpiresAt:授权码失效时间
     */
    @Schema(description = "授权码失效时间")
    private LocalDateTime authorizationCodeExpiresAt;

    /**
     * authorizationCodeMetadata:授权码元数据信息
     */
    @Schema(description = "授权码元数据信息")
    private String authorizationCodeMetadata;

    /**
     * accessTokenValue:认证令牌值
     */
    @Schema(description = "认证令牌值")
    private String accessTokenValue;

    /**
     * accessTokenIssuedAt:认证令牌颁发时间
     */
    @Schema(description = "认证令牌颁发时间")
    private LocalDateTime accessTokenIssuedAt;

    /**
     * accessTokenExpiresAt:认证临牌失效时间
     */
    @Schema(description = "认证临牌失效时间")
    private LocalDateTime accessTokenExpiresAt;

    /**
     * accessTokenMetadata:认证令牌元数据信息
     */
    @Schema(description = "认证令牌元数据信息")
    private String accessTokenMetadata;

    /**
     * accessTokenType:认证令牌类型
     */
    @Schema(description = "认证令牌类型")
    private String accessTokenType;

    /**
     * accessTokenScopes:认证令牌范围
     */
    @Schema(description = "认证令牌范围")
    private String accessTokenScopes;

    /**
     * refreshTokenValue:刷新令牌值
     */
    @Schema(description = "刷新令牌值")
    private String refreshTokenValue;

    /**
     * refreshTokenIssuedAt:刷新令牌颁发时间
     */
    @Schema(description = "刷新令牌颁发时间")
    private LocalDateTime refreshTokenIssuedAt;

    /**
     * refreshTokenExpiresAt:刷新令牌过期时间
     */
    @Schema(description = "刷新令牌过期时间")
    private LocalDateTime refreshTokenExpiresAt;

    /**
     * refreshTokenMetadata:元数据信息
     */
    @Schema(description = "元数据信息")
    private String refreshTokenMetadata;

    /**
     * oidcIdTokenValue:
     */
    @Schema(description = "")
    private String oidcIdTokenValue;

    /**
     * oidcIdTokenIssuedAt:
     */
    @Schema(description = "")
    private LocalDateTime oidcIdTokenIssuedAt;

    /**
     * oidcIdTokenExpiresAt:
     */
    @Schema(description = "")
    private LocalDateTime oidcIdTokenExpiresAt;

    /**
     * oidcIdTokenClaims:
     */
    @Schema(description = "")
    private String oidcIdTokenClaims;

    /**
     * oidcIdTokenMetadata:
     */
    @Schema(description = "")
    private String oidcIdTokenMetadata;


    public static class Builder {

        private final Authorization authorization = new Authorization();

        public Builder id(Integer id) {
            this.authorization.id = id;
            return this;
        }

        public Builder appid(Integer appid) {
            this.authorization.appid = appid;
            return this;
        }

        public Builder userId(Integer userId) {
            this.authorization.userId = userId;
            return this;
        }

        public Builder authorizationGrantType(String authorizationGrantType) {
            this.authorization.authorizationGrantType = authorizationGrantType;
            return this;
        }

        public Builder authorizedScopes(String authorizedScopes) {
            this.authorization.authorizedScopes = authorizedScopes;
            return this;
        }

        public Builder attributes(String attributes) {
            this.authorization.attributes = attributes;
            return this;
        }

        public Builder state(Integer state) {
            this.authorization.state = state;
            return this;
        }

        public Builder authorizationCodeValue(String authorizationCodeValue) {
            this.authorization.authorizationCodeValue = authorizationCodeValue;
            return this;
        }

        public Builder authorizationCodeIssuedAt(LocalDateTime authorizationCodeIssuedAt) {
            this.authorization.authorizationCodeIssuedAt = authorizationCodeIssuedAt;
            return this;
        }

        public Builder authorizationCodeExpiresAt(LocalDateTime authorizationCodeExpiresAt) {
            this.authorization.authorizationCodeExpiresAt = authorizationCodeExpiresAt;
            return this;
        }

        public Builder authorizationCodeMetadata(String authorizationCodeMetadata) {
            this.authorization.authorizationCodeMetadata = authorizationCodeMetadata;
            return this;
        }

        public Builder accessTokenValue(String accessTokenValue) {
            this.authorization.accessTokenValue = accessTokenValue;
            return this;
        }

        public Builder accessTokenIssuedAt(LocalDateTime accessTokenIssuedAt) {
            this.authorization.accessTokenIssuedAt = accessTokenIssuedAt;
            return this;
        }

        public Builder accessTokenExpiresAt(LocalDateTime accessTokenExpiresAt) {
            this.authorization.accessTokenExpiresAt = accessTokenExpiresAt;
            return this;
        }

        public Builder accessTokenMetadata(String accessTokenMetadata) {
            this.authorization.accessTokenMetadata = accessTokenMetadata;
            return this;
        }

        public Builder accessTokenType(String accessTokenType) {
            this.authorization.accessTokenType = accessTokenType;
            return this;
        }

        public Builder accessTokenScopes(String accessTokenScopes) {
            this.authorization.accessTokenScopes = accessTokenScopes;
            return this;
        }

        public Builder refreshTokenValue(String refreshTokenValue) {
            this.authorization.refreshTokenValue = refreshTokenValue;
            return this;
        }

        public Builder refreshTokenIssuedAt(LocalDateTime refreshTokenIssuedAt) {
            this.authorization.refreshTokenIssuedAt = refreshTokenIssuedAt;
            return this;
        }

        public Builder refreshTokenExpiresAt(LocalDateTime refreshTokenExpiresAt) {
            this.authorization.refreshTokenExpiresAt = refreshTokenExpiresAt;
            return this;
        }

        public Builder refreshTokenMetadata(String refreshTokenMetadata) {
            this.authorization.refreshTokenMetadata = refreshTokenMetadata;
            return this;
        }

        public Builder oidcIdTokenValue(String oidcIdTokenValue) {
            this.authorization.oidcIdTokenValue = oidcIdTokenValue;
            return this;
        }

        public Builder oidcIdTokenIssuedAt(LocalDateTime oidcIdTokenIssuedAt) {
            this.authorization.oidcIdTokenIssuedAt = oidcIdTokenIssuedAt;
            return this;
        }

        public Builder oidcIdTokenExpiresAt(LocalDateTime oidcIdTokenExpiresAt) {
            this.authorization.oidcIdTokenExpiresAt = oidcIdTokenExpiresAt;
            return this;
        }

        public Builder oidcIdTokenClaims(String oidcIdTokenClaims) {
            this.authorization.oidcIdTokenClaims = oidcIdTokenClaims;
            return this;
        }

        public Builder oidcIdTokenMetadata(String oidcIdTokenMetadata) {
            this.authorization.oidcIdTokenMetadata = oidcIdTokenMetadata;
            return this;
        }


        public Authorization build() {
            return this.authorization;
        }
    }
}
