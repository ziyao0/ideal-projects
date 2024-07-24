package com.ziyao.ideal.usercenter.response;

/**
 * @author ziyao zhang
 */
public record OAuth2AuthorizationCodeResponse(String code, String state) {

    public static OAuth2AuthorizationCodeResponse create(String code, String state) {
        return new OAuth2AuthorizationCodeResponse(code, state);
    }
}
