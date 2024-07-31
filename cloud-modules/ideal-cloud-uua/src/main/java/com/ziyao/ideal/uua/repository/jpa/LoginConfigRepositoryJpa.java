package com.ziyao.ideal.uua.repository.jpa;

import com.ziyao.ideal.uua.domain.entity.LoginConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginConfigRepositoryJpa extends JpaRepository<LoginConfig, Long> {


    Optional<LoginConfig> findByLoginMethod(String loginMethod);
}