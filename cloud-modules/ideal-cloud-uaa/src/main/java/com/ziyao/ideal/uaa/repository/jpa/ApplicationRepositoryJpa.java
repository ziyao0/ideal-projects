package com.ziyao.ideal.uaa.repository.jpa;

import com.ziyao.ideal.uaa.domain.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ziyao
 */
@Repository
public interface ApplicationRepositoryJpa extends JpaRepository<Application, Integer> {
}
