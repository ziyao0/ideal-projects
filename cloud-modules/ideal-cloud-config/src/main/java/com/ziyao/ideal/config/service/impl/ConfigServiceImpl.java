package com.ziyao.ideal.config.service.impl;

import com.ziyao.ideal.config.domain.dto.ConfigDTO;
import com.ziyao.ideal.config.domain.entity.Config;
import com.ziyao.ideal.config.domain.entity.Configuration;
import com.ziyao.ideal.config.repository.jpa.ConfigRepositoryJpa;
import com.ziyao.ideal.config.service.ConfigService;
import com.ziyao.ideal.jpa.extension.service.impl.JpaServiceImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 配置表 服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class ConfigServiceImpl extends
        JpaServiceImpl<ConfigRepositoryJpa, Config, Integer> implements ConfigService {

    private final ConfigRepositoryJpa configRepositoryJpa;
    @Override
    public Optional<Config> findByDataIdAndGroup(@NonNull String dataId, @NonNull String group) {
        return configRepositoryJpa.findByDataIdAndGroup(dataId, group);
    }

    @Override
    public void deleteByDataIdAndGroup(@NonNull String dataId, @NonNull String group) {
        configRepositoryJpa.deleteByDataIdAndGroup(dataId, group);
    }

    @Override
    public List<Configuration> findConfigurationByDataIdAndGroup(@NonNull String dataId, @NonNull String group) {
        return configRepositoryJpa.findConfigurationByDataIdAndGroup(dataId, group);
    }

    @Override
    public List<Configuration> findConfigurationBy() {
        return configRepositoryJpa.findConfiguration();
    }
    /**
     * 分页查询 配置表
     *
     * @param configDTO 查询对象
     * @param pageable  分页对象
     * @return 返回分页对象
     */
    @Override
    public Page<Config> page(ConfigDTO configDTO, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
//                .withMatcher("configType", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase()) // 用户名使用模糊查询，忽略大小写
                .withMatcher("dataId", ExampleMatcher.GenericPropertyMatchers.exact()) // 年龄精确匹配
                ; // Use CONTAINING for partial matches
        Example<Config> example = Example.of(configDTO.toEntity(), matcher);
        return configRepositoryJpa.findAll(example, pageable);
    }
}