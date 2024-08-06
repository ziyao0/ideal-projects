package com.ziyao.ideal.config.repository.jpa;

import com.ziyao.ideal.config.domain.entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ziyao
 */
@Repository
public interface ConfigRepositoryJpa extends JpaRepository<Config, Integer> {

    Optional<Config> findByDataIdAndGroup(String dataId, String group);
}
