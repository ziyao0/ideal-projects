package com.ziyao.ideal.uua.service.impl;

import com.ziyao.ideal.jpa.extension.service.impl.JapServiceImpl;
import com.ziyao.ideal.uua.domain.entity.RoleMenu;
import com.ziyao.ideal.uua.repository.jpa.RoleMenuRepositoryJpa;
import com.ziyao.ideal.uua.service.RoleMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class RoleMenuServiceImpl extends
        JapServiceImpl<RoleMenuRepositoryJpa, RoleMenu, Integer> implements RoleMenuService {

    private final RoleMenuRepositoryJpa roleMenuRepositoryJpa;

}

