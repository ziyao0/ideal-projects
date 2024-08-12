package com.ziyao.ideal.gateway.security;

/**
 * 鉴权
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public interface GSecurityContext {

    String getIp();

    String getResourceUri();

    String getRequestUri();

    String getTimestamp();

    String getDigest();

    String getToken();

    String getRefreshToken();

    String getName();

    String getDomain();
}
