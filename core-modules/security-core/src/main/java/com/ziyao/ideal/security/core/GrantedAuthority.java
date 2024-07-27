package com.ziyao.ideal.security.core;

import com.ziyao.ideal.core.Collections;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author ziyao
 */
public interface GrantedAuthority extends Serializable {

    /**
     * 获取权限信息
     */
    String getAuthority();

    /**
     * 返回当前角色的操作权限
     *
     * @return 如果返回结果为null获取为集合为空，则代表能操作该角色下的任何功能
     */
    Collection<Permission> getPermissions();

    /**
     * 是否要继续进行授权
     */
    default boolean isContinued() {
        return Collections.nonNull(getPermissions());
    }
}
