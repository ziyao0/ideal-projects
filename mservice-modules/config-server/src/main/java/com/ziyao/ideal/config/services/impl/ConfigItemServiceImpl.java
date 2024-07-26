package com.ziyao.ideal.config.services.impl;

import com.ziyao.ideal.config.domain.dto.ConfigItemDTO;
import com.ziyao.ideal.config.domain.entity.ConfigItem;
import com.ziyao.ideal.config.repos.ConfigItemRepositoryJpa;
import com.ziyao.ideal.config.services.ConfigItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">blog.zziyao.cn</a>
 */
@Service
@RequiredArgsConstructor
public class ConfigItemServiceImpl implements ConfigItemService {

    private final ConfigItemRepositoryJpa configItemRepositoryJpa;

    @Override
    public ConfigItem getConfigItemById(Integer id) {
        return configItemRepositoryJpa.findById(id).orElse(null);
    }

    @Override
    public void saveConfigItem(ConfigItemDTO configItem) {
        configItemRepositoryJpa.save(configItem.toConfigItem());
    }

    @Override
    public void updateConfigItem(ConfigItemDTO configItem) {
        configItemRepositoryJpa.save(configItem.toConfigItem());
    }

    @Override
    public void deleteConfigItem(Integer id) {
        configItemRepositoryJpa.deleteById(id);
    }
}
