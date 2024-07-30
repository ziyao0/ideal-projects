package com.ziyao.ideal.security.oauth2.core.client;

import com.ziyao.ideal.security.oauth2.core.AuthorizationGrantType;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public class ClientRegistration {

    private String registrationId;
    private String appId;
    private String appName;
    private String clientSecret;
    private AuthorizationGrantType authorizationGrantType;
    private String redirectUri;
    private String tokenUri;
    private String authorizationUri;
    private Set<String> scopes = Collections.emptySet();


    private ClientRegistration() {
    }


    public static Builder withRegistrationId(String registrationId) {
        return new Builder(registrationId);
    }

    public static class Builder {

        private static final Logger logger = LoggerFactory.getLogger(Builder.class);

        private static final List<AuthorizationGrantType> AUTHORIZATION_GRANT_TYPES = Arrays.asList(
                AuthorizationGrantType.AUTHORIZATION_CODE, AuthorizationGrantType.CLIENT_CREDENTIALS,
                AuthorizationGrantType.REFRESH_TOKEN, AuthorizationGrantType.PASSWORD);

        private final String registrationId;
        private String appId;
        private String appName;
        private String clientSecret;
        private AuthorizationGrantType authorizationGrantType;
        private String redirectUri;
        private String tokenUri;
        private String authorizationUri;
        private Set<String> scopes = Collections.emptySet();

        private Builder(String registrationId) {
            this.registrationId = registrationId;
        }

        public Builder appId(String appId) {
            this.appId = appId;
            return this;
        }

        public Builder appName(String appName) {
            this.appName = appName;
            return this;
        }

        public Builder clientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
            return this;
        }

        public Builder authorizationGrantType(AuthorizationGrantType authorizationGrantType) {
            this.authorizationGrantType = authorizationGrantType;
            return this;
        }

        public Builder redirectUri(String redirectUri) {
            this.redirectUri = redirectUri;
            return this;
        }

        public Builder tokenUri(String tokenUri) {
            this.tokenUri = tokenUri;
            return this;
        }

        public Builder authorizationUri(String authorizationUri) {
            this.authorizationUri = authorizationUri;
            return this;
        }

        public Builder scope(String... scope) {
            if (scope != null && scope.length > 0) {
                this.scopes = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(scope)));
            }
            return this;
        }

        public Builder scope(Collection<String> scope) {
            if (scope != null && !scope.isEmpty()) {
                this.scopes = Collections.unmodifiableSet(new LinkedHashSet<>(scope));
            }
            return this;
        }

        public ClientRegistration build() {

            Assert.notNull(this.authorizationGrantType, "authorizationGrantType cannot be null");
            if (AuthorizationGrantType.CLIENT_CREDENTIALS.equals(this.authorizationGrantType)) {
                this.validateClientCredentialsGrantType();
            } else if (AuthorizationGrantType.PASSWORD.equals(this.authorizationGrantType)) {
                this.validatePasswordGrantType();
            } else if (AuthorizationGrantType.AUTHORIZATION_CODE.equals(this.authorizationGrantType)) {
                this.validateAuthorizationCodeGrantType();
            }

            this.validateAuthorizationGrantTypes();
            this.validateScopes();
            return this.creation();
        }

        private ClientRegistration creation() {
            ClientRegistration registration = new ClientRegistration();
            registration.registrationId = registrationId;
            registration.appId = appId;
            registration.appName = StringUtils.hasText(this.appId) ? this.appName
                    : this.registrationId;
            registration.clientSecret = StringUtils.hasText(this.clientSecret) ? this.clientSecret : "";
            registration.authorizationGrantType = authorizationGrantType;
            registration.redirectUri = redirectUri;
            registration.tokenUri = tokenUri;
            registration.authorizationUri = authorizationUri;
            registration.scopes.addAll(scopes);
            return registration;
        }


        private void validateAuthorizationCodeGrantType() {
            Assert.isTrue(AuthorizationGrantType.AUTHORIZATION_CODE.equals(this.authorizationGrantType),
                    () -> "authorizationGrantType must be " + AuthorizationGrantType.AUTHORIZATION_CODE.value());
            Assert.hasText(this.registrationId, "registrationId cannot be empty");
            Assert.hasText(this.appId, "appId cannot be empty");
            Assert.hasText(this.redirectUri, "redirectUri cannot be empty");
            Assert.hasText(this.authorizationUri, "authorizationUri cannot be empty");
            Assert.hasText(this.tokenUri, "tokenUri cannot be empty");
        }

        private void validateClientCredentialsGrantType() {
            Assert.isTrue(AuthorizationGrantType.CLIENT_CREDENTIALS.equals(this.authorizationGrantType),
                    () -> "authorizationGrantType must be " + AuthorizationGrantType.CLIENT_CREDENTIALS.value());
            Assert.hasText(this.registrationId, "registrationId cannot be empty");
            Assert.hasText(this.appId, "appId cannot be empty");
            Assert.hasText(this.tokenUri, "tokenUri cannot be empty");
        }


        private void validatePasswordGrantType() {
            Assert.isTrue(AuthorizationGrantType.PASSWORD.equals(this.authorizationGrantType),
                    () -> "authorizationGrantType must be " + AuthorizationGrantType.PASSWORD.value());
            Assert.hasText(this.registrationId, "registrationId cannot be empty");
            Assert.hasText(this.appId, "appId cannot be empty");
            Assert.hasText(this.tokenUri, "tokenUri cannot be empty");
        }


        private void validateAuthorizationGrantTypes() {
            for (AuthorizationGrantType authorizationGrantType : AUTHORIZATION_GRANT_TYPES) {
                if (authorizationGrantType.value().equalsIgnoreCase(this.authorizationGrantType.value())
                        && !authorizationGrantType.equals(this.authorizationGrantType)) {
                    logger.warn(
                            "AuthorizationGrantType: {} does not match the pre-defined constant {} and won't match a valid OAuth2AuthorizedClientProvider",
                            this.authorizationGrantType, authorizationGrantType);
                }
            }
        }

        private void validateScopes() {
            if (this.scopes == null) {
                return;
            }
            for (String scope : this.scopes) {
                Assert.isTrue(validateScope(scope), "scope \"" + scope + "\" contains invalid characters");
            }
        }

        private static boolean validateScope(String scope) {
            return scope == null || scope.chars()
                    .allMatch((c) -> withinTheRangeOf(c, 0x21, 0x21) || withinTheRangeOf(c, 0x23, 0x5B)
                            || withinTheRangeOf(c, 0x5D, 0x7E));
        }

        private static boolean withinTheRangeOf(int c, int min, int max) {
            return c >= min && c <= max;
        }

    }
}
