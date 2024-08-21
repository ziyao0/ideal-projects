package com.ziyao.ideal.uua.service.impl;

import com.ziyao.ideal.uua.repository.jpa.MenuRepositoryJpa;
import com.ziyao.ideal.jpa.extension.service.impl.JapServiceImpl;
import com.ziyao.ideal.uua.domain.entity.Menu;
import com.ziyao.ideal.uua.service.MenuService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
/**
* <p>
    * 菜单资源表 服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends
    JapServiceImpl< MenuRepositoryJpa, Menu,Integer> implements MenuService {

    private final MenuRepositoryJpa menuRepositoryJpa;

}
