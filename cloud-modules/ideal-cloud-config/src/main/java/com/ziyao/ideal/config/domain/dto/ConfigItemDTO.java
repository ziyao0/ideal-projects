package com.ziyao.ideal.config.domain.dto;

import com.ziyao.ideal.config.domain.entity.ConfigItem;

import java.io.Serializable;

/**
 * DTO for {@link ConfigItem}
 */

public record ConfigItemDTO(Integer id, Integer configId, String configKey, String configValue, String valueType,
                            String configLevel, String description) implements Serializable {




    public ConfigItem toConfigItem() {
        ConfigItem configItem = new ConfigItem();
        configItem.setId(id);
        configItem.setConfigId(configId);
        configItem.setConfigKey(configKey);
        configItem.setConfigValue(configValue);
        configItem.setValueType(valueType);
        configItem.setConfigLevel(configLevel);
        configItem.setDescription(description);
        return configItem;
    }
}