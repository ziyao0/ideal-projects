package com.ziyao.ideal.security.core.context;

import java.io.Serializable;
import java.util.Map;

/**
 * @author ziyao zhang
 */
public interface PrincipalClaims extends Serializable {

    /**
     * 获取所有声明的信息
     *
     * @return claims
     */
    Map<String, Object> getClaims();

    /**
     * 设置声明
     */
    void setClaim(String key, Object value);

    /**
     * get claim
     *
     * @param key key
     * @param <T> value type
     * @return value
     */
    @SuppressWarnings("unchecked")
    default <T> T getClaim(String key) {
        return (T) getClaims().get(key);
    }

    default String getAccessIp() {
        return getClaim(PrincipalParamNames.ACCESS_IP);
    }

    default void setAccessIp(String accessIp) {
        setClaim(PrincipalParamNames.ACCESS_IP, accessIp);
    }

    default String getBrowserName() {
        return getClaim(PrincipalParamNames.BROWSER_NAME);
    }

    default void setBrowserName(String browserName) {
        setClaim(PrincipalParamNames.BROWSER_NAME, browserName);
    }

    default String getClientSide() {
        return getClaim(PrincipalParamNames.CLIENT_SIDE);
    }

    default void setClientSide(String clientSide) {
        setClaim(PrincipalParamNames.CLIENT_SIDE, clientSide);
    }
}
