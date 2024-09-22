package com.ziyao.ideal.uaa.service;

import com.ziyao.ideal.uaa.domain.dto.MenuDTO;
import com.ziyao.ideal.uaa.domain.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * <p>
 * 菜单资源表 服务类
 * </p>
 *
 * @author ziyao
 */
public interface MenuService {

    /**
     * 保存 菜单资源表
     *
     * @param menuDTO 待存储对象
     */
    void save(MenuDTO menuDTO);

    /**
     * 通过主键修改 菜单资源表
     *
     * @param menuDTO 待修改对象
     */
    void updateById(MenuDTO menuDTO);

    /**
     * 通过主键删除 菜单资源表
     *
     * @param id 主键id
     */
    void deleteById(Integer id);

    /**
     * 分页查询 菜单资源表
     *
     * @param menuDTO  查询对象
     * @param pageable 分页对象
     * @return 返回分页对象
     */
    Page<Menu> page(MenuDTO menuDTO, Pageable pageable);
}
