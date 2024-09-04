package com.ziyao.ideal.config.repository.jpa;

import com.ziyao.ideal.config.domain.entity.ConfigProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 配置属性 持久化接口
 * </p>
 *
 * @author ziyao
 */
@Repository
public interface ConfigPropertyRepositoryJpa extends JpaRepository<ConfigProperty, Integer> {

    /**
     * 通过配置文件ID查询对应的配置属性
     *
     * @param configId 配置ID
     * @return 返回该文件的配置属性信息
     */
    List<ConfigProperty> findByConfigId(Integer configId);

    /**
     * 通过配置ID删除配置属性信息
     *
     * @param configId 配置ID
     */
    void deleteByConfigId(Integer configId);
}
