package com.ziyao.harbor.usercenter.authentication.strategy;

import com.ziyao.harbor.usercenter.common.exception.InvalidCredentialsException;
import com.ziyao.security.oauth2.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @author ziyao zhang
 */
@Component
public class InvalidCredentialsFailureStrategy implements AuthenticationFailureStrategy {
    @Override
    public Authentication handleFailure(Authentication authentication, Exception exception) {
        if (!(exception instanceof InvalidCredentialsException)) {
            return null;
        }
         /*
        TODO 处理密码错误异常信息
        判断当前用户密码输入错误了几次：
         如果密码输入错误次数到达上线，则对账号进行锁定并设置锁定时间
         如果密码输入错误次数没有到达上线，则对错误次数进行累加
         */
        return null;
    }
}
