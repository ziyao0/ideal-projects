package com.ziyao.ideal.security.oauth2.core;

import java.time.Instant;

/**
 * @author ziyao zhang
 */
public interface OAuth2Token extends Token {

    /**
     * Returns the time at which the token was issued.
     *
     * @return the time the token was issued or {@code null}
     */
    default Instant getIssuedAt() {
        return null;
    }

    /**
     * Returns the expiration time on or after which the token MUST NOT be accepted.
     *
     * @return the token expiration time or {@code null}
     */
    default Instant getExpiresAt() {
        return null;
    }
}
