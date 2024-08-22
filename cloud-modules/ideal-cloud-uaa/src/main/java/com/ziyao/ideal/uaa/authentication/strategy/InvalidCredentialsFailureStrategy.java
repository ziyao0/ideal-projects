package com.ziyao.ideal.uaa.authentication.strategy;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.uaa.authentication.token.UsernamePasswordAuthenticationToken;
import com.ziyao.ideal.uaa.common.exception.InvalidCredentialsException;
import com.ziyao.ideal.uaa.common.utils.RedisUtils;
import com.ziyao.ideal.uaa.domain.entity.LoginConfig;
import com.ziyao.ideal.uaa.service.LoginConfigService;
import com.ziyao.ideal.uaa.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author ziyao zhang
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class InvalidCredentialsFailureStrategy implements AuthenticationFailureStrategy {


    private final UserService userService;
    private final StringRedisTemplate redisOps;
    private final LoginConfigService loginConfigService;

    @Override
    public Authentication handleFailure(Authentication authentication, Exception exception) {

        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
        /*
         *处理密码错误异常信息
         *判断当前用户密码输入错误了几次：
         *如果密码输入错误次数到达上线，则对账号进行锁定并设置锁定时间
         *如果密码输入错误次数没有到达上线，则对错误次数进行累加
         */
        LoginConfig loginConfig = loginConfigService.getAccountPasswordLoginConfig();

        String username = authenticationToken.getName();

        String passwordErrorTimesKey = RedisUtils.Uaa.getPasswordErrorTimesKey(username);

        String numsStr = redisOps.opsForValue().get(passwordErrorTimesKey);
        int errorTimes = 0;
        if (Strings.hasLength(numsStr)) {
            errorTimes = Integer.parseInt(numsStr);
        }

        if (isExceedMaximumLimit(errorTimes, loginConfig.getMaxAttempts())) {
            handleAccountLock(username);
        } else {
            redisOps.opsForValue().increment(passwordErrorTimesKey, ++errorTimes);
        }

        return buildErrorMessage(errorTimes, loginConfig.getMaxAttempts());
    }

    @Override
    public boolean isSupport(Class<? extends Exception> exceptionClass) {
        return InvalidCredentialsException.class.isAssignableFrom(exceptionClass);
    }

    /**
     * 对账号信息锁定
     *
     * @param username 账号名
     */
    private void handleAccountLock(String username) {
        // 对账号进行锁定
        if (userService.lock(username)) {
            log.debug("账号：{}已锁定", username);
        } else {
            log.error("账号：{}锁定失败", username);
        }
    }

    /**
     * 是否超过最大限制
     * 判断是否超过了规定的最大限制
     *
     * @return <code>true</code> 超过最大限制
     */
    private boolean isExceedMaximumLimit(int errorTimes, int maxAttempts) {
        return errorTimes >= maxAttempts;
    }

    /**
     * 组装错误信息
     *
     * @param errorTimes  错误次数
     * @param maxAttempts 最大尝试次数
     * @return 返回构建测错误信息
     */
    private Authentication buildErrorMessage(int errorTimes, int maxAttempts) {
        String message = "用户名密码错误，错误次数：" + errorTimes + "，剩余错误次数：" + (maxAttempts - errorTimes);
        return createFailureAuthentication(500, message);
    }
}
