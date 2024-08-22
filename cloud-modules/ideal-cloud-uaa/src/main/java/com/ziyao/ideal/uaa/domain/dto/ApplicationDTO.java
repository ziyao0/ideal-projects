package com.ziyao.ideal.uaa.domain.dto;

import com.ziyao.ideal.uaa.domain.convertor.ApplicationConvertor;
import com.ziyao.ideal.uaa.domain.entity.Application;
import com.ziyao.ideal.web.orm.EntityDTO;
import lombok.Data;

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
public class ApplicationDTO implements EntityDTO<Application>, Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Integer appId;
    /**
     * 应用类型 0内部系统应用 1三方平台应用
     */
    private Integer appType;
    /**
     * 客户端允许的授权类型
     */
    private String authorizationGrantTypes;
    /**
     * 授权范围
     */
    private String scopes;
    /**
     * 删除状态 0正常 1失效
     */
    private Integer state;
    /**
     * 颁发时间
     */
    private LocalDateTime issuedAt;
    /**
     * 应用秘钥
     */
    private String appSecret;
    /**
     * 应用秘钥过期时间
     */
    private LocalDateTime appSecretExpiresAt;
    /**
     * 应用名称
     */
    private String appName;
    /**
     * 系统重定向路径
     */
    private String redirectUri;
    /**
     *
     */
    private String postLogoutRedirectUri;
    /**
     *
     */
    private String tokenSettings;
    /**
     * 系统简介
     */
    private String remark;

    public Application convert() {
        return ApplicationConvertor.INSTANCE.convert(this);
    }
}
