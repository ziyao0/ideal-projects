package com.ziyao.ideal.jpa.extension.service.impl;

import com.ziyao.ideal.jpa.extension.service.JapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public abstract class JapServiceImpl<JPA extends JpaRepository<T, ID>, T, ID> implements JapService<T, ID> {

    @Autowired
    private JPA repositoryJpa;


    @Override
    @Transactional(rollbackFor = Throwable.class)
    public <S extends T> S save(S entity) {
        return repositoryJpa.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<T> findById(ID id) {
        return repositoryJpa.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(ID id) {
        return repositoryJpa.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repositoryJpa.count();
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void deleteById(ID id) {
        repositoryJpa.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void delete(T entity) {
        repositoryJpa.delete(entity);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void deleteAllById(Iterable<? extends ID> ids) {
        repositoryJpa.deleteAllById(ids);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void deleteAll(Iterable<? extends T> entities) {
        repositoryJpa.deleteAll(entities);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void deleteAll() {
        repositoryJpa.deleteAll();
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        return repositoryJpa.saveAll(entities);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return repositoryJpa.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAllById(Iterable<ID> ids) {
        return repositoryJpa.findAllById(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll(Sort sort) {
        return repositoryJpa.findAll(sort);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<T> findAll(Pageable pageable) {
        return repositoryJpa.findAll(pageable);
    }

    @Override
    public <S extends T> Page<S> searchSimilar(S entity, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // Use CONTAINING for partial matches
        Example<S> example = Example.of(entity, matcher);
        return repositoryJpa.findAll(example, pageable);
    }
}
