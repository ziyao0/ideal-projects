package com.ziyao.ideal.config.services.config;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.ziyao.ideal.config.core.ConfigType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Service
@RequiredArgsConstructor
public class AliNacosConfigManager implements ConfigManager {

    private final NacosConfigManager nacosConfigManager;

    @Override
    public boolean publishing(String dataId, String groupId, String content, ConfigType configType) {

        ConfigService configService = nacosConfigManager.getConfigService();

        try {
            return configService.publishConfig(dataId, groupId, content, configType.type());
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getConfig(String dataId, String groupId) {
        try {
            return nacosConfigManager.getConfigService().getConfig(dataId, groupId, 10000);
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removeConfig(String dataId, String groupId) {
        try {
            return nacosConfigManager.getConfigService().removeConfig(dataId, groupId);
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }
}
