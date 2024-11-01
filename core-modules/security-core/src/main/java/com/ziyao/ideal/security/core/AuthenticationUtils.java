package com.ziyao.ideal.security.core;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public abstract class AuthenticationUtils {


    public static boolean isAuthenticated(Authentication authentication) {
        return authentication != null && authentication.isAuthenticated();
    }

    public static boolean unauthenticated(Authentication authentication) {
        return !isAuthenticated(authentication);
    }

    private AuthenticationUtils() {
    }
}
