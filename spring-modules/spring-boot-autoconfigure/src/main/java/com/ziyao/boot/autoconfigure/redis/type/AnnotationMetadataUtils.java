package com.ziyao.boot.autoconfigure.redis.type;

import org.springframework.core.type.StandardAnnotationMetadata;

/**
 * @author ziyao zhang
 */
public abstract class AnnotationMetadataUtils {

    @SuppressWarnings("deprecation")
    public static org.springframework.core.type.AnnotationMetadata introspect(Class<?> type) {
        return new StandardAnnotationMetadata(type, true);
    }
}
