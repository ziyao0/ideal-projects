package com.ziyao.ideal.gateway.core.token;

import java.io.Serializable;

/**
 * @author ziyao
 */
public interface AccessToken extends Serializable {

    /**
     * 访问令牌
     */
    String getToken();

    /**
     * 刷新令牌
     */
    String getRefreshToken();

    /**
     * 获取时间戳
     */
    String getTimestamp();

}
