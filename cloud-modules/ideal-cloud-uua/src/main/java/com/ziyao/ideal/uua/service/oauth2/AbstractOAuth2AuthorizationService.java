package com.ziyao.ideal.uua.service.oauth2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ziyao.ideal.core.Dates;
import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.security.oauth2.core.*;
import com.ziyao.ideal.security.oauth2.core.jackson2.Jackson2Modules;
import com.ziyao.ideal.security.oauth2.core.jackson2.OAuth2AuthorizationServerJackson2Module;
import com.ziyao.ideal.security.oauth2.core.support.AuthorizationGrantTypes;
import com.ziyao.ideal.security.oauth2.core.token.OAuth2ParameterNames;
import com.ziyao.ideal.uua.domain.entity.Authorization;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author ziyao
 */
public abstract class AbstractOAuth2AuthorizationService implements OAuth2AuthorizationService {


    private final ObjectMapper objectMapper = new ObjectMapper();

    public AbstractOAuth2AuthorizationService() {
        ClassLoader classLoader = JpaOAuth2AuthorizationService.class.getClassLoader();
        List<Module> securityModules = Jackson2Modules.getModules(classLoader);
        this.objectMapper.registerModules(securityModules);
        this.objectMapper.registerModule(new OAuth2AuthorizationServerJackson2Module());
    }

    protected static boolean isComplete(OAuth2Authorization authorization) {
        return authorization.getAccessToken() != null;
    }

    protected boolean hasToken(OAuth2Authorization authorization, String token,
                               @Nullable OAuth2TokenType tokenType) {
        // @formatter:off
        if (tokenType == null) {
            return matchesState(authorization, token) ||
                    matchesAuthorizationCode(authorization, token) ||
                    matchesAccessToken(authorization, token) ||
                    matchesRefreshToken(authorization, token);
        }
        else if (OAuth2ParameterNames.STATE.equals(tokenType.value())) {
            return matchesState(authorization, token);
        }
        else if (OAuth2ParameterNames.CODE.equals(tokenType.value())) {
            return matchesAuthorizationCode(authorization, token);
        }
        else if (OAuth2TokenType.ACCESS_TOKEN.equals(tokenType)) {
            return matchesAccessToken(authorization, token);
        }
        else if (OAuth2TokenType.REFRESH_TOKEN.equals(tokenType)) {
            return matchesRefreshToken(authorization, token);
        }
        // @formatter:on
        return false;
    }

    protected boolean matchesState(OAuth2Authorization authorization, String token) {
        return token.equals(authorization.getAttribute(OAuth2ParameterNames.STATE));
    }

    protected boolean matchesAuthorizationCode(OAuth2Authorization authorization, String token) {
        OAuth2Authorization.Token<OAuth2AuthorizationCode> authorizationCode = authorization
                .getToken(OAuth2AuthorizationCode.class);
        return authorizationCode != null && authorizationCode.getToken().getTokenValue().equals(token);
    }

    protected boolean matchesAccessToken(OAuth2Authorization authorization, String token) {
        OAuth2Authorization.Token<OAuth2AccessToken> accessToken = authorization.getToken(OAuth2AccessToken.class);
        return accessToken != null && accessToken.getToken().getTokenValue().equals(token);
    }

    protected boolean matchesRefreshToken(OAuth2Authorization authorization, String token) {
        OAuth2Authorization.Token<OAuth2RefreshToken> refreshToken = authorization.getToken(OAuth2RefreshToken.class);
        return refreshToken != null && refreshToken.getToken().getTokenValue().equals(token);
    }


    protected OAuth2Authorization toObject(Authorization entity) {

        OAuth2Authorization.Builder builder = OAuth2Authorization.withAppId(entity.getAppid())
                .id(entity.getId())
                .userId(entity.getUserId())
                .authorizationGrantType(AuthorizationGrantTypes.resolve(entity.getAuthorizationGrantType()))
                .authorizedScopes(Strings.commaDelimitedListToSet(entity.getAuthorizedScopes()))
                .attributes(attributes -> attributes.putAll(parseMap(entity.getAttributes())));

        if (entity.getState() != null) {
            builder.attribute(OAuth2ParameterNames.STATE, entity.getState());
        }

        if (entity.getAuthorizationCodeValue() != null) {
            OAuth2AuthorizationCode authorizationCode = new OAuth2AuthorizationCode(
                    entity.getAuthorizationCodeValue(),
                    Dates.toInstant(entity.getAuthorizationCodeIssuedAt()),
                    Dates.toInstant(entity.getAuthorizationCodeExpiresAt()));
            builder.token(authorizationCode, metadata -> metadata.putAll(parseMap(entity.getAuthorizationCodeMetadata())));
        }
        if (entity.getAccessTokenValue() != null) {
            OAuth2AccessToken accessToken = new OAuth2AccessToken(
                    OAuth2AccessToken.TokenType.Bearer,
                    entity.getAccessTokenValue(),
                    Dates.toInstant(entity.getAccessTokenIssuedAt()),
                    Dates.toInstant(entity.getAccessTokenExpiresAt()),
                    Strings.commaDelimitedListToSet(entity.getAccessTokenScopes()));
            builder.token(accessToken, metadata -> metadata.putAll(parseMap(entity.getAccessTokenMetadata())));
        }

        if (entity.getRefreshTokenValue() != null) {
            OAuth2RefreshToken refreshToken = new OAuth2RefreshToken(
                    entity.getRefreshTokenValue(),
                    Dates.toInstant(entity.getRefreshTokenIssuedAt()),
                    Dates.toInstant(entity.getRefreshTokenExpiresAt()));
            builder.token(refreshToken, metadata -> metadata.putAll(parseMap(entity.getRefreshTokenMetadata())));
        }

        if (entity.getOidcIdTokenValue() != null) {
            OidcIdToken oidcIdToken = new OidcIdToken(
                    entity.getOidcIdTokenValue(),
                    Dates.toInstant(entity.getOidcIdTokenIssuedAt()),
                    Dates.toInstant(entity.getOidcIdTokenExpiresAt()),
                    parseMap(entity.getOidcIdTokenClaims())
            );
            builder.token(oidcIdToken, metadata -> metadata.putAll(parseMap(entity.getOidcIdTokenMetadata())));
        }

        return builder.build();
    }


