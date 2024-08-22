package com.ziyao.ideal.uua.authentication.token;

import com.ziyao.ideal.security.core.AbstractAuthenticationToken;
import com.ziyao.ideal.security.core.GrantedAuthority;
import com.ziyao.ideal.security.oauth2.core.user.OAuth2User;
import lombok.Getter;

import java.util.Collection;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class OAuth2AuthenticationToken extends AbstractAuthenticationToken {


    private static final long serialVersionUID = 3485826886509511328L;

    private final OAuth2User principal;
    @Getter
    private final String registrationId;

    /**
     * 使用提供的颁发机构数组创建令牌。
     *
     * @param authorities 此身份验证对象表示的主体的 GrantedAuthority 集合。
     */
    public OAuth2AuthenticationToken(OAuth2User principal,
                                     Collection<? extends GrantedAuthority> authorities, String registrationId) {
        super(authorities);
        this.principal = principal;
        this.registrationId = registrationId;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public Object getCredentials() {
        return "";
    }
}
