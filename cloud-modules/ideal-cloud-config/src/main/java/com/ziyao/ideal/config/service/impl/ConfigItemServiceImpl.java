package com.ziyao.ideal.config.service.impl;

import com.ziyao.ideal.config.domain.entity.ConfigItem;
import com.ziyao.ideal.config.repository.jpa.ConfigItemRepositoryJpa;
import com.ziyao.ideal.config.service.ConfigItemService;
import com.ziyao.ideal.core.Collections;
import com.ziyao.ideal.jpa.extension.service.impl.JapServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class ConfigItemServiceImpl
        extends JapServiceImpl<ConfigItemRepositoryJpa, ConfigItem, Integer> implements ConfigItemService {

    private final ConfigItemRepositoryJpa configItemRepositoryJpa;

    @Override
    public List<ConfigItem> findByConfigId(Integer configId) {
        List<ConfigItem> configItems = configItemRepositoryJpa.findByConfigId(configId);
        if (Collections.isEmpty(configItems)) {
            return Lists.newArrayList();
        }
        return configItems;
    }

    @Override
    public void deleteByConfigId(Integer configId) {
        configItemRepositoryJpa.deleteByConfigId(configId);
    }
}
