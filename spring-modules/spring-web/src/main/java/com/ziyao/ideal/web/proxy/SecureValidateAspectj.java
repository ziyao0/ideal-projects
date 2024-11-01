package com.ziyao.ideal.web.proxy;

import com.alibaba.fastjson2.JSON;
import com.ziyao.ideal.security.core.DefaultAuthenticationToken;
import com.ziyao.ideal.security.core.context.SecurityContextHolder;
import com.ziyao.ideal.web.ResponseBuilder;
import com.ziyao.ideal.web.exception.ServiceException;
import com.ziyao.ideal.web.security.Secured;
import com.ziyao.ideal.web.security.factory.SecureValidateFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Objects;

/**
 * @author ziyao
 * @link <a href="https://github.com/ziyao0">ziyao for github</a>
 */
@Aspect
public class SecureValidateAspectj {

    private final SecureValidateFactory secureValidateFactory;

    public SecureValidateAspectj(SecureValidateFactory secureValidateFactory) {
        this.secureValidateFactory = secureValidateFactory;
    }

    @Around(value = "@annotation(secured)")
    public Object around(ProceedingJoinPoint point, Secured secured) throws Throwable {

        try {
            if (SecurityContextHolder.isAuthorized()) {
                DefaultAuthenticationToken authentication = (DefaultAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
                // 获取请求凭证的参数
                Object arg = point.getArgs()[0];
                Object credentials = JSON.parseObject(JSON.toJSONString(arg)).get("credentials");
                if (Objects.isNull(credentials)) {
                    throw new ServiceException(ResponseBuilder.forbidden());
                }
                authentication.setCredentials(credentials.toString());
                // 填充凭证
                SecurityContextHolder.getContext().setAuthentication(authentication);
                // 校验
                this.secureValidateFactory.validate(secured.mode(), secured.condition());
                // 校验通过后执行后续逻辑
                return point.proceed();
            }
            throw new ServiceException(ResponseBuilder.forbidden());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(ResponseBuilder.internalServerError());
        }
    }
}
