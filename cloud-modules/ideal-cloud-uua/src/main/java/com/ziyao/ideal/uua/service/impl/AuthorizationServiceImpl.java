package com.ziyao.ideal.uua.service.impl;

import com.ziyao.ideal.uua.repository.jpa.AuthorizationRepositoryJpa;
import com.ziyao.ideal.jpa.extension.service.impl.JapServiceImpl;
import com.ziyao.ideal.uua.domain.entity.Authorization;
import com.ziyao.ideal.uua.service.AuthorizationService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
/**
* <p>
    *  服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl extends
    JapServiceImpl< AuthorizationRepositoryJpa, Authorization,Integer> implements AuthorizationService {

    private final AuthorizationRepositoryJpa authorizationRepositoryJpa;

}
