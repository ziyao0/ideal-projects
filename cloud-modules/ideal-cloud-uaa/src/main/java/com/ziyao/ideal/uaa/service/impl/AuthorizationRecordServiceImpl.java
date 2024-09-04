package com.ziyao.ideal.uaa.service.impl;

import com.ziyao.ideal.uaa.repository.jpa.AuthorizationRecordRepositoryJpa;
import com.ziyao.ideal.jpa.extension.service.impl.JpaServiceImpl;
import com.ziyao.ideal.uaa.domain.entity.AuthorizationRecord;
import com.ziyao.ideal.uaa.service.AuthorizationRecordService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
/**
* <p>
    * 授权记录表 服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class AuthorizationRecordServiceImpl extends
        JpaServiceImpl< AuthorizationRecordRepositoryJpa, AuthorizationRecord,Integer> implements AuthorizationRecordService {

    private final AuthorizationRecordRepositoryJpa authorizationRecordRepositoryJpa;

}
