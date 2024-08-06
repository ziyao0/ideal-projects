package com.ziyao.ideal.config.repository.jpa;

import com.ziyao.ideal.config.domain.entity.ConfigItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ziyao
 */
@Repository
public interface ConfigItemRepositoryJpa extends JpaRepository<ConfigItem, Integer> {

    List<ConfigItem> findByConfigId(Integer configId);

    void deleteByConfigId(Integer configId);
}
