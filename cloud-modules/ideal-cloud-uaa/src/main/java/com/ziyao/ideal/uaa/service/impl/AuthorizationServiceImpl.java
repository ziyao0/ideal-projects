package com.ziyao.ideal.uaa.service.impl;

import com.ziyao.ideal.uaa.repository.jpa.AuthorizationRepositoryJpa;
import com.ziyao.ideal.jpa.extension.service.impl.JapServiceImpl;
import com.ziyao.ideal.uaa.domain.entity.Authorization;
import com.ziyao.ideal.uaa.service.AuthorizationService;
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
