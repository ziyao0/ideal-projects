package com.ziyao.ideal.gateway.security;

import com.ziyao.ideal.core.Assert;

/**
 * @author ziyao zhang
 */
public abstract class AccessTokenValidator {

    /**
     * 时间戳默认有效时长为 60秒.
     */
    private static final long TIMESTAMP_EXPIRATION_TIME = 60 * 1000L;

    /**
     * 快速校验访问令牌
     *
     * @param securityContext 访问令牌
     */
    public static void validateToken(SessionContext securityContext) {
        doValidated(securityContext);
    }

    private static void doValidated(SessionContext securityContext) {
        Assert.notNull(securityContext, "缺少安全验证信息");
        Assert.notNull(securityContext.getToken(), "缺少认证头(Authorization)");
        Assert.notNull(securityContext.getTimestamp(), "缺失时间戳(timestamp)");
        Assert.isTimestampNotExpire(securityContext.getTimestamp(), getTimestampValidity());
    }

    private static long getTimestampValidity() {
        return TIMESTAMP_EXPIRATION_TIME;
    }


    private AccessTokenValidator() {
    }
}
