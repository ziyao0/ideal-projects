package com.ziyao.ideal.gateway.filter;

import lombok.Getter;
import org.springframework.core.Ordered;

/**
 * @author ziyao zhang
 */
@Getter
public enum FilterOrder {
    Bootstrapping(Ordered.HIGHEST_PRECEDENCE),
    Authorization(0),
    RateLimiting(100),
    Permission(200),
    RequestBodyDecode(300),
    ResponseBodyEncode(400),
    RevokeAuthHttpHeaders(Ordered.LOWEST_PRECEDENCE),
    ;

    private final int order;

    FilterOrder(int order) {
        this.order = order;
    }

}
