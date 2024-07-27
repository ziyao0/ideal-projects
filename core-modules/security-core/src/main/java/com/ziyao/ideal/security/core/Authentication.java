package com.ziyao.ideal.security.core;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author ziyao
 */
public interface Authentication extends Serializable {

    Object getPrincipal();

    Object getCredentials();

    Collection<? extends GrantedAuthority> getAuthorities();

    boolean isAuthenticated();

    void setAuthenticated(boolean authenticated);
}
