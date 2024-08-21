package com.ziyao.ideal.security.oauth2.core.token;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author ziyao zhang
 */
public class DefaultOAuth2TokenContext implements OAuth2TokenContext {

    private final Map<String, Object> context;

    private DefaultOAuth2TokenContext(Map<String, Object> context) {
        this.context =  Maps.newHashMap(context);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <V> V get(String key) {
        return hasKey(key) ? (V) this.context.get(key) : null;
    }

    @Override
    public boolean hasKey(String key) {
        return this.context.containsKey(key);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractBuilder<DefaultOAuth2TokenContext, Builder> {

        private Builder() {
        }

        /**
         * Builds a new {@link DefaultOAuth2TokenContext}.
         *
         * @return the {@link DefaultOAuth2TokenContext}
         */
        public DefaultOAuth2TokenContext build() {
            return new DefaultOAuth2TokenContext(getContext());
        }

    }
}
