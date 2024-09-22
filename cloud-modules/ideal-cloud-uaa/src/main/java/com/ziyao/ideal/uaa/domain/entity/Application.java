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
 * 应用系统
 * </p>
 *
 * @author ziyao
 */
@Getter
@Setter
@Builder
@Entity(name = "application")
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "应用系统")
public class Application implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * appId:主键id
     */
    @Id
    @Schema(description = "主键id")
    private Integer appId;

    /**
     * appType:应用类型 0内部系统应用 1三方平台应用
     */
    @Schema(description = "应用类型 0内部系统应用 1三方平台应用 ")
    private Integer appType;

    /**
     * authorizationGrantTypes:客户端允许的授权类型
     */
    @Schema(description = "客户端允许的授权类型")
    private String authorizationGrantTypes;

    /**
     * scopes:授权范围
     */
    @Schema(description = "授权范围")
    private String scopes;

    /**
     * state:删除状态 0正常 1失效
     */
    @Schema(description = "删除状态 0正常 1失效")
    private Integer state;

    /**
     * issuedAt:颁发时间
     */
    @Schema(description = "颁发时间")
    private LocalDateTime issuedAt;

    /**
     * appSecret:应用秘钥
     */
    @Schema(description = "应用秘钥")
    private String appSecret;

    /**
     * appSecretExpiresAt:应用秘钥过期时间
     */
    @Schema(description = "应用秘钥过期时间")
    private LocalDateTime appSecretExpiresAt;

    /**
     * appName:应用名称
     */
    @Schema(description = "应用名称")
    private String appName;

    /**
     * redirectUri:系统重定向路径
     */
    @Schema(description = "系统重定向路径")
    private String redirectUri;

    /**
     * postLogoutRedirectUri:
     */
    @Schema(description = "")
    private String postLogoutRedirectUri;

    /**
     * tokenSettings:
     */
    @Schema(description = "")
    private String tokenSettings;

    /**
     * remark:系统简介
     */
    @Schema(description = "系统简介")
    private String remark;


    public static class Builder {

        private final Application application = new Application();

        public Builder appId(Integer appId) {
            this.application.appId = appId;
            return this;
        }

        public Builder appType(Integer appType) {
            this.application.appType = appType;
            return this;
        }

        public Builder authorizationGrantTypes(String authorizationGrantTypes) {
            this.application.authorizationGrantTypes = authorizationGrantTypes;
            return this;
        }

        public Builder scopes(String scopes) {
            this.application.scopes = scopes;
            return this;
        }

        public Builder state(Integer state) {
            this.application.state = state;
            return this;
        }

        public Builder issuedAt(LocalDateTime issuedAt) {
            this.application.issuedAt = issuedAt;
            return this;
        }

        public Builder appSecret(String appSecret) {
            this.application.appSecret = appSecret;
            return this;
        }

        public Builder appSecretExpiresAt(LocalDateTime appSecretExpiresAt) {
            this.application.appSecretExpiresAt = appSecretExpiresAt;
            return this;
        }

        public Builder appName(String appName) {
            this.application.appName = appName;
            return this;
        }

        public Builder redirectUri(String redirectUri) {
            this.application.redirectUri = redirectUri;
            return this;
        }

        public Builder postLogoutRedirectUri(String postLogoutRedirectUri) {
            this.application.postLogoutRedirectUri = postLogoutRedirectUri;
            return this;
        }

        public Builder tokenSettings(String tokenSettings) {
            this.application.tokenSettings = tokenSettings;
            return this;
        }

        public Builder remark(String remark) {
            this.application.remark = remark;
            return this;
        }


        public Application build() {
            return this.application;
        }
    }
}
