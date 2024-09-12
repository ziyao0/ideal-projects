package com.ziyao.ideal.gateway.core.cache;

import com.ziyao.ideal.convert.ConversionProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * redis keyvalue 操作对象
 *
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Service
@RequiredArgsConstructor
@SuppressWarnings("deprecation")
public class KeyValueRedisOpsService implements RedisOpsService {

    final ConversionProvider conversionProvider = ConversionProvider.getInstance();
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public <T> Optional<T> findById(Object id, Class<T> type) {

        byte[] rawKey = createKey(id);

        T value = execute(connection -> {

            byte[] rawValue = connection.get(rawKey);

            return conversionProvider.read(type, rawValue);
        });
        return Optional.ofNullable(value);
    }

    @Override
    public <T> void save(Object id, T t) {

        execute((RedisCallback<Void>) connection -> {
            byte[] rawKey = createKey(id);
            byte[] rakValue = conversionProvider.write(t);
            connection.set(rawKey, rakValue);
            return null;
        });
    }

    @Override
    public <T> void save(Object id, T t, long ttl, TimeUnit unit) {
        execute((RedisCallback<Void>) connection -> {
            byte[] rawKey = createKey(id);
            byte[] rakValue = conversionProvider.write(t);
            connection.set(rawKey, rakValue, Expiration.from(ttl, unit), RedisStringCommands.SetOption.upsert());
            return null;
        });
    }

    @Override
    public void delete(Object id) {
        redisTemplate.execute((RedisCallback<Void>) connection -> {
            byte[] rawKey = createKey(id);
            connection.del(rawKey);
            return null;
        });
    }

    @Override
    public <T> T execute(RedisCallback<T> callback) {
        return redisTemplate.execute(callback);
    }


    protected byte[] createKey(Object id) {
        return conversionProvider.write(id);
    }

}

