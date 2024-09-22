package com.ziyao.ideal.uaa.service;

import com.ziyao.ideal.uaa.domain.dto.UserRoleDTO;
import com.ziyao.ideal.uaa.domain.entity.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ziyao
 */
public interface UserRoleService {

    /**
     * 保存
     *
     * @param userRoleDTO 待存储对象
     */
    void save(UserRoleDTO userRoleDTO);

    /**
     * 通过主键修改
     *
     * @param userRoleDTO 待修改对象
     */
    void updateById(UserRoleDTO userRoleDTO);

    /**
     * 通过主键删除
     *
     * @param id 主键id
     */
    void deleteById(Integer id);

    /**
     * 分页查询
     *
     * @param userRoleDTO 查询对象
     * @param pageable    分页对象
     * @return 返回分页对象
     */
    Page<UserRole> page(UserRoleDTO userRoleDTO, Pageable pageable);
}
