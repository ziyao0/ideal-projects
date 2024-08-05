package com.ziyao.ideal.config.service.impl;

import com.ziyao.ideal.config.repository.jpa.ConfigRepositoryJpa;
import com.ziyao.ideal.jpa.extension.service.impl.JapServiceImpl;
import com.ziyao.ideal.config.domain.entity.Config;
import com.ziyao.ideal.config.service.ConfigService;
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
public class ConfigServiceImpl extends JapServiceImpl< ConfigRepositoryJpa, Config,Integer> implements ConfigService {

    private final ConfigRepositoryJpa configRepositoryJpa;

}
