package com.ziyao.ideal.gateway.authorization;

import com.ziyao.ideal.gateway.core.ResponseDetails;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AuthorizationToken extends AbstractAuthorizationToken {

    private ResponseDetails responseDetails = new ResponseDetails.Instance(401, "请求未认证");


    public static AuthorizationToken of() {
        AuthorizationToken authorizationToken = new AuthorizationToken();
        authorizationToken.setAuthorized(true);
        return authorizationToken;
    }
}
