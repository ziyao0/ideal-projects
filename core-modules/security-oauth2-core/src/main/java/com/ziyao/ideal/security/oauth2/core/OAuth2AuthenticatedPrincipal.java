package com.ziyao.ideal.security.oauth2.core;

import com.ziyao.ideal.security.core.AuthenticatedPrincipal;
import com.ziyao.ideal.security.core.GrantedAuthority;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Map;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public interface OAuth2AuthenticatedPrincipal extends AuthenticatedPrincipal {

    /**
     * 按名称获取 OAuth 2.0 令牌属性
     *
     * @param name 属性的名称
     * @param <A>  属性类型
     * @return 获取属性值，如果没有则返回{@code null}
     */
    @Nullable
    @SuppressWarnings("unchecked")
    default <A> A getAttribute(String name) {
        return (A) getAttributes().get(name);
    }

    /**
     * 获取 OAuth 2.0 令牌属性
     *
     * @return OAuth 2.0 令牌属性
     */
    Map<String, Object> getAttributes();

    /**
     * 获取与此 OAuth 2.0 令牌关联的 {@link GrantedAuthority} 的 {@link Collection}
     *
     * @return OAuth 2.0 令牌颁发机构
     */
    Collection<? extends GrantedAuthority> getAuthorities();

}
