package com.ziyao.ideal.gateway.service.impl;

import com.alibaba.fastjson2.JSON;
import com.ziyao.ideal.gateway.authorization.AuthorizationToken;
import com.ziyao.ideal.gateway.filter.body.ReqRes;
import com.ziyao.ideal.gateway.service.MonitoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Service
@RequiredArgsConstructor
public class MonitoringServiceImpl implements MonitoringService {


    @Override
    public void recordUserBehavior(AuthorizationToken authorizationToken, ReqRes reqRes) {
        System.out.println(JSON.toJSONString(authorizationToken));
        System.out.println(JSON.toJSONString(reqRes));
    }
}
