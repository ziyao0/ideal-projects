package com.ziyao.ideal.gateway.service.impl;

import com.ziyao.ideal.gateway.security.SessionContext;
import com.ziyao.ideal.gateway.service.AuthorizationService;
import com.ziyao.ideal.gateway.service.SessionContextService;
import com.ziyao.ideal.security.core.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    private final SessionContextService sessionContextService;
    @Override
    public void authorize(SessionContext context) {

        Optional<SessionUser> sessionUser = sessionContextService.load(context.getSessionId());


    }
}
