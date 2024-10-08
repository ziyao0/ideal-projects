package com.ziyao.ideal.uaa.authentication.token;

import com.ziyao.ideal.security.oauth2.core.Token;
import com.ziyao.ideal.security.oauth2.core.token.OAuth2TokenContext;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public interface TokenGenerator<T extends Token> {


    T generate(OAuth2TokenContext context);

}
