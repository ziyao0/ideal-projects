package com.ziyao.ideal.web.security;

import java.lang.annotation.*;

/**
 * 处理对特殊功能的安全校验
 * <p>
 * {@link Mode#CERTIFICATE} 证书模式: 校验当前用户的证书
 * {@link Mode#PASSWD} 密码模式：校验当前操作人的密码
 * {@link Mode#ROLE} 角色模式：校验当前操作人是否拥有操作当前功能的角色，角色定义为获取{@link #condition()}的值
 * {@link Mode#IDENT} 身份模式：校验当前操作人是否拥有操作当前功能的身份，
 * 通过{@link #condition()}或操作当前功能的身份，例如：管理员、一般用户等
 *
 * @author ziyao
 * @link <a href="https://github.com/ziyao0">ziyao for github</a>
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Secured {

    /**
     * 安全模式
     *
     * @return {@code Mode}
     */
    Mode mode() default Mode.PASSWD;

    /**
     * 安全校验所需要的附加条件
     * <p>
     * 角色模式：填写操作当前功能时需要的角色编码
     * 身份模式：填写操作当前功能时需要的身份标识
     *
     * @return 返回附加验证
     */
    String condition() default "";

    enum Mode {
        PASSWD,
        CERTIFICATE,
        ROLE,
        IDENT,
        ;
    }
}
