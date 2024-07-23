package com.ziyao.security.oauth2.core;

/**
 * @author ziyao
 */
public interface CredentialsContainer {

    /**
     * 擦除凭证
     * <p>
     * 例如密码等
     */
    void eraseCredentials();
}
