package com.ziyao.ideal.usercenter.authentication.token;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ziyao.ideal.security.core.GrantedAuthority;
import com.ziyao.ideal.security.core.UserInfo;
import com.ziyao.ideal.security.oauth2.core.RegisteredApp;
import org.springframework.lang.NonNull;

import java.io.Serial;
import java.util.Collection;
import java.util.Set;

/**
 * @author ziyao zhang
 */
public class SuccessfulAuthenticationToken extends AbstractAuthenticationToken {


    @Serial
    private static final long serialVersionUID = 919449188452752172L;

    private final UserInfo principal;

    private final RegisteredApp registeredApp;

    @JsonCreator
    public SuccessfulAuthenticationToken(@JsonProperty("principal") UserInfo principal,
                                         @JsonProperty("registeredApp") RegisteredApp registeredApp,
                                         @JsonProperty("authorities") Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.registeredApp = registeredApp;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    public @NonNull RegisteredApp getRegisteredApp() {
        return registeredApp;
    }

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        SuccessfulAuthenticationToken successfulAuthenticationToken1 = new SuccessfulAuthenticationToken(
                UserInfo.withUsername("张子尧").build(), null, Set.of());

        String s = objectMapper.writeValueAsString(successfulAuthenticationToken1);

        System.out.println(s);

        SuccessfulAuthenticationToken successfulAuthenticationToken = objectMapper.readValue(s, SuccessfulAuthenticationToken.class);

        System.out.println(successfulAuthenticationToken);
    }
}
