package com.ziyao.harbor.usercenter.repository.jpa;

import com.ziyao.harbor.usercenter.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ziyao
 */
@Repository
public interface ApplicationRepositoryJpa extends JpaRepository<Application, Long> {
}
