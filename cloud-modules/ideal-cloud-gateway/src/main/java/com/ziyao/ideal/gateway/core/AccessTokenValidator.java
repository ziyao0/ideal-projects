package com.ziyao.ideal.gateway.core;

import com.ziyao.ideal.core.Assert;
import com.ziyao.ideal.gateway.core.token.AccessToken;

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
     * @param accessToken 访问令牌
     */
    public static void validateToken(AccessToken accessToken) {
        doValidated(accessToken);
    }

    private static void doValidated(AccessToken accessToken) {
        Assert.notNull(accessToken, "缺少安全验证信息");
        Assert.notNull(accessToken.getToken(), "缺少认证头(Authorization)");
        Assert.notNull(accessToken.getTimestamp(), "缺失时间戳(timestamp)");
        Assert.isTimestampNotExpire(accessToken.getTimestamp(), getTimestampValidity());
    }

    private static long getTimestampValidity() {
        return TIMESTAMP_EXPIRATION_TIME;
    }


    private AccessTokenValidator() {
    }
}
