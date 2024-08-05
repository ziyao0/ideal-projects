package com.ziyao.ideal.config.repository.jpa;

import com.ziyao.ideal.config.domain.entity.ConfigItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
*
* @author ziyao
*/
@Repository
public interface ConfigItemRepositoryJpa extends JpaRepository<ConfigItem, Integer> {
}
