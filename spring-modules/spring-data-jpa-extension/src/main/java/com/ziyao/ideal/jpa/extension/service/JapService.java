package com.ziyao.ideal.jpa.extension.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public interface JapService<T, ID> {

    <S extends T> S save(S entity);

    Optional<T> findById(ID id);

    boolean existsById(ID id);

    long count();

    void deleteById(ID id);

    void delete(T entity);

    void deleteAllById(Iterable<? extends ID> ids);

    void deleteAll(Iterable<? extends T> entities);

    void deleteAll();

    <S extends T> List<S> saveBatch(List<S> entities, int batchSize);

    default <S extends T> List<S> saveBatch(List<S> entities) {
        return saveBatch(entities, 500);
    }


    List<T> findAll();

    List<T> findAllById(Iterable<ID> ids);

    List<T> findAll(Sort sort);

    Page<T> findAll(Pageable pageable);

    <S extends T> Page<S> list(S entity, Pageable pageable);

}
