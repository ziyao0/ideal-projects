package com.ziyao.ideal.security.core.context;

import com.ziyao.ideal.security.core.Authentication;
import org.springframework.util.ObjectUtils;

import java.io.Serial;

/**
 * @author ziyao zhang
 */
public class SecurityContextImpl implements SecurityContext {
    @Serial
    private static final long serialVersionUID = -6481012224001521435L;

    private Authentication authentication;

    public SecurityContextImpl() {
        this(null);
    }

    public SecurityContextImpl(Authentication authentication) {
        this.authentication = authentication;
    }

    @Override
    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(this.authentication);
    }

    @Override
    public Authentication getAuthentication() {
        return this.authentication;
    }

    @Override
    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SecurityContextImpl other) {
            if ((this.getAuthentication() == null) && (other.getAuthentication() == null)) {
                return true;
            }
            return (this.getAuthentication() != null) && (other.getAuthentication() != null)
                    && this.getAuthentication().equals(other.getAuthentication());
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append(" [");
        if (this.authentication == null) {
            sb.append("Null authentication");
        } else {
            sb.append("Authentication=").append(this.authentication);
        }
        sb.append("]");
        return sb.toString();
    }
}
