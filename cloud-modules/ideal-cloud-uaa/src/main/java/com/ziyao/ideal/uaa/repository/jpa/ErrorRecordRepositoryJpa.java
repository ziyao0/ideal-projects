package com.ziyao.ideal.uaa.repository.jpa;

import com.ziyao.ideal.uaa.domain.entity.ErrorRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 异常信息记录表 持久化接口
 * </p>
 *
 * @author ziyao
 */
@Repository
public interface ErrorRecordRepositoryJpa extends JpaRepository<ErrorRecord, Long> {
}
