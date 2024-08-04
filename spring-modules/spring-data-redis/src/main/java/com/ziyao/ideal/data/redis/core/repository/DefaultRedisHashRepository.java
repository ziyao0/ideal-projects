package com.ziyao.ideal.data.redis.core.repository;

import com.ziyao.ideal.core.Collections;
import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.data.redis.core.RedisAdapter;
import com.ziyao.ideal.data.redis.core.RepositoryInformation;
import org.springframework.data.redis.core.RedisCallback;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ziyao
 */
@SuppressWarnings("deprecation")
public class DefaultRedisHashRepository<K, V>
        extends AbstractRedisRepository<Object, Object> implements RedisHashRepository<K, V> {

    private final Class<K> keyType;
    private final Class<V> valueType;

    @SuppressWarnings("unchecked")
    public DefaultRedisHashRepository(RepositoryInformation repositoryInformation, RedisAdapter adapter) {
        super(adapter, repositoryInformation);
        this.keyType = (Class<K>) repositoryInformation.getIdType();
        this.valueType = (Class<V>) repositoryInformation.getJavaType();
    }

    @Override
    public Map<K, V> findById(Object id) {

        return adapter.execute(connection -> {

            byte[] rawKey = createKey(id);

            Map<byte[], byte[]> entries = connection.hGetAll(rawKey);

            if (Collections.isEmpty(entries)) {
                return Map.of();
            }
            Map<K, V> map = new LinkedHashMap<>(entries.size());
            for (Map.Entry<byte[], byte[]> entry : entries.entrySet()) {
                map.put(
                        this.conversionProvider.read(this.keyType, entry.getKey()),
                        this.conversionProvider.read(this.valueType, entry.getValue())
                );
            }
            return map;
        });
    }

    @Override
    public Optional<V> findByKey(Object id, Object key) {
        return adapter.execute(connection -> {

            byte[] rawKey = createKey(id);
            byte[] rawHashKey = this.conversionProvider.write(key);
            byte[] rawHashValue = connection.hGet(rawKey, rawHashKey);

            if (Strings.isEmpty(rawHashValue)) {
                return Optional.empty();
            }
            return Optional.ofNullable(this.conversionProvider.read(this.valueType, rawHashValue));
        });
    }

    @Override
    public Set<K> keys(Object id) {

        byte[] rawKey = createKey(id);

        return adapter.execute(connection -> {
            Set<byte[]> rawHashKeys = connection.hKeys(rawKey);
            if (Collections.isEmpty(rawHashKeys)) {
                return Set.of();
            }
            return rawHashKeys.stream()
                    .map(rawHashKey -> this.conversionProvider.read(this.keyType, rawHashKey))
                    .collect(Collectors.toSet());
        });
    }

    @Override
    public List<V> values(Object key) {

        byte[] rawKey = createKey(key);

        return adapter.execute(connection -> {
            List<byte[]> vals = connection.hVals(rawKey);
            if (Collections.isEmpty(vals)) {
                return List.of();
            }
            return vals.stream()
                    .map(valueType -> this.conversionProvider.read(this.valueType, valueType))
                    .collect(Collectors.toList());
        });
    }

    @Override
    public Long deleteByKeys(Object id, Object... keys) {

        byte[] rawKey = createKey(id);

        byte[][] rawKeys = new byte[keys.length][];

        for (int i = 0; i < keys.length; i++) {
            rawKeys[i] = conversionProvider.write(keys[i]);
        }

        return adapter.execute(connection -> connection.hDel(rawKey, rawKeys));
    }

    @Override
    public void save(Object id, K key, V value) {
        byte[] rawKey = createKey(id);
        byte[] rawHashKey = this.conversionProvider.write(key);
        byte[] rawHashValue = this.conversionProvider.write(value);
        adapter.execute(connection -> {
            connection.hSet(rawKey, rawHashKey, rawHashValue);
            if (expires(ttl)) {
                connection.expire(rawKey, ttl);
            }
            return null;
        });
    }

    @Override
    public void saveAll(Object id, Map<K, V> map) {
        Map<byte[], byte[]> rawMap = new LinkedHashMap<>(map.size());
        for (Map.Entry<K, V> entry : map.entrySet()) {
            rawMap.put(conversionProvider.write(entry.getKey()), conversionProvider.write(entry.getValue()));
        }
        byte[] rawKey = createKey(id);
        adapter.execute((RedisCallback<Void>) connection -> {
            connection.hMSet(rawKey, rawMap);
            if (expires(ttl)) {
                connection.expire(rawKey, ttl);
            }
            return null;
        });
    }
}
