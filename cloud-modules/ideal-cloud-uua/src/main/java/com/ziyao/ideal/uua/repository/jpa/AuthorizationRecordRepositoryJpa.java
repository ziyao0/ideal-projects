package com.ziyao.ideal.uua.repository.jpa;

import com.ziyao.ideal.uua.domain.entity.AuthorizationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ziyao
 */
@Repository
public interface AuthorizationRecordRepositoryJpa extends JpaRepository<AuthorizationRecord, Integer> {
}
