package com.ziyao.ideal.gateway.filter;

import com.ziyao.ideal.gateway.support.GSecurityContextExtractor;
import com.ziyao.ideal.gateway.support.GatewayStopWatches;
import com.ziyao.ideal.gateway.common.response.RequestAttributes;
import com.ziyao.ideal.gateway.security.DefaultGSecurityContext;
import com.ziyao.ideal.gateway.filter.intercept.DefaultInterceptContext;
import com.ziyao.ideal.gateway.filter.intercept.GatewayInterceptor;
import com.ziyao.ideal.gateway.filter.intercept.InterceptContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 前置访问控制过滤器
 *
 * @author zhangziyao
 */
@Component
public class AuthorizationPreFilter extends AbstractGlobalFilter {

    private final GatewayInterceptor interceptor;

    public AuthorizationPreFilter(@Qualifier("delegatingInterceptor") GatewayInterceptor interceptor) {
        this.interceptor = interceptor;
    }


    @Override
    protected Mono<Void> doFilter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 2023/9/9 从请求头提取请求路径，请求ip等相关信息，进行前置校验   快速失败
        DefaultGSecurityContext securityContext = GSecurityContextExtractor.extractForHeaders(exchange);
        RequestAttributes.storeAttribute(exchange, securityContext);
        return Mono.just(securityContext)
                .flatMap(access -> {
                    InterceptContext context = DefaultInterceptContext.withRequestUri(access.getRequestUri())
                            .clientIp(access.getIp())
                            .domains(access.getDomain())
                            .build();
                    // 过滤黑名单、跨域等相关信息
                    interceptor.intercept(context);
                    GatewayStopWatches.stop(getBeanName(), exchange);
                    return chain.filter(exchange);
                });
    }


    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE + 1;
    }
}
