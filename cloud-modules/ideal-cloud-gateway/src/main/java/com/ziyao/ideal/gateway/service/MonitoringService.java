package com.ziyao.ideal.gateway.service;

import com.ziyao.ideal.gateway.authorization.AuthorizationToken;
import com.ziyao.ideal.gateway.filter.body.ReqRes;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public interface MonitoringService {


    void recordUserBehavior(AuthorizationToken authorizationToken, ReqRes reqRes);
}
