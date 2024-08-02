package com.ziyao.ideal.data.redis.core;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface TimeToLive {

    /**
     * 存活时间
     *
     * @return 返回-1则表示永久存活
     */
    long ttl() default -1;

    /**
     * 时间单位
     *
     * @return {@return TimeUnit}
     */
    TimeUnit unit() default TimeUnit.SECONDS;
}
