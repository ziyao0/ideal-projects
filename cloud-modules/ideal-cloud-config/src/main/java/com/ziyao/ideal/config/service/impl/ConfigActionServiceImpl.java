package com.ziyao.ideal.config.service.impl;

import com.ziyao.ideal.config.core.ConfigProcessor;
import com.ziyao.ideal.config.core.ConfigValueType;
import com.ziyao.ideal.config.core.YamlConfigProcessor;
import com.ziyao.ideal.config.domain.entity.Config;
import com.ziyao.ideal.config.domain.entity.ConfigItem;
import com.ziyao.ideal.config.manager.ConfigManager;
import com.ziyao.ideal.config.service.ConfigActionService;
import com.ziyao.ideal.config.service.ConfigItemService;
import com.ziyao.ideal.config.service.ConfigService;
import com.ziyao.ideal.core.Collections;
import com.ziyao.ideal.crypto.Property;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Service
@RequiredArgsConstructor
public class ConfigActionServiceImpl implements ConfigActionService {

    private final ConfigManager configManager;
    private final ConfigService configService;
    private final ConfigItemService configItemService;

    private final ConfigProcessor<List<Property>> configProcessor = new YamlConfigProcessor();


    @Override
    public void publishConfig(String dataId, String group) {

        // 如果存在 进行配置推送
        configService.findByDataIdAndGroup(dataId, group)
                .ifPresent(config -> publishConfig(config, configItemService.findByConfigId(config.getId())));
    }

    @Override
    public void publishConfig(String dataId, String group, String configType, List<Property> properties) {
        // 如果配置信息为空，则跳过发布
        if (Collections.isEmpty(properties)) {
            return;
        }
        //  转换配置信息
        String configContent = configProcessor.resolve(properties);

        doPublishConfig(dataId, group, configContent, configType);
    }

    @Override
    public boolean removeConfig(String dataId, String group) {
        configService.findByDataIdAndGroup(dataId, group).ifPresent(config -> {
            configItemService.deleteByConfigId(config.getId());
            configService.deleteById(config.getId());
        });
        return configManager.removeConfig(dataId, group);
    }

    @Override
    public String getConfig(String dataId, String group) {
        return configManager.getConfig(dataId, group);
    }

    @Override
    public void publishAllConfig() {

        List<Config> configs = configService.findAll();
        List<ConfigItem> configItems = configItemService.findAll();

        Map<Integer, List<ConfigItem>> configItemByConfigId =
                configItems.stream().collect(Collectors.groupingBy(ConfigItem::getConfigId));
        // 推送配置
        configs.forEach(config -> publishConfig(config, configItemByConfigId.get(config.getId())));
    }

    private void doPublishConfig(String dataId, String group, String content, String configType) {
        configManager.publishing(dataId, group, content, configType);
    }

    private void publishConfig(Config config, List<ConfigItem> configItems) {
        List<Property> properties = configItems.stream().map(ci -> {
            String configValue = ci.getConfigValue();
            String valueType = ci.getValueType();
            try {
                Object value = ConfigValueType.of(valueType).getObject(configValue);
                return new Property(ci.getConfigKey(), value);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }).toList();
        // 推送配置
        if (Collections.nonNull(properties)) {
            publishConfig(config.getDataId(), config.getGroup(), config.getConfigType(), properties);
        }
    }
}
