package com.ziyao.ideal.config.service.impl;

import com.ziyao.ideal.config.core.ConfigProcessor;
import com.ziyao.ideal.config.core.YamlConfigProcessor;
import com.ziyao.ideal.config.manager.ConfigManager;
import com.ziyao.ideal.config.service.ConfigActionService;
import com.ziyao.ideal.config.service.ConfigItemService;
import com.ziyao.ideal.config.service.ConfigService;
import com.ziyao.ideal.crypto.Property;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

        String content = "";



    }

    @Override
    public void publishConfig(String dataId, String group, List<Property> properties) {

    }

    @Override
    public void deleteConfig(String dataId, String group) {

    }

    @Override
    public String getConfig(String dataId, String group) {
        return "";
    }

    @Override
    public void publishAllConfig() {

    }

    private void  doPublishConfig(String dataId, String group,String content) {
        configManager.publishing(dataId,)
    }
}
