package com.ziyao.ideal.config.manager;

import com.ziyao.ideal.config.core.ConfigType;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class ApolloConfigManager implements ConfigManager {
    @Override
    public boolean publishing(String dataId, String groupId, String content, ConfigType configType) {
        return false;
    }

    @Override
    public String getConfig(String dataId, String groupId) {
        return "";
    }

    @Override
    public boolean removeConfig(String dataId, String groupId) {
        return false;
    }
}
