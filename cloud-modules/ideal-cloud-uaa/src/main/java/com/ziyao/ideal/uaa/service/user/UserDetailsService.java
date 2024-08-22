package com.ziyao.ideal.uaa.service.user;

import com.ziyao.ideal.core.lang.Nullable;
import com.ziyao.ideal.security.core.UserDetails;

/**
 * @author ziyao
 */
public interface UserDetailsService {

    /**
     * 通过用户名获取用户信息
     *
     * @param username 用户登陆名
     * @return 返回用户信息
     */
    @Nullable
    UserDetails loadUserByUsername(String username);

}
