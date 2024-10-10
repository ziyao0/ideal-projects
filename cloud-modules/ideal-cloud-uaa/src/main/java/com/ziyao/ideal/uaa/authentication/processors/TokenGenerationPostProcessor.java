package com.ziyao.ideal.uaa.authentication.processors;

import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.security.core.User;
import com.ziyao.ideal.security.core.context.SecurityContextHolder;
import com.ziyao.ideal.uaa.authentication.token.AccessToken;
import com.ziyao.ideal.uaa.authentication.token.AccessTokenGenerator;
import com.ziyao.ideal.uaa.domain.entity.LoginConfig;
import com.ziyao.ideal.uaa.repository.redis.UserRepositoryRedis;
import com.ziyao.ideal.uaa.service.LoginConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author ziyao zhang
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TokenGenerationPostProcessor implements AuthenticationPostProcessor {

    private final AccessTokenGenerator tokenGenerator = new AccessTokenGenerator();

    private final UserRepositoryRedis userRepositoryRedis;
    private final LoginConfigService loginConfigService;


    @Override
    public Authentication process(Authentication authentication) {

        String ip = SecurityContextHolder.getContext().getPrincipal().getIp();
        String location = SecurityContextHolder.getContext().getPrincipal().getLocation();

        //生成认证token
        User principal = (User) authentication.getPrincipal();

        log.debug("principal:{}", principal);

        AccessToken token = tokenGenerator.generate(null);

        LoginConfig loginConfig = loginConfigService.getAccountPasswordLoginConfig();

        principal.setTtl(loginConfig.getSessionTimeout());
        principal.setLastLogin(LocalDateTime.now());
        principal.setLoginIp(ip);

        userRepositoryRedis.save(token.getTokenValue(), principal);

        userRepositoryRedis.expire(token.getTokenValue(), loginConfig.getSessionTimeout(), TimeUnit.MINUTES);
        // 判断是否需要刷新token

        return authentication;
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
