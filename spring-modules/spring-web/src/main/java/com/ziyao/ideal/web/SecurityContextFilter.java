package com.ziyao.ideal.web;

import com.google.common.collect.Lists;
import com.ziyao.ideal.core.Collections;
import com.ziyao.ideal.core.Dates;
import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.security.core.*;
import com.ziyao.ideal.security.core.context.SecurityContext;
import com.ziyao.ideal.security.core.context.SecurityContextHolder;
import com.ziyao.ideal.security.core.context.SecurityContextImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

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

        UserInfo userInfo = creation(request);
        Authentication authentication = new SuccessfulAuthenticationToken(userInfo, userInfo.getAuthorities());

        SecurityContext context = new SecurityContextImpl(authentication);
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

    public UserInfo creation(HttpServletRequest request) {
        String userId = getValue(request, UserParamNames.USER_ID);
        String username = getValue(request, UserParamNames.USERNAME);
        String status = getValue(request, UserParamNames.STATUS);
        String idCardName = getValue(request, UserParamNames.ID_CARD_NAME);
        String gender = getValue(request, UserParamNames.GENDER);
        String mobile = getValue(request, UserParamNames.MOBILE);
        String address = getValue(request, UserParamNames.ADDRESS);
        String lastLogin = getValue(request, UserParamNames.LAST_LOGIN);
        String loginIp = getValue(request, UserParamNames.LOGIN_IP);
        String authorities = getValue(request, UserParamNames.AUTHORITIES);

        return UserInfo.withId(Strings.hasLength(userId) ? Integer.valueOf(userId) : null)
                .username(username)
                .status(Strings.hasLength(status) ? Integer.valueOf(status) : null)
                .idCardName(idCardName)
                .gender(gender)
                .mobile(mobile)
                .address(address)
                .lastLogin(Strings.hasLength(lastLogin) ? Dates.parse(lastLogin) : null)
                .loginIp(loginIp)
                .authorities(grantedAuthorities -> {
                    Set<String> authorityList = Strings.commaDelimitedListToSet(authorities);
                    if (Collections.nonNull(authorityList)) {
                        for (String authority : authorityList) {
                            grantedAuthorities.add(new SimpleGrantedAuthority(authority));
                        }
                    }
                }).build();
    }

    private static String getValue(HttpServletRequest request, String name) {
        String value = request.getHeader(name);
        if (Strings.hasLength(value)) {
            try {
                return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return value;
    }
}
