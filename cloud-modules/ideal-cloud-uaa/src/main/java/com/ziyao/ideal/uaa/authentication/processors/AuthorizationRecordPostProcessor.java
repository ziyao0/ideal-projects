package com.ziyao.ideal.uaa.authentication.processors;

import com.ziyao.ideal.security.core.Authentication;
import com.ziyao.ideal.security.core.User;
import com.ziyao.ideal.security.core.context.SecurityContextHolder;
import com.ziyao.ideal.uaa.authentication.LoginMethod;
import com.ziyao.ideal.uaa.authentication.support.AuthenticationConstants;
import com.ziyao.ideal.uaa.authentication.support.SystemContextHolder;
import com.ziyao.ideal.uaa.domain.entity.LoginRecord;
import com.ziyao.ideal.uaa.repository.jpa.LoginRecordRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * @author ziyao zhang
 */
@Component
@RequiredArgsConstructor
public class AuthorizationRecordPostProcessor implements AuthenticationPostProcessor {

    private final LoginRecordRepositoryJpa loginRecordRepositoryJpa;

    @Override
    public Authentication process(Authentication authentication) {

        // 记录授权记录 、登录记录等


        String ip = SecurityContextHolder.getContext().getIp();
        String location = SecurityContextHolder.getContext().getLocation();

        User principal = (User) authentication.getPrincipal();

        User user = (User) authentication.getPrincipal();


        LoginRecord.Builder builder = new LoginRecord.Builder();

        int i = new Random().nextInt(4);

        LoginRecord loginRecord = builder.id(i).userId(user.getId())
                .username(user.getUsername())
                .status(authentication.isAuthenticated()
                        ? AuthenticationConstants.SUCCESS
                        : AuthenticationConstants.FAIL)
                .loginIp(ip)
                .location(location)
                .loginMethod(LoginMethod.PASSWORD.name())
                .loginTime(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .osInfo(SystemContextHolder.getOS())
                .browserInfo(SystemContextHolder.getBrowser())
                .build();

        loginRecordRepositoryJpa.save(loginRecord);

        return authentication;
    }
}
