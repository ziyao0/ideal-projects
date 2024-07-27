package com.ziyao.ideal.usercenter.authentication.token;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.security.core.CredentialsContainer;
import com.ziyao.ideal.security.core.GrantedAuthority;
import com.ziyao.ideal.security.core.UserDetails;
import lombok.Getter;

import java.io.Serial;
import java.util.*;

/**
 * @author ziyao
 */
public abstract class AbstractAuthenticationToken implements Authentication, CredentialsContainer {
    @Serial
    private static final long serialVersionUID = 6449672296011987802L;

    private final Collection<? extends GrantedAuthority> authorities;

    private boolean authenticated;

    //附加信息
    @Getter
    private Map<String, Object> additionalInformation;

    public AbstractAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        if (authorities == null) {
            this.authorities = Set.of();
            return;
        }
        Collection<GrantedAuthority> f = new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            if (authority != null) f.add(authority);
        }
        this.authorities = Collections.unmodifiableCollection(f);
        additionalInformation = new HashMap<>(8);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public void setAdditionalInformation(Map<String, Object> additionalInformation) {
        this.additionalInformation = Objects.requireNonNullElse(additionalInformation, Collections.emptyMap());
    }

    public void setAdditionalInfo(String key, Object value) {
        additionalInformation.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T getAdditionalInfo(String key, Class<T> type) {
        return (T) additionalInformation.get(key);
    }

    public String getName() {
        if (getPrincipal() == null) {
            return Strings.EMPTY;
        }
        if (getPrincipal() instanceof UserDetails userDetails) {
            return userDetails.getUsername();
        }
        return getPrincipal().toString();
    }

    @Override
    public void eraseCredentials() {
        if (getCredentials() instanceof CredentialsContainer credentialsContainer) {
            credentialsContainer.eraseCredentials();
        }
    }
}
