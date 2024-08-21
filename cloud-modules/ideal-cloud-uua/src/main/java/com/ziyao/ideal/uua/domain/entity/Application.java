package com.ziyao.ideal.uua.domain.entity;

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
public class Application implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * appId:主键id
     */
    @Id
    private Integer appId;

    /**
     * appType:应用类型 0内部系统应用 1三方平台应用
     */
    private Integer appType;

    /**
     * authorizationGrantTypes:客户端允许的授权类型
     */
    private String authorizationGrantTypes;

    /**
     * scopes:授权范围
     */
    private String scopes;

    /**
     * state:删除状态 0正常 1失效
     */
    private Integer state;

    /**
     * issuedAt:颁发时间
     */
    private LocalDateTime issuedAt;

    /**
     * appSecret:应用秘钥
     */
    private String appSecret;

    /**
     * appSecretExpiresAt:应用秘钥过期时间
     */
    private LocalDateTime appSecretExpiresAt;

    /**
     * appName:应用名称
     */
    private String appName;

    /**
     * redirectUri:系统重定向路径
     */
    private String redirectUri;

    /**
     * postLogoutRedirectUri:
     */
    private String postLogoutRedirectUri;

    /**
     * tokenSettings:
     */
    private String tokenSettings;

    /**
     * remark:系统简介
     */
    private String remark;
}
