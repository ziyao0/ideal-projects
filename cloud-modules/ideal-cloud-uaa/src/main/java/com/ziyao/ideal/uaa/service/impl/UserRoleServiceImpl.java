package com.ziyao.ideal.uaa.service.impl;

import com.ziyao.ideal.uaa.repository.jpa.UserRoleRepositoryJpa;
import com.ziyao.ideal.jpa.extension.service.impl.JapServiceImpl;
import com.ziyao.ideal.uaa.domain.entity.UserRole;
import com.ziyao.ideal.uaa.service.UserRoleService;
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
public class UserRoleServiceImpl extends
    JapServiceImpl< UserRoleRepositoryJpa, UserRole,Integer> implements UserRoleService {

    private final UserRoleRepositoryJpa userRoleRepositoryJpa;

}

