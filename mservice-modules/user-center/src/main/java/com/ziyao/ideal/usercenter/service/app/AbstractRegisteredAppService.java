package com.ziyao.ideal.usercenter.service.app;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ziyao.ideal.core.Dates;
import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.usercenter.entity.Application;
import com.ziyao.ideal.usercenter.service.oauth2.JpaOAuth2AuthorizationService;
import com.ziyao.security.oauth2.core.RegisteredApp;
import com.ziyao.security.oauth2.core.jackson2.Jackson2Modules;
import com.ziyao.security.oauth2.core.jackson2.OAuth2AuthorizationServerJackson2Module;
import com.ziyao.security.oauth2.core.settings.TokenSettings;
import com.ziyao.security.oauth2.core.support.AuthorizationGrantTypes;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public abstract class AbstractRegisteredAppService implements RegisteredAppService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public AbstractRegisteredAppService() {
        ClassLoader classLoader = JpaOAuth2AuthorizationService.class.getClassLoader();
        List<Module> securityModules = Jackson2Modules.getModules(classLoader);
        this.objectMapper.registerModules(securityModules);
        this.objectMapper.registerModule(new OAuth2AuthorizationServerJackson2Module());
    }


    protected RegisteredApp toObject(Application application) {

        Set<String> authorizationGrantTypes = Strings.commaDelimitedListToSet(
                application.getAuthorizationGrantTypes());

        Set<String> appScopes = Strings.commaDelimitedListToSet(
                application.getScopes()
        );

        Map<String, Object> tokenSettingsMap = parseMap(application.getTokenSettings());

        RegisteredApp.Builder builder = RegisteredApp.withAppId(application.getAppId())
                .appName(application.getAppName())
                .appType(application.getAppType())
                .issuedAt(Dates.toInstant(application.getIssuedAt()))
                .appSecret(application.getAppSecret())
                .appSecretExpiresAt(Dates.toInstant(application.getIssuedAt()))
                .state(application.getState())
                .redirectUri(application.getRedirectUri())
                .postLogoutRedirectUris(application.getPostLogoutRedirectUri())
                .authorizationGrantTypes(grantTypes ->
                        authorizationGrantTypes.forEach(authorizationGrantType ->
                                grantTypes.add(AuthorizationGrantTypes.resolve(authorizationGrantType))))
                .scopes((scopes) -> scopes.addAll(appScopes));

        TokenSettings tokenSettings = TokenSettings.withSettings(tokenSettingsMap).reuseRefreshTokens(true).build();

        return builder.tokenSettings(tokenSettings).build();
    }

    protected Application toEntity(RegisteredApp registeredApp) {
        Application application = new Application();
        application.setAppId(registeredApp.getAppId());
        application.setAppName(registeredApp.getAppName());
        application.setState(registeredApp.getState());
        application.setAuthorizationGrantTypes(Strings.collectionToCommaDelimitedString(registeredApp.getAuthorizationGrantTypes()));
        application.setScopes(Strings.collectionToCommaDelimitedString(registeredApp.getScopes()));
        application.setAppType(registeredApp.getAppType());
        application.setAppSecret(registeredApp.getAppSecret());
        application.setIssuedAt(Dates.toLocalDateTime(registeredApp.getIssuedAt()));
        application.setAppSecretExpiresAt(Dates.toLocalDateTime(registeredApp.getAppSecretExpiresAt()));
        application.setRedirectUri(registeredApp.getRedirectUri());
        application.setPostLogoutRedirectUri(registeredApp.getPostLogoutRedirectUri());
        application.setTokenSettings(writeMap(registeredApp.getTokenSettings().getSettings()));
        return application;
    }


    private Map<String, Object> parseMap(String data) {
        try {
            return this.objectMapper.readValue(data, new TypeReference<>() {
            });
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage(), ex);
        }
    }

    private String writeMap(Map<String, Object> data) {
        try {
            return this.objectMapper.writeValueAsString(data);
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage(), ex);
        }
    }
}
