package com.ziyao.ideal.security.oauth2.core.oidc;

import com.ziyao.ideal.security.oauth2.core.OidcIdToken;
import com.ziyao.ideal.security.oauth2.core.user.OAuth2User;

import java.util.Map;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public interface OidcUser extends OAuth2User, IdTokenClaimAccessor {

    /**
     * 返回有关用户的声明。声明是从 {@link #getIdToken()}获取（如果可用）。
     *
     * @return a {@code Map} of claims about the user
     */
    @Override
    Map<String, Object> getClaims();

    /**
     * Returns the {@link OidcIdToken ID Token} containing claims about the user.
     *
     * @return the {@link OidcIdToken} containing claims about the user.
     */
    OidcIdToken getIdToken();
}
