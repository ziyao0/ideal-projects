package com.ziyao.ideal.config.repos;

import com.ziyao.ideal.config.domain.entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepositoryJpa extends JpaRepository<Config, Integer> {
}