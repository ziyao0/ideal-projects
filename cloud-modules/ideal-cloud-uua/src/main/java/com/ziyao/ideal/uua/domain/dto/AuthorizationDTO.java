package com.ziyao.ideal.uua.domain.dto;

import com.ziyao.ideal.uua.domain.convertor.AuthorizationConvertor;
import com.ziyao.ideal.uua.domain.entity.Authorization;
import com.ziyao.ideal.web.orm.EntityDTO;
import lombok.Data;

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
public class AuthorizationDTO implements EntityDTO<Authorization>, Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 应用系统id
     */
    private Integer appid;
    /**
     *
     */
    private Integer userId;
    /**
     * 授权类型
     */
    private String authorizationGrantType;
    /**
     * 授权范围
     */
    private String authorizedScopes;
    /**
     * 授权附加属性
     */
    private String attributes;
    /**
     * 状态 1正常 2失效
     */
    private Integer state;
    /**
     * 授权码值
     */
    private String authorizationCodeValue;
    /**
     * 授权码颁发时间
     */
    private LocalDateTime authorizationCodeIssuedAt;
    /**
     * 授权码失效时间
     */
    private LocalDateTime authorizationCodeExpiresAt;
    /**
     * 授权码元数据信息
     */
    private String authorizationCodeMetadata;
    /**
     * 认证令牌值
     */
    private String accessTokenValue;
    /**
     * 认证令牌颁发时间
     */
    private LocalDateTime accessTokenIssuedAt;
    /**
     * 认证临牌失效时间
     */
    private LocalDateTime accessTokenExpiresAt;
    /**
     * 认证令牌元数据信息
     */
    private String accessTokenMetadata;
    /**
     * 认证令牌类型
     */
    private String accessTokenType;
    /**
     * 认证令牌范围
     */
    private String accessTokenScopes;
    /**
     * 刷新令牌值
     */
    private String refreshTokenValue;
    /**
     * 刷新令牌颁发时间
     */
    private LocalDateTime refreshTokenIssuedAt;
    /**
     * 刷新令牌过期时间
     */
    private LocalDateTime refreshTokenExpiresAt;
    /**
     * 元数据信息
     */
    private String refreshTokenMetadata;
    /**
     *
     */
    private String oidcIdTokenValue;
    /**
     *
     */
    private LocalDateTime oidcIdTokenIssuedAt;
    /**
     *
     */
    private LocalDateTime oidcIdTokenExpiresAt;
    /**
     *
     */
    private String oidcIdTokenClaims;
    /**
     *
     */
    private String oidcIdTokenMetadata;

    public Authorization convert() {
        return AuthorizationConvertor.INSTANCE.convert(this);
    }
}
