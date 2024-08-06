package com.ziyao.ideal.config.service;

import com.ziyao.ideal.config.domain.entity.ConfigItem;
import com.ziyao.ideal.jpa.extension.service.JapService;

import java.util.List;

/**
* <p>
    *  服务类
    * </p>
*
* @author ziyao
*/
public interface ConfigItemService extends JapService<ConfigItem,Integer> {

    List<ConfigItem> findByConfigId(Integer id);
}
