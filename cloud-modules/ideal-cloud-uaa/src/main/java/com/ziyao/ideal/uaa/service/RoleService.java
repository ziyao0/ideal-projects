package com.ziyao.ideal.uaa.service;

import com.ziyao.ideal.uaa.domain.dto.RoleDTO;
import com.ziyao.ideal.uaa.domain.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author ziyao
 */
public interface RoleService {

    /**
     * 保存 角色表
     *
     * @param roleDTO 待存储对象
     */
    void save(RoleDTO roleDTO);

    /**
     * 通过主键修改 角色表
     *
     * @param roleDTO 待修改对象
     */
    void updateById(RoleDTO roleDTO);

    /**
     * 通过主键删除 角色表
     *
     * @param id 主键id
     */
    void deleteById(Integer id);

    /**
     * 分页查询 角色表
     *
     * @param roleDTO  查询对象
     * @param pageable 分页对象
     * @return 返回分页对象
     */
    Page<Role> page(RoleDTO roleDTO, Pageable pageable);
}
