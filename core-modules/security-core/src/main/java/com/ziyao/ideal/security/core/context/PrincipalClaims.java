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
    default void setClaim(String key, Object value) {
        getClaims().put(key, value);
    }

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

    default String getLocation() {
        return getClaim(PrincipalParamNames.LOCATION);
    }

    default void setLocation(String location) {
        setClaim(PrincipalParamNames.LOCATION, location);
    }

    default String getIp() {
        return getClaim(PrincipalParamNames.IP);
    }

    default void setIp(String accessIp) {
        setClaim(PrincipalParamNames.IP, accessIp);
    }

    default String getBrowser() {
        return getClaim(PrincipalParamNames.BROWSER_NAME);
    }

    default void setBrowser(String browserName) {
        setClaim(PrincipalParamNames.BROWSER_NAME, browserName);
    }

    default String getClientSide() {
        return getClaim(PrincipalParamNames.CLIENT_SIDE);
    }

    default void setClientSide(String clientSide) {
        setClaim(PrincipalParamNames.CLIENT_SIDE, clientSide);
    }
}
