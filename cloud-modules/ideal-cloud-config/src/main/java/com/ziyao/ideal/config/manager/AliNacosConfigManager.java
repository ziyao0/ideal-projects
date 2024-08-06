package com.ziyao.ideal.config.manager;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
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
    public boolean publishing(String dataId, String group, String content, String configType) {

        ConfigService configService = nacosConfigManager.getConfigService();

        try {
            return configService.publishConfig(dataId, group, content, configType);
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getConfig(String dataId, String group) {
        try {
            return nacosConfigManager.getConfigService().getConfig(dataId, group, 10000);
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removeConfig(String dataId, String group) {
        try {
            return nacosConfigManager.getConfigService().removeConfig(dataId, group);
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }
}
