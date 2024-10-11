package com.ziyao.ideal.web;

import com.google.common.collect.Lists;
import com.ziyao.ideal.core.Collections;
import com.ziyao.ideal.core.Dates;
import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.security.core.*;
import com.ziyao.ideal.security.core.context.SecurityContext;
import com.ziyao.ideal.security.core.context.SecurityContextHolder;
import com.ziyao.ideal.security.core.context.SecurityContextImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author ziyao zhang
 */
@Slf4j
@WebFilter("/*")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityContextFilter extends OncePerRequestFilter {

    private final List<String> excludePaths;

    private final AntPathMatcher matcher = new AntPathMatcher();

    public SecurityContextFilter() {
        this.excludePaths = Lists.newArrayList(
                "/actuator/**",
                "/error/**",
                "/auth/**",
                "/oauth2/**"
        );
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response, @NonNull FilterChain chain) throws ServletException, IOException {

        User.UserBuilder builder = creation(request);

        User user = builder.claims(claims -> {
            claims.put(UserParamNames.IP, getValue(request, UserParamNames.IP));
            claims.put(UserParamNames.LOCATION, getValue(request, UserParamNames.LOCATION));
        }).build();


        String authorities = getValue(request, UserParamNames.AUTHORITIES);

        Authentication authentication = DefaultAuthenticationToken.builder()
                .principal(user)
                .authorities(grantedAuthorities -> {
                    Set<String> authorityList = Strings.commaDelimitedListToSet(authorities);
                    if (Collections.nonNull(authorityList)) {
                        for (String authority : authorityList) {
                            grantedAuthorities.add(new SimpleGrantedAuthority(authority));
                        }
                    }
                }).build();

        SecurityContext context = new SecurityContextImpl(authentication);
        // 填充附加信息

        SecurityContextHolder.setContext(context);

        if (SecurityContextHolder.unauthorized()) {
            log.debug("请求未认证，URL:{}", request.getRequestURL());
        }
        chain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        return this.excludePaths.stream()
                .anyMatch(skipApi -> matcher.match(skipApi, request.getRequestURI()));
    }

    public User.UserBuilder creation(HttpServletRequest request) {
        String userId = getValue(request, UserParamNames.USER_ID);
        String username = getValue(request, UserParamNames.USERNAME);
        String nickname = getValue(request, UserParamNames.NICKNAME);
        String status = getValue(request, UserParamNames.STATUS);
        String idCardName = getValue(request, UserParamNames.ID_CARD_NAME);
        String gender = getValue(request, UserParamNames.GENDER);
        String mobile = getValue(request, UserParamNames.MOBILE);
        String address = getValue(request, UserParamNames.ADDRESS);
        String lastLogin = getValue(request, UserParamNames.LAST_LOGIN);
        String loginIp = getValue(request, UserParamNames.LOGIN_IP);

        return User.withId(Strings.hasLength(userId) ? Integer.valueOf(userId) : null)
                .username(username)
                .nickname(nickname)
                .status(Strings.hasLength(status) ? Integer.valueOf(status) : null)
                .idCardName(idCardName)
                .gender(gender)
                .mobile(mobile)
                .address(address)
                .lastLogin(Strings.hasLength(lastLogin) ? Dates.parse(lastLogin) : null)
                .loginIp(loginIp);
    }

    private static String getValue(HttpServletRequest request, String name) {
        String value = request.getHeader(name);
        if (Strings.hasLength(value)) {
            return URLDecoder.decode(value, StandardCharsets.UTF_8);
        }
        return value;
    }
}
