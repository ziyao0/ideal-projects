package com.ziyao.harbor.usercenter.authentication.provider;

import com.ziyao.harbor.usercenter.authentication.codec.BCryptCredentialsEncryptor;
import com.ziyao.harbor.usercenter.authentication.codec.CredentialsEncryptor;
import com.ziyao.harbor.usercenter.authentication.support.UserDetailsValidator;
import com.ziyao.harbor.usercenter.authentication.token.UsernamePasswordAuthenticationToken;
import com.ziyao.harbor.usercenter.common.exception.AuthenticationFailureException;
import com.ziyao.harbor.usercenter.common.exception.Errors;
import com.ziyao.harbor.usercenter.service.user.UserDetailsService;
import com.ziyao.security.oauth2.core.Authentication;
import com.ziyao.security.oauth2.core.UserDetails;
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

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        UserDetailsValidator.assertExists(userDetails);
        // 输入的密码
        String credentials = (String) authenticationToken.getCredentials();

        if (!credentialsEncryptor.matches(credentials, userDetails.getPassword())) {
            throw new AuthenticationFailureException(Errors.ERROR_100009);
        }

        UserDetailsValidator.validated(userDetails);
        return UsernamePasswordAuthenticationToken.authenticated(
                userDetails, null, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authenticationClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authenticationClass);
    }
}
