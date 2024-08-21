package com.ziyao.ideal.security.oauth2.core;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.Sets;
import com.ziyao.ideal.core.Assert;
import com.ziyao.ideal.core.Collections;
import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.security.oauth2.core.jackson2.RegisteredAppDeserializer;
import com.ziyao.ideal.security.oauth2.core.jackson2.RegisteredAppSerializer;
import com.ziyao.ideal.security.oauth2.core.settings.TokenSettings;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;
import org.springframework.data.redis.core.TimeToLive;


import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @author ziyao zhang
 */
@Getter
@JsonSerialize(using = RegisteredAppSerializer.class)
@JsonDeserialize(using = RegisteredAppDeserializer.class)
@KeySpace("oauth2:registeredapp")
public class RegisteredApp implements Serializable {

    
    private static final long serialVersionUID = -5766646592402572432L;
    @Id
    private Integer appId;
    private String appName;
    private Integer appType;
    private Set<AuthorizationGrantType> authorizationGrantTypes;
    private Integer state;
    private Instant issuedAt;
    private String appSecret;
    private Instant appSecretExpiresAt;
    private Set<String> scopes;
    private String redirectUri;
    private String postLogoutRedirectUri;
    private TokenSettings tokenSettings;
    @Setter
    @TimeToLive(unit = TimeUnit.DAYS)
    private long ttl = 7;

    protected RegisteredApp() {
    }

    public static Builder withAppId(Integer appid) {
        Assert.notNull(appid, "id cannot be empty");
        return new Builder(appid);
    }

    public static class Builder implements Serializable {

        
        private static final long serialVersionUID = 6495205880660410126L;
        private Integer appId;
        private Integer appType;
        private Set<AuthorizationGrantType> authorizationGrantTypes = new HashSet<>();
        private Integer state;
        private Instant issuedAt;
        private String appSecret;
        private Instant appSecretExpiresAt;
        private String appName;
        private Set<String> scopes = new HashSet<>();
        private String redirectUri;
        private String postLogoutRedirectUri;
        private TokenSettings tokenSettings;

        protected Builder(Integer appId) {
            this.appId = appId;
        }

        protected Builder(RegisteredApp registeredApp) {

            this.appId = registeredApp.getAppId();
            this.appType = registeredApp.getAppType();
            if (!Collections.isEmpty(registeredApp.getAuthorizationGrantTypes())) {
                this.authorizationGrantTypes = registeredApp.getAuthorizationGrantTypes();
            }
            this.state = registeredApp.getState();
            this.issuedAt = registeredApp.getIssuedAt();
            this.appSecret = registeredApp.getAppSecret();
            this.appSecretExpiresAt = registeredApp.getAppSecretExpiresAt();
            this.appName = registeredApp.getAppName();
            if (!Collections.isEmpty(registeredApp.getScopes())) {
                this.scopes = registeredApp.getScopes();
            }
            this.redirectUri = registeredApp.getRedirectUri();
            this.postLogoutRedirectUri = registeredApp.getPostLogoutRedirectUri();

            this.tokenSettings = TokenSettings.withSettings(registeredApp.getTokenSettings().getSettings()).build();
        }

        public Builder appId(Integer appId) {
            this.appId = appId;
            return this;
        }

        public Builder appType(Integer appType) {
            this.appType = appType;
            return this;
        }

        public Builder authorizationGrantTypes(Set<AuthorizationGrantType> authorizationGrantTypes) {
            this.authorizationGrantTypes = authorizationGrantTypes;
            return this;
        }

        public Builder authorizationGrantTypes(Consumer<Set<AuthorizationGrantType>> authorizationGrantTypesConsumer) {
            authorizationGrantTypesConsumer.accept(this.authorizationGrantTypes);
            return this;
        }

        public Builder state(Integer state) {
            this.state = state;
            return this;
        }

        public Builder issuedAt(Instant issuedAt) {
            this.issuedAt = issuedAt;
            return this;
        }

        public Builder appSecret(String appSecret) {
            this.appSecret = appSecret;
            return this;
        }

        public Builder appSecretExpiresAt(Instant appSecretExpiresAt) {
            this.appSecretExpiresAt = appSecretExpiresAt;
            return this;
        }

        public Builder appName(String appName) {
            this.appName = appName;
            return this;
        }

        public Builder scopes(Set<String> scopes) {
            this.scopes = scopes;
            return this;
        }

        public Builder scopes(Consumer<Set<String>> scopesConsumer) {
            scopesConsumer.accept(this.scopes);
            return this;
        }


        public Builder redirectUri(String redirectUri) {
            this.redirectUri = redirectUri;
            return this;
        }

        public Builder postLogoutRedirectUris(String postLogoutRedirectUri) {
            this.postLogoutRedirectUri = postLogoutRedirectUri;
            return this;
        }

        public Builder tokenSettings(TokenSettings tokenSettings) {
            this.tokenSettings = tokenSettings;
            return this;
        }

        public RegisteredApp build() {
            Assert.notNull(this.appId, "clientId cannot be empty");
            Assert.notNull(this.authorizationGrantTypes, "authorizationGrantTypes cannot be empty");
            if (this.authorizationGrantTypes.contains(AuthorizationGrantType.AUTHORIZATION_CODE)) {
                Assert.notNull(this.redirectUri, "redirectUris cannot be empty");
            }
            if (this.tokenSettings == null) {
                this.tokenSettings = TokenSettings.builder().build();
            }
            return create();
        }

        private @NonNull RegisteredApp create() {
            RegisteredApp registeredApp = new RegisteredApp();
            registeredApp.appId = this.appId;
            registeredApp.appType = this.appType;
            registeredApp.authorizationGrantTypes = Sets.newHashSet(this.authorizationGrantTypes);
            registeredApp.state = this.state;
            registeredApp.issuedAt = this.issuedAt;
            registeredApp.appSecret = this.appSecret;
            registeredApp.appSecretExpiresAt = this.appSecretExpiresAt;
            registeredApp.appName = this.appName;
            registeredApp.scopes = Sets.newHashSet(this.scopes);
            registeredApp.redirectUri = this.redirectUri;
            registeredApp.postLogoutRedirectUri = this.postLogoutRedirectUri;
            registeredApp.tokenSettings = this.tokenSettings;
            return registeredApp;
        }
    }
}
