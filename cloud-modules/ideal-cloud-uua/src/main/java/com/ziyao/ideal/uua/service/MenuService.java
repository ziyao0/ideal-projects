package com.ziyao.ideal.uua.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ziyao.ideal.uua.domain.dto.MenuDTO;
import com.ziyao.ideal.uua.domain.entity.Menu;

/**
 * <p>
 * 菜单资源表 服务类
 * </p>
 *
 * @author zhangziyao
 */
public interface MenuService extends IService<Menu> {

    /**
     * 分页查询
     */
    Page<Menu> page(Page<Menu> page, MenuDTO menuDTO);
}
