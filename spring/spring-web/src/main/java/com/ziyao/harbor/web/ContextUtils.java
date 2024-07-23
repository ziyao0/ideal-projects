package com.ziyao.harbor.web;

import com.ziyao.eis.core.Strings;

import java.util.Objects;

/**
 * @author zhangziyao
 */
public abstract class ContextUtils {

    private ContextUtils() {
    }

    public static boolean isLegal(UserDetails userDetails) {
        return Objects.nonNull(userDetails) && Strings.hasLength(userDetails.getUsername());
    }
}
