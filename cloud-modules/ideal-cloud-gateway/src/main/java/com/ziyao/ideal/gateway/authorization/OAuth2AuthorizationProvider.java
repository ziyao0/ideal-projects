package com.ziyao.ideal.gateway.authorization;

import com.ziyao.ideal.core.Assert;
import org.springframework.stereotype.Component;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Component
public class OAuth2AuthorizationProvider implements AuthorizationProvider {
    /**
     * 时间戳默认有效时长为 60秒.
     */
    private static final long TIMESTAMP_EXPIRATION_TIME = 60 * 1000L;


    @Override
    public Authorization authorize(Authorization authorization) {
        OAuth2AuthorizationToken authorizationToken = (OAuth2AuthorizationToken) authorization;
        Assert.notNull(authorizationToken, "缺少安全验证信息");
        Assert.notNull(authorizationToken.getAccessToken(), "缺少认证头(Authorization)");
        Assert.notNull(authorizationToken.getTimestamp(), "缺失时间戳(timestamp)");
        Assert.isTimestampNotExpire(String.valueOf(authorizationToken.getTimestamp()), getTimestampValidity());
        return null;
    }

    private static long getTimestampValidity() {
        return TIMESTAMP_EXPIRATION_TIME;
    }

    @Override
    public boolean supports(Class<? extends Authorization> authorizationClass) {
        return false;
    }
}
