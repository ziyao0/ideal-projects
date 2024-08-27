package com.ziyao.ideal.generator.mybatisplus;

import java.lang.annotation.*;

/**
 * 乐观锁注解
 * <p>
 * 支持的字段类型:
 * long,
 * Long,
 * int,
 * Integer,
 * java.util.Date,
 * java.sql.Timestamp,
 * java.time.LocalDateTime
 * java.time.Instant
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface Version {
}
