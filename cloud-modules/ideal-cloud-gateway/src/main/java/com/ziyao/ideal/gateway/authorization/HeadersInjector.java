package com.ziyao.ideal.gateway.authorization;

import org.springframework.web.server.ServerWebExchange;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public interface HeadersInjector {

    void inject(ServerWebExchange exchange);
}
