package com.ziyao.ideal.uaa.service.oauth2;

import com.ziyao.ideal.security.oauth2.core.OAuth2Authorization;
import com.ziyao.ideal.security.oauth2.core.OAuth2TokenType;
import org.springframework.lang.Nullable;

/**
 * @author ziyao
 */
public interface OAuth2AuthorizationService {
    /**
     * 存储 {@link OAuth2Authorization}.
     *
     * @param authorization the {@link OAuth2Authorization}
     */
    void save(OAuth2Authorization authorization);

    /**
     * 删除 {@link OAuth2Authorization}.
     *
     * @param authorization the {@link OAuth2Authorization}
     */
    void remove(OAuth2Authorization authorization);

    /**
     * 通过ID查询 {@link OAuth2Authorization}.
     */
    @Nullable
    OAuth2Authorization findById(Integer id);

    /**
     * 返回包含提供的 {@code token} 的 {@link OAuth2Authorization}，如果未找到，则返回 {@code null}。
     *
     * @param token     令牌
     * @param tokenType the {@link OAuth2TokenType} token 类型
     * @return 返回 {@link OAuth2Authorization}，如果未找到返回 {@code null}
     */
    @Nullable
    OAuth2Authorization findByToken(String token, @Nullable OAuth2TokenType tokenType);

    default Model model() {
        return null;
    }

    enum Model {
        caffeine, redis, jpa
    }
}
