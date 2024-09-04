package com.ziyao.ideal.config.service;

import com.ziyao.ideal.config.domain.dto.ConfigPropertyDTO;
import com.ziyao.ideal.config.domain.entity.ConfigProperty;
import com.ziyao.ideal.jpa.extension.service.JpaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * <p>
 *配置属性 服务类
 * </p>
 *
 * @author ziyao
 */
public interface ConfigPropertyService extends JpaService<ConfigProperty,Integer> {

    /**
     * 分页查询 配置属性
     *
     * @param configPropertyDTO 查询对象
     * @param pageable 分页对象
     * @return 返回分页对象
     */
    Page<ConfigProperty> page(ConfigPropertyDTO configPropertyDTO, Pageable pageable);

    void deleteByConfigId(Integer id);
}
