package com.ziyao.ideal.web.orm;

import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Setter
public abstract class AbstractPersistentEntity<T> {

    /**
     * 创建人ID
     */
    private T createdBy;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新人ID
     */
    private T updatedBy;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    public Optional<T> getCreatedBy() {
        return Optional.ofNullable(createdBy);
    }

    public Optional<LocalDateTime> getCreatedAt() {
        return Optional.ofNullable(createdAt);
    }

    public Optional<T> getUpdatedBy() {
        return Optional.ofNullable(updatedBy);
    }

    public Optional<LocalDateTime> getUpdatedAt() {
        return Optional.ofNullable(updatedAt);
    }

}
