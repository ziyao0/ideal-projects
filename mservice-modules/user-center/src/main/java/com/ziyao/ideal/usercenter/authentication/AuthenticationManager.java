package com.ziyao.ideal.usercenter.authentication;

import com.ziyao.ideal.security.core.Authentication;

/**
 * @author ziyao zhang
 */
public interface AuthenticationManager {

    /**
     * 认证处理 映射
     *
     * @param authentication {@link Authentication}认证核心参数
     * @return 返回认证结果
     */
    Authentication authenticate(Authentication authentication);
}
