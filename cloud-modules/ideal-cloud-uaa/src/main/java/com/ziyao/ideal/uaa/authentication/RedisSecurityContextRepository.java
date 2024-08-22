package com.ziyao.ideal.uaa.authentication;

import com.ziyao.ideal.security.core.context.SecurityContext;
import com.ziyao.ideal.security.core.context.SecurityContextRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Repository
public class RedisSecurityContextRepository implements SecurityContextRepository<String> {

    private static final String SECURITY_CONTEXT_KEY = "system:security:context:";

    @Override
    public SecurityContext loadContext(String request) {
        return null;
    }

    @Override
    public void saveContext(SecurityContext context) {

    }

    @Override
    public boolean containsContext(String request) {
        return false;
    }

}
