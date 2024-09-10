package com.ziyao.ideal.gateway.service;

import com.ziyao.ideal.gateway.security.SessionContext;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public interface AuthorizationService {


    void authorize(SessionContext context);
}
