package com.ziyao.ideal.uua.authentication.core;

import com.ziyao.ideal.core.Assert;
import com.ziyao.ideal.security.core.GrantedAuthority;
import com.ziyao.ideal.security.core.Permission;

import java.io.Serial;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

/**
 * @author ziyao
 */
public class SimpleGrantedAuthority implements GrantedAuthority {
    @Serial
    private static final long serialVersionUID = 1944660992125502463L;

    private final String role;

    private final Collection<Permission> permissions;

    public SimpleGrantedAuthority(String role) {
        this(role, Set.of());
    }

    public SimpleGrantedAuthority(String role, Collection<Permission> permissions) {
        Assert.hasText(role, "role must not be empty");
        this.role = role;
        this.permissions = permissions;
    }

    @Override
    public String getAuthority() {
        return this.role;
    }

    @Override
    public Collection<Permission> getPermissions() {
        return this.permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleGrantedAuthority that = (SimpleGrantedAuthority) o;
        return Objects.equals(role, that.role) && Objects.equals(permissions, that.permissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, permissions);
    }
}
