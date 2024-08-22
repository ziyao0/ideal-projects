package com.ziyao.ideal.uaa.service.impl;

import com.ziyao.ideal.uaa.repository.jpa.UserLoginRestrictionRepositoryJpa;
import com.ziyao.ideal.jpa.extension.service.impl.JapServiceImpl;
import com.ziyao.ideal.uaa.domain.entity.UserLoginRestriction;
import com.ziyao.ideal.uaa.service.UserLoginRestrictionService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
/**
* <p>
    * 用户登录限制表 服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class UserLoginRestrictionServiceImpl extends
    JapServiceImpl< UserLoginRestrictionRepositoryJpa, UserLoginRestriction,Integer> implements UserLoginRestrictionService {

    private final UserLoginRestrictionRepositoryJpa userLoginRestrictionRepositoryJpa;

}
