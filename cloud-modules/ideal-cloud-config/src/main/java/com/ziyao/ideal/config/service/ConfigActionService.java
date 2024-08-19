package com.ziyao.ideal.config.service;

import com.ziyao.ideal.crypto.Property;

import java.util.List;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public interface ConfigActionService {


    /**
     * 发布配置
     *
     * @param dataId 配置ID
     * @param group  配置组
     */
    void publishConfig(String dataId, String group);

    /**
     * 发布属性到指定配置文件中
     *
     * @param dataId     配置ID
     * @param group      组
     * @param properties 配置信息
     */
    void publishConfig(String dataId, String group, String configType, List<Property> properties);

    /**
     * 通过配置id和配置组删除配置
     *
     * @param dataId 配置ID
     * @param group  配置组
     */
    boolean removeConfig(String dataId, String group);

    /**
     * 获取指定配置
     *
     * @param dataId 配置ID
     * @param group  配置组
     * @return 配置内容
     */
    String getConfig(String dataId, String group);

    /**
     * 发布所有配置
     */
    void publishAllConfig();

    /**
     * 发布指定配置信息到指定的配置文件中
     *
     * @param dataId        数据ID
     * @param group         组
     * @param propertyKey   配置属性key
     * @param propertyValue 配置属性value
     */
    default void publishConfig(String dataId, String group, String configType, String propertyKey, Object propertyValue) {
        this.publishConfig(dataId, group, configType, Lists.newArrayList(new Property(propertyKey, propertyValue)));
    }
}
