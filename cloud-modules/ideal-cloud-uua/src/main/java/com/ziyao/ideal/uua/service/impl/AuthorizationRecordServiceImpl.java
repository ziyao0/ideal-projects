package com.ziyao.ideal.uua.service.impl;

import com.ziyao.ideal.jpa.extension.service.impl.JapServiceImpl;
import com.ziyao.ideal.uua.domain.entity.AuthorizationRecord;
import com.ziyao.ideal.uua.repository.jpa.AuthorizationRecordRepositoryJpa;
import com.ziyao.ideal.uua.service.AuthorizationRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        JapServiceImpl<AuthorizationRecordRepositoryJpa, AuthorizationRecord, Integer> implements AuthorizationRecordService {

    private final AuthorizationRecordRepositoryJpa authorizationRecordRepositoryJpa;

}
