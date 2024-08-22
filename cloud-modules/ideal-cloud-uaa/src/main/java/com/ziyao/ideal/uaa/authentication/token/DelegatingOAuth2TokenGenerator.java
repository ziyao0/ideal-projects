package com.ziyao.ideal.uaa.authentication.token;

import com.google.common.collect.Lists;
import com.ziyao.ideal.core.Assert;
import com.ziyao.ideal.security.oauth2.core.OAuth2Token;
import com.ziyao.ideal.security.oauth2.core.token.OAuth2TokenContext;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ziyao zhang
 */
@Getter
public final class DelegatingOAuth2TokenGenerator implements OAuth2TokenGenerator<OAuth2Token> {

    private final List<OAuth2TokenGenerator<OAuth2Token>> tokenGenerators;

    @SafeVarargs
    public DelegatingOAuth2TokenGenerator(OAuth2TokenGenerator<? extends OAuth2Token>... tokenGenerators) {
        Assert.notNull(tokenGenerators, "tokenGenerators cannot be empty");
        Assert.noNullElements(tokenGenerators, "tokenGenerator cannot be null");
        this.tokenGenerators = Lists.newArrayList(asList(tokenGenerators));
    }

    @Override
    public OAuth2Token generate(OAuth2TokenContext context) {
        for (OAuth2TokenGenerator<OAuth2Token> tokenGenerator : this.tokenGenerators) {
            OAuth2Token token = tokenGenerator.generate(context);
            if (token != null) {
                return token;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static List<OAuth2TokenGenerator<OAuth2Token>> asList(
            OAuth2TokenGenerator<? extends OAuth2Token>... tokenGenerators) {

        List<OAuth2TokenGenerator<OAuth2Token>> tokenGeneratorList = new ArrayList<>();
        for (OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator : tokenGenerators) {
            tokenGeneratorList.add((OAuth2TokenGenerator<OAuth2Token>) tokenGenerator);
        }
        return tokenGeneratorList;
    }
}
