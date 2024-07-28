package com.ziyao.ideal.config.services;

import com.ziyao.ideal.config.domain.dto.ConfigItemDTO;
import com.ziyao.ideal.config.domain.entity.ConfigItem;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public interface ConfigItemService {


    ConfigItem getConfigItemById(Integer id);

    void saveConfigItem(ConfigItemDTO configItem);

    void updateConfigItem(ConfigItemDTO configItem);

    void deleteConfigItem(Integer id);
}
