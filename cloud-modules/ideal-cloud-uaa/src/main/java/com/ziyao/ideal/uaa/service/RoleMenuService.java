package com.ziyao.ideal.uaa.service;

import com.ziyao.ideal.uaa.domain.dto.RoleMenuDTO;
import com.ziyao.ideal.uaa.domain.entity.RoleMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * <p>
 * 角色菜单表 服务类
 * </p>
 *
 * @author ziyao
 */
public interface RoleMenuService {

    /**
     * 保存 角色菜单表
     *
     * @param roleMenuDTO 待存储对象
     */
    void save(RoleMenuDTO roleMenuDTO);

    /**
     * 通过主键修改 角色菜单表
     *
     * @param roleMenuDTO 待修改对象
     */
    void updateById(RoleMenuDTO roleMenuDTO);

    /**
     * 通过主键删除 角色菜单表
     *
     * @param id 主键id
     */
    void deleteById(Integer id);

    /**
     * 分页查询 角色菜单表
     *
     * @param roleMenuDTO 查询对象
     * @param pageable    分页对象
     * @return 返回分页对象
     */
    Page<RoleMenu> page(RoleMenuDTO roleMenuDTO, Pageable pageable);
}
