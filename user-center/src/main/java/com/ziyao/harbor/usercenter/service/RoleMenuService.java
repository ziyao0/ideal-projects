package com.ziyao.harbor.usercenter.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ziyao.harbor.usercenter.dto.RoleMenuDTO;
import com.ziyao.harbor.usercenter.entity.RoleMenu;

/**
 * <p>
 * 角色菜单表 服务类
 * </p>
 *
 * @author zhangziyao
 */
public interface RoleMenuService extends IService<RoleMenu> {

    /**
     * 分页查询
     */
    Page<RoleMenu> page(Page<RoleMenu> page, RoleMenuDTO roleMenuDTO);
}
