package com.ziyao.ideal.generator;

import java.lang.annotation.*;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigurationProperties {

    /**
     * 属性配置前缀
     */
    String prefix();
}
