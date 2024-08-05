package com.ziyao.ideal.config.service.impl;

import com.ziyao.ideal.config.repository.jpa.ConfigItemRepositoryJpa;
import com.ziyao.ideal.jpa.extension.service.impl.JapServiceImpl;
import com.ziyao.ideal.config.domain.entity.ConfigItem;
import com.ziyao.ideal.config.service.ConfigItemService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
/**
* <p>
    *  服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class ConfigItemServiceImpl extends JapServiceImpl< ConfigItemRepositoryJpa, ConfigItem,Integer> implements ConfigItemService {

    private final ConfigItemRepositoryJpa configItemRepositoryJpa;

}
