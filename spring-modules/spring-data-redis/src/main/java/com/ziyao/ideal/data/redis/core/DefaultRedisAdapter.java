package com.ziyao.ideal.data.redis.core;

import com.ziyao.ideal.core.Collections;
import com.ziyao.ideal.data.redis.core.convert.ConversionProvider;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mapping.PersistentPropertyAccessor;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.mapping.RedisPersistentEntity;
import org.springframework.data.redis.core.mapping.RedisPersistentProperty;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author ziyao zhang
 */
@SuppressWarnings("deprecation")
public class DefaultRedisAdapter implements RedisAdapter {

    private static final int PHANTOM_KEY_TTL = 300;
    private final RedisOperations<?, ?> redisOps;
    private final ConversionProvider conversionProvider;

    public DefaultRedisAdapter(RedisOperations<?, ?> redisOps) {
        this.redisOps = redisOps;
        this.conversionProvider = ConversionProvider.getInstance();
    }


    @Override
    public <T> T findById(Object id, String keyspace, Class<T> type) {

        String stringId = tostring(id);
        byte[] binId = rawKey(keyspace, stringId);
        RedisCallback<byte[]> command = connection -> connection.get(binId);

        byte[] raw = this.execute(command);

        if (raw == null || raw.length == 0) {
            return null;
        }
        RedisRawData data = new RedisRawData(raw);

        data.setId(stringId);
        data.setKeyspace(keyspace);
        return readBackTimeToLiveIfSet(binId, conversionProvider.read(type, data));
    }

    @Override
    public <T> void save(Object id, String keyspace, T source) {


        RedisRawData rdo = new RedisRawData();

        this.conversionProvider.write(source, rdo);

        if (rdo.getId() == null) {
            rdo.setId(this.conversionProvider.convert(id, String.class));
        }
        redisOps.execute((RedisCallback<Object>) connection -> {

            byte[] key = toBytes(rdo.getId());
            byte[] objectKey = rawKey(rdo.getKeyspace(), rdo.getId());

            boolean isNew = Boolean.TRUE.equals(connection.exists(objectKey));

            connection.set(objectKey, rdo.getRaw());

            if (isNew) {
                connection.sAdd(toBytes(rdo.getKeyspace()), key);
            }

            if (expires(rdo.getTimeToLive())) {
                connection.expire(objectKey, rdo.getTimeToLive());
            } else {
                connection.persist(objectKey);
            }
            return null;
        });
    }

    @Override
    public void delete(Object id, String keyspace) {


        byte[] binId = toBytes(id);
        byte[] binKeyspace = toBytes(keyspace);

        byte[] keyToDelete = rawKey(keyspace, tostring(id));

        this.execute((RedisCallback<Void>) connection -> {

            connection.del(keyToDelete);
            connection.sRem(binKeyspace, binId);

            return null;
        });
    }

    @Override
    public <T> void deleteOf(@NonNull String keyspace) {


        redisOps.execute((RedisCallback<Void>) connection -> {
            Set<byte[]> ids = connection.sMembers(toBytes(keyspace));

            if (Collections.isEmpty(ids)) {
                connection.del(toBytes(keyspace));
                return null;
            }

            List<byte[]> keys = new ArrayList<>(ids.size());

            for (byte[] id : ids) {
                keys.add(rawKey(keyspace, tostring(id)));
            }
            connection.del(keys.toArray(new byte[0][]));
            connection.del(toBytes(keyspace));
            return null;
        });
    }

    @Override
    public boolean contains(Object id, String keyspace) {
        RedisCallback<Boolean> command = connection -> connection.sIsMember(toBytes(keyspace), toBytes(id));

        return Boolean.TRUE.equals(this.execute(command));
    }

    @Override
    public boolean expire(Object id, String keyspace, long ttl) {

        return execute(connection -> {

            byte[] rawKey = rawKey(keyspace, tostring(id));
            return connection.expire(rawKey, ttl);
        });
    }

    @Override
    public <T> T execute(RedisCallback<T> callback) {
        return redisOps.execute(callback);
    }


    private boolean expires(Long timeToLive) {
        return timeToLive != null && timeToLive > 0;
    }

    private String tostring(Object source) {
        return conversionProvider.convert(source, String.class);
    }

    private byte[] rawKey(String keyspace, String id) {
        return toBytes(keyspace + ":" + id);
    }

    public byte[] toBytes(Object source) {
        return source instanceof byte[] bytes ? bytes
                : conversionProvider.convert(source, byte[].class);
    }

    private <T> T readBackTimeToLiveIfSet(@Nullable byte[] key, @Nullable T target) {

        if (target == null || key == null) {
            return target;
        }

        RedisPersistentEntity<?> entity = this.conversionProvider.getMappingContext().getRequiredPersistentEntity(target.getClass());

        if (entity.hasExplictTimeToLiveProperty()) {

            RedisPersistentProperty ttlProperty = entity.getExplicitTimeToLiveProperty();

            if (ttlProperty == null) {
                return target;
            }

            TimeToLive ttl = ttlProperty.findAnnotation(TimeToLive.class);

            Long timeout = redisOps.execute((RedisCallback<Long>) connection -> {

                if (ttl == null) {
                    return null;
                }

                if (ObjectUtils.nullSafeEquals(TimeUnit.SECONDS, ttl.unit())) {
                    return connection.ttl(key);
                }

                return connection.pTtl(key, ttl.unit());
            });

            if (timeout != null || !ttlProperty.getType().isPrimitive()) {

                PersistentPropertyAccessor<T> propertyAccessor = entity.getPropertyAccessor(target);

                propertyAccessor.setProperty(ttlProperty,
                        this.conversionProvider.convert(timeout, ttlProperty.getType()));

                target = propertyAccessor.getBean();
            }
        }

        return target;
    }

}
