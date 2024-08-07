package com.ziyao.ideal.uua.service.impl;

import com.ziyao.ideal.jpa.extension.service.impl.JapServiceImpl;
import com.ziyao.ideal.uua.domain.entity.UserLoginRestriction;
import com.ziyao.ideal.uua.repository.jpa.UserLoginRestrictionRepositoryJpa;
import com.ziyao.ideal.uua.service.UserLoginRestrictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登录限制表 服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class UserLoginRestrictionServiceImpl extends JapServiceImpl<
        UserLoginRestrictionRepositoryJpa, UserLoginRestriction, Integer> implements UserLoginRestrictionService {

    private final UserLoginRestrictionRepositoryJpa userLoginRestrictionRepositoryJpa;

}
