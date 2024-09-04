package com.ziyao.ideal.uaa.service.impl;

import com.ziyao.ideal.uaa.repository.jpa.RoleMenuRepositoryJpa;
import com.ziyao.ideal.jpa.extension.service.impl.JpaServiceImpl;
import com.ziyao.ideal.uaa.domain.entity.RoleMenu;
import com.ziyao.ideal.uaa.service.RoleMenuService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
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
        JpaServiceImpl< RoleMenuRepositoryJpa, RoleMenu,Integer> implements RoleMenuService {

    private final RoleMenuRepositoryJpa roleMenuRepositoryJpa;

}