    protected Authorization toEntity(OAuth2Authorization authorization) {
        Authorization entity = new Authorization();
        entity.setId(authorization.getId());
        entity.setAppid(authorization.getAppId());
        entity.setUserId(authorization.getUserId());
        entity.setAuthorizationGrantType(authorization.getAuthorizationGrantType().value());
        entity.setAuthorizedScopes(Strings.collectionToDelimitedString(authorization.getAuthorizedScopes(), ","));
        entity.setAttributes(writeMap(authorization.getAttributes()));
        entity.setState(authorization.getAttribute(OAuth2ParameterNames.STATE));
        //设置状态
        entity.setState(1);

        OAuth2Authorization.Token<OAuth2AuthorizationCode> authorizationCode =
                authorization.getToken(OAuth2AuthorizationCode.class);
        setTokenValues(
                authorizationCode,
                entity::setAuthorizationCodeValue,
                entity::setAuthorizationCodeIssuedAt,
                entity::setAuthorizationCodeExpiresAt,
                entity::setAuthorizationCodeMetadata
        );

        OAuth2Authorization.Token<OAuth2AccessToken> accessToken =
                authorization.getToken(OAuth2AccessToken.class);
        setTokenValues(
                accessToken,
                entity::setAccessTokenValue,
                entity::setAccessTokenIssuedAt,
                entity::setAccessTokenExpiresAt,
                entity::setAccessTokenMetadata
        );
        if (accessToken != null && accessToken.getToken().getScopes() != null) {
            entity.setAccessTokenScopes(Strings.collectionToDelimitedString(accessToken.getToken().getScopes(), ","));
        }

        OAuth2Authorization.Token<OAuth2RefreshToken> refreshToken =
                authorization.getToken(OAuth2RefreshToken.class);
        setTokenValues(
                refreshToken,
                entity::setRefreshTokenValue,
                entity::setRefreshTokenIssuedAt,
                entity::setRefreshTokenExpiresAt,
                entity::setRefreshTokenMetadata
        );

        OAuth2Authorization.Token<OidcIdToken> oidcToken =
                authorization.getToken(OidcIdToken.class);
        setTokenValues(
                oidcToken,
                entity::setOidcIdTokenValue,
                entity::setOidcIdTokenIssuedAt,
                entity::setOidcIdTokenExpiresAt,
                entity::setOidcIdTokenMetadata
        );
        if (oidcToken != null) {
            entity.setOidcIdTokenClaims(writeMap(oidcToken.getClaims()));
        }
        return entity;
    }


    protected void setTokenValues(
            OAuth2Authorization.Token<?> token,
            Consumer<String> tokenValueConsumer,
            Consumer<LocalDateTime> issuedAtConsumer,
            Consumer<LocalDateTime> expiresAtConsumer,
            Consumer<String> metadataConsumer) {
        if (token != null) {
            OAuth2Token oauth2Token = token.getToken();
            tokenValueConsumer.accept(oauth2Token.getTokenValue());
            issuedAtConsumer.accept(Dates.toLocalDateTime(oauth2Token.getIssuedAt()));
            expiresAtConsumer.accept(Dates.toLocalDateTime(oauth2Token.getExpiresAt()));
            metadataConsumer.accept(writeMap(token.getMetadata()));
        }
    }


    protected Map<String, Object> parseMap(String data) {
        try {
            return this.objectMapper.readValue(data, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage(), ex);
        }
    }

    protected String writeMap(Map<String, Object> metadata) {
        try {
            return this.objectMapper.writeValueAsString(new HashMap<>(metadata));
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage(), ex);
        }
    }
}
