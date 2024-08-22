package com.ziyao.ideal.uaa.repository.jpa;

import com.ziyao.ideal.uaa.domain.entity.LoginConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginConfigRepositoryJpa extends JpaRepository<LoginConfig, Long> {


    Optional<LoginConfig> findByLoginMethod(String loginMethod);
}