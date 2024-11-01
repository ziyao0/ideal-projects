package com.ziyao.ideal.web.security.factory;

import com.ziyao.ideal.security.core.context.SecurityContext;
import com.ziyao.ideal.web.security.Secured;

/**
 * @author ziyao
 * @link <a href="https://github.com/ziyao0">ziyao for github</a>
 */
public interface SecureValidator {

    /**
     * 验证
     */
    void validate(SecurityContext context,String condition);

    /**
     * 当前验证器模式
     */
    Secured.Mode mode();
}
