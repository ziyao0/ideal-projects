package com.ziyao.ideal.gateway.authorization.convertor;

import com.ziyao.ideal.gateway.authorization.Authorization;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public interface AuthorizationConvertor {


    /**
     * 转换请求为授权对象
     *
     * @param exchange the current server exchange
     * @return {@link Authorization}
     */
    Authorization convert(ServerWebExchange exchange);

}
