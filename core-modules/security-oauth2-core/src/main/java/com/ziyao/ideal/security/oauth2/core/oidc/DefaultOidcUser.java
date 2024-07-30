package com.ziyao.ideal.security.oauth2.core.oidc;

import com.ziyao.ideal.security.core.GrantedAuthority;
import com.ziyao.ideal.security.oauth2.core.OidcIdToken;
import com.ziyao.ideal.security.oauth2.core.user.DefaultOAuth2User;

import java.io.Serial;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class DefaultOidcUser extends DefaultOAuth2User implements OidcUser {

    @Serial
    private static final long serialVersionUID = -1801917951775714662L;

    private final OidcIdToken idToken;

    public DefaultOidcUser(Collection<? extends GrantedAuthority> authorities, OidcIdToken idToken) {
        this(authorities, idToken, IdTokenClaimNames.SUB);
    }

    public DefaultOidcUser(Set<GrantedAuthority> authorities, Map<String, Object> attributes,
                           String nameAttributeKey, OidcIdToken idToken) {
        super(authorities, attributes, nameAttributeKey);
        this.idToken = idToken;
    }

    public DefaultOidcUser(Collection<? extends GrantedAuthority> authorities, OidcIdToken idToken,
                           String nameAttributeKey) {
        super(authorities, OidcUserAuthority.collectClaims(idToken), nameAttributeKey);
        this.idToken = idToken;
    }


    @Override
    public Map<String, Object> getClaims() {
        return this.getAttributes();
    }

    @Override
    public OidcIdToken getIdToken() {
        return this.idToken;
    }
}
