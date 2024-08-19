package com.ziyao.ideal.security.oauth2.core.oidc;

import com.ziyao.ideal.security.oauth2.core.OidcIdToken;
import com.ziyao.ideal.security.oauth2.core.user.OAuth2UserAuthority;
import lombok.Getter;
import org.springframework.util.Assert;


import java.util.HashMap;
import java.util.Map;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public class OidcUserAuthority extends OAuth2UserAuthority {


    
    private static final long serialVersionUID = 1523780882922504924L;

    private final OidcIdToken idToken;


    public OidcUserAuthority(OidcIdToken idToken) {
        this("OIDC_USER", idToken);
    }

    public OidcUserAuthority(String authority, OidcIdToken idToken) {
        super(authority, collectClaims(idToken));
        this.idToken = idToken;
    }

    static Map<String, Object> collectClaims(OidcIdToken idToken) {
        Assert.notNull(idToken, "idToken cannot be null");
        return new HashMap<>(idToken.getClaims());
    }

}
