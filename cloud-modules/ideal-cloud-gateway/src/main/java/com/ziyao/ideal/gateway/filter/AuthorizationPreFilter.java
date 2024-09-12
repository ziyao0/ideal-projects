//package com.ziyao.ideal.gateway.filter;
//
//import com.ziyao.ideal.gateway.intercept.RequestInterceptor;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
///**
// * 前置访问控制过滤器
// *
// * @author zhangziyao
// */
//@Component
//public class AuthorizationPreFilter extends AbstractGlobalFilter {
//
//    private final RequestInterceptor interceptor;
//
//    public AuthorizationPreFilter(@Qualifier("delegatingInterceptor") RequestInterceptor interceptor) {
//        this.interceptor = interceptor;
//    }
//
//
//    @Override
//    protected Mono<Void> doFilter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        // 2023/9/9 从请求头提取请求路径，请求ip等相关信息，进行前置校验   快速失败
////        DefaultSessionContext securityContext = SessionContextExtractor.extractForHeaders(exchange);
////        RequestAttributes.storeAttribute(exchange, securityContext);
////        return Mono.just(securityContext)
////                .flatMap(access -> {
////                    Authorization authorization = DefaultInterceptContext.withRequestUri(access.getRequestUri())
////                            .clientIp(access.getIp())
////                            .domains(access.getDomain())
////                            .build();
////                    // 过滤黑名单、跨域等相关信息
////                    interceptor.intercept(context);
////                    GatewayStopWatches.stop(getBeanName(), exchange);
////                    return chain.filter(exchange);
////                });
//        return null;
//    }
//
//
//    @Override
//    public int getOrder() {
//        return HIGHEST_PRECEDENCE + 1;
//    }
//}
