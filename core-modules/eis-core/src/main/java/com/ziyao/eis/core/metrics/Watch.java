package com.ziyao.eis.core.metrics;

import java.lang.annotation.*;

/**
 * @author ziyao zhang
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Watch {

    /**
     * 秒表名称
     *
     * @see StopWatch
     */
    String value();

    String description() default "";

}
