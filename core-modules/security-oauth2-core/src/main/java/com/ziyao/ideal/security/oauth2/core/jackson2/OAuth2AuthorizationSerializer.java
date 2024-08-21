package com.ziyao.ideal.security.oauth2.core.jackson2;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.security.oauth2.core.OAuth2AccessToken;
import com.ziyao.ideal.security.oauth2.core.OAuth2Authorization;
import com.ziyao.ideal.security.oauth2.core.OAuth2Token;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ziyao
 */
public class OAuth2AuthorizationSerializer extends JsonSerializer<OAuth2Authorization> {

    @Override
    public void serialize(OAuth2Authorization authorization, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();

        if (authorization.getId() != null)
            jsonGenerator.writeNumberField("id", authorization.getId());

        if (authorization.getAppId() != null)
            jsonGenerator.writeNumberField("appId", authorization.getAppId());

        if (authorization.getUserId() != null)
            jsonGenerator.writeNumberField("userId", authorization.getUserId());

        jsonGenerator.writeStringField("authorizationGrantType", authorization.getAuthorizationGrantType().value());

        jsonGenerator.writeArrayFieldStart("authorizedScopes");
        for (String authorizedScope : authorization.getAuthorizedScopes()) {
            jsonGenerator.writeString(authorizedScope);
        }
        jsonGenerator.writeEndArray();


        jsonGenerator.writeObjectField("tokens", writeMap(authorization.getTokens()));

        jsonGenerator.writeObjectField("attributes", authorization.getAttributes());

        jsonGenerator.writeEndObject();
    }

    private Map<String, Object> writeMap(Map<Class<? extends OAuth2Token>, OAuth2Authorization.Token<?>> tokens) {
        Map<String, Object> map = new HashMap<>();

        for (Map.Entry<Class<? extends OAuth2Token>, OAuth2Authorization.Token<?>> entry : tokens.entrySet()) {

            Class<? extends OAuth2Token> key = entry.getKey();

            OAuth2Authorization.Token<?> authorizationToken = entry.getValue();
            OAuth2Token token = authorizationToken.getToken();

            Map<String, Object> metadata = authorizationToken.getMetadata();

            Map<String, String> writeMap = new HashMap<String, String>() {
                private static final long serialVersionUID = -6169056380797051213L;

                {
                put("tokenValue", token.getTokenValue());
                put("issuedAt", token.getIssuedAt().toString());
                put("expiresAt", token.getExpiresAt().toString());
            }};

            if (OAuth2AccessToken.class.isAssignableFrom(key)) {
                OAuth2AccessToken accessToken = (OAuth2AccessToken) token;
                writeMap.put("tokenType", accessToken.getTokenType().getValue());
                writeMap.put("scopes", Strings.collectionToCommaDelimitedString(accessToken.getScopes()));
            }
            map.put(key.getSimpleName(), writeMap);
            map.put("metadata", metadata);
        }
        return map;
    }
}
