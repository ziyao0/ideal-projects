package com.ziyao.ideal.uaa.authentication.provider;

import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.security.core.DefaultAuthenticationToken;
import com.ziyao.ideal.security.core.User;
import com.ziyao.ideal.uaa.authentication.codec.BCryptCredentialsEncryptor;
import com.ziyao.ideal.uaa.authentication.codec.CredentialsEncryptor;
import com.ziyao.ideal.uaa.authentication.support.AccountStatusValidator;
import com.ziyao.ideal.uaa.authentication.token.UsernamePasswordAuthenticationToken;
import com.ziyao.ideal.uaa.common.exception.AuthenticationFailureException;
import com.ziyao.ideal.uaa.common.exception.Errors;
import com.ziyao.ideal.uaa.service.user.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @author ziyao zhang
 */
@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    private final CredentialsEncryptor credentialsEncryptor = new BCryptCredentialsEncryptor();

    public UsernamePasswordAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {

        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;

        String username = (String) authenticationToken.getPrincipal();

        User user = userDetailsService.loadUserByUsername(username);

        AccountStatusValidator.assertExists(user);
        // 输入的密码
        String credentials = (String) authenticationToken.getCredentials();
        authenticationToken.eraseCredentials();
        if (!credentialsEncryptor.matches(credentials, user.getPassword())) {
            throw new AuthenticationFailureException(Errors.ERROR_100009);
        }
        user.eraseCredentials();
        // 检查账号状态
        AccountStatusValidator.valid(user);
        //构建成功的认证对象
        return DefaultAuthenticationToken.builder().principal(user).authenticated().build();
    }

    @Override
    public boolean supports(Class<?> authenticationClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authenticationClass);
    }
}
