package com.ziyao.harbor.data.redis.core;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */

public class RedisRawData {

    @Getter
    private final Container container;

    private String keyspace;
    private String id;
    @Getter
    @Setter
    private byte[] raw;
    /**
     * -- SETTER --
     * Set the time before expiration in
     */
    @Setter
    private Long timeToLive;

    public RedisRawData() {
        this(Collections.emptyMap());
    }

    public RedisRawData(byte[] raw) {
        this(Collections.emptyMap());
        this.raw = raw;
    }

    public RedisRawData(Map<String, Object> raw) {
        this(Container.newBucketFromRawMap(raw));
    }

    public RedisRawData(Container container) {

        Assert.notNull(container, "Bucket must not be null");

        this.container = container;
    }

    public void setId(@Nullable String id) {
        this.id = id;
    }

    @Nullable
    public String getId() {
        return this.id;
    }

    @Nullable
    public Long getTimeToLive() {
        return timeToLive;
    }

    @Nullable
    public String getKeyspace() {
        return keyspace;
    }

    public void setKeyspace(@Nullable String keyspace) {
        this.keyspace = keyspace;
    }


    /**
     * Set the time before expiration converting the given arguments to {@link TimeUnit#SECONDS}.
     *
     * @param timeToLive must not be {@literal null}
     * @param timeUnit   must not be {@literal null}
     */
    public void setTimeToLive(Long timeToLive, TimeUnit timeUnit) {

        Assert.notNull(timeToLive, "TimeToLive must not be null when used with TimeUnit");
        Assert.notNull(timeToLive, "TimeUnit must not be null");

        setTimeToLive(TimeUnit.SECONDS.convert(timeToLive, timeUnit));
    }

    @Override
    public String toString() {
        return "RedisDataObject [key=" + keyspace + ":" + id + ", hash=" + container + "]";
    }


}
