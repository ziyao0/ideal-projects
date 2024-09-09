package com.ziyao.ideal.gateway.filter;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.security.core.SessionUser;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class UserSessionProcessingFilter extends AbstractGlobalFilter {



    @Override
    protected Mono<Void> doFilter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String sessionId = exchange.getRequest().getHeaders().getFirst("sessionId");
        return exchange.getSession().flatMap(session -> {
            if (Strings.hasText(sessionId)) {
                Optional<SessionUser> sessionUserOptional = Optional.ofNullable(
                        (SessionUser) session.getAttribute(sessionId)).or(() -> fetchSessionUserForRedis(sessionId));
                sessionUserOptional.ifPresent(sessionUser -> {
                    // 把当前用户信息存储到缓存中
                });

            }


            // 从缓存中获取sessionUser
            // 处理用户未登录的逻辑
            return chain.filter(exchange);
        });
//        return null;
    }

    private Optional<SessionUser> fetchSessionUserForRedis(String sessionId) {
        return Optional.empty();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
