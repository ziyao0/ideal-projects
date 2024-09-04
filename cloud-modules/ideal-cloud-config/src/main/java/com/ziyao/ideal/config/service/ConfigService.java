package com.ziyao.ideal.config.service;

import com.ziyao.ideal.config.domain.dto.ConfigDTO;
import com.ziyao.ideal.config.domain.entity.Config;
import com.ziyao.ideal.config.domain.entity.Configuration;
import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.jpa.extension.service.JpaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 *配置表 服务类
 * </p>
 *
 * @author ziyao
 */
public interface ConfigService extends JpaService<Config,Integer> {

    Optional<Config> findByDataIdAndGroup(@NonNull String dataId, @NonNull String group);

    void deleteByDataIdAndGroup(@NonNull String dataId, @NonNull String group);

    List<Configuration> findConfigurationByDataIdAndGroup(@NonNull String dataId, @NonNull String group);

    List<Configuration> findConfigurationBy();
    /**
     * 分页查询 配置表
     *
     * @param configDTO 查询对象
     * @param pageable 分页对象
     * @return 返回分页对象
     */
    Page<Config> page(ConfigDTO configDTO, Pageable pageable);
}
