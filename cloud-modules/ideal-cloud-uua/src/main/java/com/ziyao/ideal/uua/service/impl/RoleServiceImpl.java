package com.ziyao.ideal.uua.service.impl;

import com.ziyao.ideal.jpa.extension.service.impl.JapServiceImpl;
import com.ziyao.ideal.uua.domain.entity.Role;
import com.ziyao.ideal.uua.repository.jpa.RoleRepositoryJpa;
import com.ziyao.ideal.uua.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends
        JapServiceImpl<RoleRepositoryJpa, Role, Integer> implements RoleService {

    private final RoleRepositoryJpa roleRepositoryJpa;

}
