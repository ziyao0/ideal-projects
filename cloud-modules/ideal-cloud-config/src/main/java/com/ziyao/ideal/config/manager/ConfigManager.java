package com.ziyao.ideal.config.manager;

import com.ziyao.ideal.config.core.ConfigType;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public interface ConfigManager {

    /**
     * 配置推送
     *
     * @param dataId     配置id
     * @param groupId    组id
     * @param content    配置内容
     * @param configType 配置类型
     */
    boolean publishing(String dataId, String groupId, String content, ConfigType configType);

    /**
     * 获取配置信息
     *
     * @param dataId  配置ID
     * @param groupId 组ID
     * @return 返回配置信息
     */
    String getConfig(String dataId, String groupId);

    /**
     * 通过配置ID和组ID删除配置信息
     *
     * @param dataId  配置ID
     * @param groupId 组ID
     * @return <code>true</code> 删除成功
     */
    boolean removeConfig(String dataId, String groupId);
}
