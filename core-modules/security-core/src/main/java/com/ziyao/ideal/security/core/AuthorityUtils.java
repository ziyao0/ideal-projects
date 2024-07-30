package com.ziyao.ideal.security.core;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public abstract class AuthorityUtils {

    public static final List<GrantedAuthority> NO_AUTHORITIES = Collections.emptyList();

    private AuthorityUtils() {
    }

    public static List<GrantedAuthority> commaSeparatedStringToAuthorityList(String authorityString) {
        return createAuthorityList(StringUtils.tokenizeToStringArray(authorityString, ","));
    }

    public static Set<String> authorityListToSet(Collection<? extends GrantedAuthority> userAuthorities) {
        Assert.notNull(userAuthorities, "userAuthorities cannot be null");
        Set<String> set = new HashSet<>(userAuthorities.size());
        for (GrantedAuthority authority : userAuthorities) {
            set.add(authority.getAuthority());
        }
        return set;
    }

    public static List<GrantedAuthority> createAuthorityList(String... authorities) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(authorities.length);
        for (String authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority));
        }
        return grantedAuthorities;
    }

    public static List<GrantedAuthority> createAuthorityList(Collection<String> authorities) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(authorities.size());
        for (String authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority));
        }
        return grantedAuthorities;
    }
}
