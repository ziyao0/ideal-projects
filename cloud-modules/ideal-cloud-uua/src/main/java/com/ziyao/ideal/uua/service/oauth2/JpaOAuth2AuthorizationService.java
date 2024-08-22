package com.ziyao.ideal.uua.service.oauth2;

import com.ziyao.ideal.core.Assert;
import com.ziyao.ideal.security.oauth2.core.OAuth2Authorization;
import com.ziyao.ideal.security.oauth2.core.OAuth2TokenType;
import com.ziyao.ideal.security.oauth2.core.token.OAuth2ParameterNames;
import com.ziyao.ideal.uua.domain.entity.Authorization;
import com.ziyao.ideal.uua.repository.jpa.AuthorizationRepositoryJpa;

import java.util.Optional;

/**
 * 默认实现---数据库操作
 *
 * @author ziyao
 */
public class JpaOAuth2AuthorizationService extends AbstractOAuth2AuthorizationService {


    private final AuthorizationRepositoryJpa authorizationRepository;

    public JpaOAuth2AuthorizationService(AuthorizationRepositoryJpa authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
    }


    @Override
    public void save(OAuth2Authorization authorization) {
        Assert.notNull(authorization, "authorization must not be null");
        this.authorizationRepository.save(toEntity(authorization));
    }

    @Override
    public void remove(OAuth2Authorization authorization) {
        Assert.notNull(authorization, "authorization must not be null");
        this.authorizationRepository.deleteById(authorization.getId());
    }

    @Override
    public OAuth2Authorization findById(Integer id) {
        Assert.notNull(id, "id must not be null");
        return this.authorizationRepository.findById(id).map(this::toObject).orElse(null);
    }

    @Override
    public OAuth2Authorization findByToken(String token, OAuth2TokenType tokenType) {
        Assert.notNull(token, "token must not be null");
        Assert.notNull(tokenType, "tokenType must not be null");


        Optional<Authorization> result;
        if (OAuth2ParameterNames.CODE.equals(tokenType.value())) {
            result = authorizationRepository.findByAuthorizationCodeValue(token);
        } else if (OAuth2ParameterNames.REFRESH_TOKEN.equals(tokenType.value())) {
            result = authorizationRepository.findByRefreshTokenValue(token);
        } else if (OAuth2ParameterNames.ACCESS_TOKEN.equals(tokenType.value())) {
            result = authorizationRepository.findByAccessTokenValue(token);
        } else if (OAuth2ParameterNames.STATE.equals(tokenType.value())) {
            result = authorizationRepository.findByState(Integer.parseInt(token));
        } else {
            result = Optional.empty();
        }

        return result.map(this::toObject).orElse(null);
    }

    @Override
    public Model model() {
        return Model.jpa;
    }


}
