package com.ziyao.ideal.uaa.authentication.strategy;

import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.security.core.AuthenticationException;
import com.ziyao.ideal.uaa.authentication.token.FailureAuthenticationToken;
import com.ziyao.ideal.uaa.authentication.token.UsernamePasswordAuthenticationToken;
import com.ziyao.ideal.web.exception.ServiceException;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 超出预期外的异常处理逻辑
 * <p>
 * 通常是由于程序不可见的错误引起的，比如代码书写不健壮或者出现未在可控制范围内的错误
 *
 * @author ziyao zhang
 */
@Component
public class UnexpectedConditionFailureStrategy implements AuthenticationFailureStrategy {



    @Override
    public Authentication handleFailure(Authentication authentication, Exception exception) {
        // 处理程序因为某些未知原因所产生的异常

        // 先判断异常是否为不可控异常
        if (exception instanceof ServiceException
                || exception instanceof AuthenticationException) {
            return null;
        }

        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;

        Object principal = authenticationToken.getPrincipal();

        // 记录异常信息
        // 发送告警
        // 抛出登录失败异常

        return FailureAuthenticationToken.of(500, "您访问的系统正在进行维护，" + tostring(exception));
    }

    @Override
    public boolean isSupport(Class<? extends Exception> exceptionClass) {
        return Exception.class.isAssignableFrom(exceptionClass);
    }


    private String tostring(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString();
    }
}
