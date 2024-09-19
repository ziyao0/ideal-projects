package com.ziyao.ideal.gateway.filter;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.crypto.digest.DigestUtils;
import com.ziyao.ideal.gateway.authorization.AuthorizationToken;
import com.ziyao.ideal.gateway.config.ConfigCenter;
import com.ziyao.ideal.gateway.config.GatewayConfig;
import com.ziyao.ideal.gateway.core.RequestAttributes;
import com.ziyao.ideal.gateway.core.error.GatewayErrors;
import com.ziyao.ideal.gateway.support.ConstantPool;
import com.ziyao.ideal.gateway.support.RedisKeys;
import com.ziyao.ideal.security.core.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Optional;

/**
 * 防抖
 * <p>
 * 限制请求频率
 *
 * @author ziyao
 */
@Component
@RequiredArgsConstructor
public class RateLimitingFilter extends AbstractAfterAuthenticationFilter {

    private final ReactiveStringRedisTemplate operations;
    private final ConfigCenter configCenter;


    @Override
    protected Mono<Void> doOnSuccess(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 判断是否开启防抖动功能
        GatewayConfig gatewayConfig = configCenter.getGatewayConfig();
        if (!gatewayConfig.isEnableDebounced()) {
            return chain.filter(exchange);
        }
        return operations.opsForValue().setIfAbsent(
                RedisKeys.getDebounceKeyByValue(md5Hex(exchange)),
                ConstantPool.DEBOUNCE_VALUE,
                Duration.ofMillis(gatewayConfig.getDebounceTimes())
        ).flatMap(res -> Boolean.TRUE.equals(res)
                ? chain.filter(exchange)
                : GatewayErrors.createException(HttpStatus.TOO_MANY_REQUESTS));
    }

    /**
     * 对接口和请求参数进行md5
     *
     * @param exchange 服务请求上下文信息
     * @return 返回md5后的请求结果
     */
    private String md5Hex(ServerWebExchange exchange) {
        //是不是应该还需要解析出当前用户信息，并把用户唯一标识作为key的一部分
        AuthorizationToken authorizationToken = RequestAttributes.loadAuthorizationToken(exchange);
        Optional<SessionUser> principal = authorizationToken.getPrincipal();
        String prefix = "unknown";
        if (principal.isPresent()) {
            SessionUser user = principal.get();
            prefix = user.getId().toString();
        }
        String path = exchange.getRequest().getURI().getPath();
        String params = exchange.getRequest().getQueryParams().toString();
        return DigestUtils.md5Hex(
                prefix + Strings.C_COLON + path + ConstantPool.CONSTANT_QUESTION_MARK + params);
    }

    @Override
    public int getOrder() {
        return FilterOrder.RateLimiting.getOrder();
    }
}
