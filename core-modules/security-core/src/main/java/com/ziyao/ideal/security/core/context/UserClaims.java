package com.ziyao.ideal.security.core.context;

import com.ziyao.ideal.core.Assert;
import com.ziyao.ideal.core.Collections;


import java.util.HashMap;
import java.util.Map;

/**
 * @author ziyao zhang
 */
public class UserClaims implements PrincipalClaims {


    
    private static final long serialVersionUID = 4157668045536145164L;

    private final Map<String, Object> claims;

    public UserClaims() {
        this(new HashMap<>());
    }

    public UserClaims(Map<String, Object> claims) {
        if (Collections.isEmpty(claims)) {
            this.claims = new HashMap<>();
        } else {
            this.claims = claims;
        }
    }

    @Override
    public Map<String, Object> getClaims() {
        return this.claims;
    }

    @Override
    public void setClaim(String key, Object value) {
        Assert.notNull(key, "key must not be null");
        Assert.notNull(value, "value must not be null");
        this.claims.put(key, value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getClaim(String key) {
        return (T) this.claims.get(key);
    }

}
