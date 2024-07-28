package com.ziyao.ideal.uua.service.oauth2;

import com.ziyao.ideal.core.Assert;
import com.ziyao.ideal.security.oauth2.core.OAuth2Authorization;
import com.ziyao.ideal.security.oauth2.core.OAuth2TokenType;
import com.ziyao.ideal.uua.repository.redis.AuthorizationRepositoryRedis;

import java.util.List;

/**
 * @author ziyao
 */
public class RedisOAuth2AuthorizationService extends AbstractOAuth2AuthorizationService {

    private final AuthorizationRepositoryRedis oauth2AuthorizationRepositoryRedis;

    public RedisOAuth2AuthorizationService(AuthorizationRepositoryRedis authorizationRepository) {
        this.oauth2AuthorizationRepositoryRedis = authorizationRepository;
    }

    @Override
    public void save(OAuth2Authorization authorization) {
        Assert.notNull(authorization, "authorization must not be null");
        oauth2AuthorizationRepositoryRedis.save(this.toEntity(authorization));
    }

    @Override
    public void remove(OAuth2Authorization authorization) {
        oauth2AuthorizationRepositoryRedis.delete(authorization.getId());
    }

    @Override
    public OAuth2Authorization findById(Long id) {
        return oauth2AuthorizationRepositoryRedis.findById(id).map(this::toObject).orElse(null);
    }

    @Override
    public OAuth2Authorization findByToken(String token, OAuth2TokenType tokenType) {
        Assert.notNull(token, "token must not be null");
        Assert.notNull(tokenType, "tokenType must not be null");

        List<OAuth2Authorization> oauth2Authorizations = oauth2AuthorizationRepositoryRedis.findAll().stream().map(this::toObject).toList();
        for (OAuth2Authorization authorization : oauth2Authorizations) {
            if (hasToken(authorization, token, tokenType)) {
                return authorization;
            }
        }
        return null;
    }

    @Override
    public Model model() {
        return Model.redis;
    }
}
