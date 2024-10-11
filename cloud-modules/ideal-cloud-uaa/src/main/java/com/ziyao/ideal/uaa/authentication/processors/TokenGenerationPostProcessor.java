package com.ziyao.ideal.uaa.authentication.processors;

import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.security.core.User;
import com.ziyao.ideal.security.core.UserParamNames;
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

        //生成认证token
        User principal = preprocess(authentication);

        AccessToken token = tokenGenerator.generate(null);

        principal.setClaim(UserParamNames.BEARER_TOKEN, token);

        userRepositoryRedis.save(token.getTokenValue(), principal);

        userRepositoryRedis.expire(token.getTokenValue(), principal.getTtl(), TimeUnit.MINUTES);
        // 判断是否需要刷新token

        return authentication;
    }

    /**
     * 预处理逻辑
     * <p>
     * 填充一些必要的参数
     *
     * @param authentication 认证对象
     * @return <code>User</code>
     */
    private User preprocess(Authentication authentication) {
        String ip = SecurityContextHolder.getContext().getPrincipal().getIp();
        String location = SecurityContextHolder.getContext().getPrincipal().getLocation();
        LoginConfig loginConfig = loginConfigService.getAccountPasswordLoginConfig();

        User principal = (User) authentication.getPrincipal();
        principal.setLoginIp(ip);
        principal.setLoginLocation(location);
        principal.setTtl(loginConfig.getSessionTimeout());
        principal.setLastLogin(LocalDateTime.now());
        return principal;
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
