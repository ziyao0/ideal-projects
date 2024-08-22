package com.ziyao.ideal.uaa.service.impl;

import com.ziyao.ideal.uaa.repository.jpa.RoleRepositoryJpa;
import com.ziyao.ideal.jpa.extension.service.impl.JapServiceImpl;
import com.ziyao.ideal.uaa.domain.entity.Role;
import com.ziyao.ideal.uaa.service.RoleService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
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
    JapServiceImpl< RoleRepositoryJpa, Role,Integer> implements RoleService {

    private final RoleRepositoryJpa roleRepositoryJpa;

}
