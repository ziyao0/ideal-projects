package com.ziyao.ideal.config.repos;

import com.ziyao.ideal.config.domain.entity.ConfigItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigItemRepositoryJpa extends JpaRepository<ConfigItem, Integer> {

}