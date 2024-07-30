package com.ziyao.ideal.security.oauth2.core.user;

import com.ziyao.ideal.security.core.AuthorityUtils;
import com.ziyao.ideal.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class DefaultOAuth2User implements OAuth2User, Serializable {

    @Serial
    private static final long serialVersionUID = 4951075650242467559L;

    private final Set<GrantedAuthority> authorities;

    private final Map<String, Object> attributes;

    private final String nameAttributeKey;

    public DefaultOAuth2User(Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes,
                             String nameAttributeKey) {
        Assert.notEmpty(attributes, "attributes cannot be empty");
        Assert.hasText(nameAttributeKey, "nameAttributeKey cannot be empty");
        if (!attributes.containsKey(nameAttributeKey)) {
            throw new IllegalArgumentException("Missing attribute '" + nameAttributeKey + "' in attributes");
        } else {
            this.authorities = authorities != null
                    ? Collections.unmodifiableSet(new LinkedHashSet<>(this.sortAuthorities(authorities)))
                    : Collections.unmodifiableSet(new LinkedHashSet<>(AuthorityUtils.NO_AUTHORITIES));
            this.attributes = Collections.unmodifiableMap(new LinkedHashMap<>(attributes));
            this.nameAttributeKey = nameAttributeKey;
        }
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getName() {
        Object attribute = this.getAttribute(this.nameAttributeKey);
        if (attribute == null) {
            return null;
        }
        return attribute.toString();
    }

    private Set<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(Comparator.comparing(GrantedAuthority::getAuthority));
        sortedAuthorities.addAll(authorities);
        return sortedAuthorities;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj != null && this.getClass() == obj.getClass()) {
            DefaultOAuth2User that = (DefaultOAuth2User) obj;
            if (!this.getName().equals(that.getName())) {
                return false;
            } else {
                return this.getAuthorities().equals(that.getAuthorities())
                        && this.getAttributes().equals(that.getAttributes());
            }
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = this.getName().hashCode();
        result = 31 * result + this.getAuthorities().hashCode();
        result = 31 * result + this.getAttributes().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Name: [" +
                this.getName() +
                "], Granted Authorities: [" +
                getAuthorities() +
                "], User Attributes: [" +
                getAttributes() +
                "]";
    }
}
