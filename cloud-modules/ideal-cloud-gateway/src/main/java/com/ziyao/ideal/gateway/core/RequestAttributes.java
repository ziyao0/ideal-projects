package com.ziyao.ideal.gateway.core;

import com.ziyao.ideal.gateway.authorization.AuthorizationToken;
import com.ziyao.ideal.gateway.support.ParameterNames;
import com.ziyao.ideal.gateway.support.SecurityUtils;
import org.springframework.web.server.ServerWebExchange;

import java.util.Set;

/**
 * @author ziyao
 */
public abstract class RequestAttributes {

    private RequestAttributes() {
    }

    public static final String OAUTH_REQUEST_MARKING = ParameterNames.OAUTH_REQUEST_MARKING;
    public static final String AUTHORIZATION = ParameterNames.AUTHORIZATION;
    public static final String TIMESTAMP = ParameterNames.TIMESTAMP;
    public static final String REFRESH_TOKEN = ParameterNames.REFRESH_TOKEN;
    public static final String RESOURCE = ParameterNames.RESOURCE;
    public static final String DIGEST = ParameterNames.DIGEST;
    public static final String AUTHORIZER_CONTEXT = "authorization_token";
    public static final String ATTRIBUTE_PREFIX = "gateway_attribute_prefix_";

    /**
     * 认证相关请求头key
     */
    public static final Set<String> AUTHORIZATION_HEADERS =
            Set.of(AUTHORIZATION, TIMESTAMP, REFRESH_TOKEN, DIGEST);

    /**
     * 判断是否已经通过认证
     *
     * @param exchange HTTP 请求-响应交互的协定。提供对 HTTP 请求和响应的访问，并公开其他与服务器端处理相关的属性和功能，例如请求属性。
     * @return <code>true</code> 已经通过认证
     */
    public static boolean isAuthenticated(ServerWebExchange exchange) {
        AuthorizationToken authorizationToken = loadAuthorizationToken(exchange);
        return SecurityUtils.authorized(authorizationToken);
    }

    /**
     * 获取认证上下文
     *
     * @param exchange exchange
     * @return {@link com.ziyao.ideal.gateway.authorization.AuthorizationToken} 认证上下文
     */
    public static AuthorizationToken loadAuthorizationToken(ServerWebExchange exchange) {
        return exchange.getAttribute(AUTHORIZER_CONTEXT);
    }

    /**
     * 存储认证上下文信息
     *
     * @param exchange           exchange
     * @param authorizationToken {@link AuthorizationToken} 认证上下文
     */
    public static void storeAuthorizationToken(ServerWebExchange exchange, AuthorizationToken authorizationToken) {
        exchange.getAttributes().put(AUTHORIZER_CONTEXT, authorizationToken);
    }

    /**
     * 存储属性
     *
     * @param exchange  the current server exchange
     * @param attribute 需要存储的属性
     * @param <T>       attribute type
     */
    public static <T> void storeAttribute(ServerWebExchange exchange, T attribute) {
        exchange.getAttributes().put(ATTRIBUTE_PREFIX + attribute.getClass().getSimpleName(), attribute);
    }

    /**
     * 从exchange获取attribute
     *
     * @param exchange       the current server exchange
     * @param attributeClass attribute class
     * @param <T>            attribute type
     * @return attribute
     */
    public static <T> T getAttribute(ServerWebExchange exchange, Class<T> attributeClass) {
        return exchange.getAttribute(ATTRIBUTE_PREFIX + attributeClass.getSimpleName());
    }

    /**
     * 从exchange获取attribute
     *
     * @param exchange       the current server exchange
     * @param attributeClass attribute class
     * @param <T>            attribute type
     * @param defaultValue   如果获取到的为空，默认返回数据
     * @return attribute
     */
    public static <T> T getAttributeOrDefault(ServerWebExchange exchange, Class<T> attributeClass, T defaultValue) {
        return exchange.getAttributeOrDefault(ATTRIBUTE_PREFIX + attributeClass.getSimpleName(), defaultValue);
    }
}
