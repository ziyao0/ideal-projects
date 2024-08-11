package com.ziyao.ideal.gateway.authorization.token;

import com.auth0.jwt.interfaces.Claim;
import com.ziyao.ideal.core.Collections;
import lombok.Getter;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ziyao zhang
 */
@Getter
public class SuccessfulAuthorization implements Authorization {

    @Serial
    private static final long serialVersionUID = 1906018666555325676L;
    private transient Map<String, Object> claims;
    private final String token;

    public SuccessfulAuthorization(String token) {
        this.token = token;
    }

    public SuccessfulAuthorization(Map<String, ?> claims, String token) {
        if (Collections.isEmpty(claims)) {
            this.claims = new HashMap<>();
        } else {
            Map<String, Object> finalClaims = new HashMap<>(claims.size());
            for (Map.Entry<String, ?> entry : claims.entrySet()) {
                if (entry.getValue() instanceof Claim claim) {
                    finalClaims.put(entry.getKey(), claim.as(Object.class));
                } else {
                    finalClaims.put(entry.getKey(), entry.getValue());
                }
            }
            this.claims = finalClaims;
        }
        this.token = token;
    }

    @Override
    public String getToken() {
        return this.token;
    }

    @Override
    public boolean isAuthorized() {
        return true;
    }
}
