package com.ziyao.ideal.uaa.service.impl;

import com.ziyao.ideal.uaa.repository.jpa.LoginRecordRepositoryJpa;
import com.ziyao.ideal.jpa.extension.service.impl.JpaServiceImpl;
import com.ziyao.ideal.uaa.domain.entity.LoginRecord;
import com.ziyao.ideal.uaa.service.LoginRecordService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
/**
* <p>
    * 登录记录表 服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class LoginRecordServiceImpl extends
        JpaServiceImpl< LoginRecordRepositoryJpa, LoginRecord,Integer> implements LoginRecordService {

    private final LoginRecordRepositoryJpa loginRecordRepositoryJpa;

}
