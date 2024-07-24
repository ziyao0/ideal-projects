package com.ziyao.ideal.usercenter.authentication.strategy;

import com.ziyao.harbor.web.exception.ServiceException;
import com.ziyao.security.oauth2.core.Authentication;
import com.ziyao.security.oauth2.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author ziyao zhang
 */
@Component
public class ProgramExceptionFailureStrategy implements AuthenticationFailureStrategy {
    @Override
    public Authentication handleFailure(Authentication authentication, Exception exception) {
        // 处理程序因为某些未知原因所产生的异常

        // 先判断异常是否为不可控异常
        if (exception instanceof ServiceException
                || exception instanceof AuthenticationException) {
            return null;
        }

        // 记录异常信息
        // 发送告警
        // 抛出登录失败异常

        return null;
    }

    private String tostring(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString();
    }
}
