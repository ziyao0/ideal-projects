package com.ziyao.ideal.uua.service.impl;

import com.ziyao.ideal.uua.repository.jpa.LoginRecordRepositoryJpa;
import com.ziyao.ideal.jpa.extension.service.impl.JapServiceImpl;
import com.ziyao.ideal.uua.domain.entity.LoginRecord;
import com.ziyao.ideal.uua.service.LoginRecordService;
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
    JapServiceImpl< LoginRecordRepositoryJpa, LoginRecord,Integer> implements LoginRecordService {

    private final LoginRecordRepositoryJpa loginRecordRepositoryJpa;

}
