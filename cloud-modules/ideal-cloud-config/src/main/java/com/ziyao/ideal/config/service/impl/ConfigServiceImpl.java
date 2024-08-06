package com.ziyao.ideal.config.service.impl;

import com.ziyao.ideal.config.domain.entity.Config;
import com.ziyao.ideal.config.repository.jpa.ConfigRepositoryJpa;
import com.ziyao.ideal.config.service.ConfigService;
import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.jpa.extension.service.impl.JapServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class ConfigServiceImpl extends JapServiceImpl<ConfigRepositoryJpa, Config, Integer> implements ConfigService {

    private final ConfigRepositoryJpa configRepositoryJpa;

    @Override
    public Optional<Config> findByDataIdAndGroup(@NonNull String dataId, @NonNull String group) {
        return configRepositoryJpa.findByDataIdAndGroup(dataId, group);
    }
}
