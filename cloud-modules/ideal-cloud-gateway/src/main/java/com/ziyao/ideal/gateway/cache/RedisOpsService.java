package com.ziyao.ideal.gateway.cache;

import org.springframework.data.redis.core.RedisCallback;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public interface RedisOpsService {

    /**
     * 通过键获取实例对象
     *
     * @param id    唯一标识
     * @param clazz 对象class
     * @param <T>   获取的对象类型
     * @return 返回到
     */
    <T> Optional<T> findById(Object id, Class<T> clazz);

    /**
     * 保存数据
     *
     * @param id  ID
     * @param t   data
     * @param <T> 对象类型
     */
    <T> void save(Object id, T t);

    /**
     * 保存数据
     * <p>
     * 可以设置过期时间
     *
     * @param id   ID
     * @param t    data
     * @param ttl  过期时间
     * @param unit 时间单位
     * @param <T>  对象类型
     */
    <T> void save(Object id, T t, long ttl, TimeUnit unit);

    /**
     * 通过ID删除
     *
     * @param id redis key
     */
    void delete(Object id);

    /**
     * redis执行器
     *
     * @param callback 回调函数
     * @param <T>      Bean Type
     * @return 返回执行结果
     */
    <T> T execute(RedisCallback<T> callback);

}
