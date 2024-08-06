package com.ziyao.ideal.config.service;

import com.ziyao.ideal.config.domain.entity.Config;
import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.jpa.extension.service.JapService;

import java.util.Optional;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ziyao
 */
public interface ConfigService extends JapService<Config, Integer> {


    Optional<Config> findByDataIdAndGroup(@NonNull String dataId, @NonNull String group);


}
