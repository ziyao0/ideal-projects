package com.ziyao.ideal.config.service.impl;

import com.ziyao.ideal.config.domain.dto.ConfigPropertyDTO;
import com.ziyao.ideal.config.domain.entity.ConfigProperty;
import com.ziyao.ideal.config.repository.jpa.ConfigPropertyRepositoryJpa;
import com.ziyao.ideal.config.repository.jpa.ConfigRepositoryJpa;
import com.ziyao.ideal.config.service.ConfigPropertyService;
import com.ziyao.ideal.jpa.extension.service.impl.JpaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 配置属性 服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class ConfigPropertyServiceImpl extends
        JpaServiceImpl<ConfigPropertyRepositoryJpa, ConfigProperty, Integer> implements ConfigPropertyService {

    private final ConfigPropertyRepositoryJpa configPropertyRepositoryJpa;
    private final ConfigRepositoryJpa configRepositoryJpa;

    /**
     * 分页查询 配置属性
     *
     * @param configPropertyDTO 查询对象
     * @param pageable          分页对象
     * @return 返回分页对象
     */
    @Override
    public Page<ConfigProperty> page(ConfigPropertyDTO configPropertyDTO, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // Use CONTAINING for partial matches
        Example<ConfigProperty> example = Example.of(configPropertyDTO.toEntity(), matcher);
        return configPropertyRepositoryJpa.findAll(example, pageable);
    }

    @Override
    public void deleteByConfigId(Integer configId) {
        configPropertyRepositoryJpa.deleteByConfigId(configId);
    }
}