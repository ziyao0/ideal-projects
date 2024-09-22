package com.ziyao.ideal.uaa.service;

import com.ziyao.ideal.uaa.domain.dto.UserLoginRestrictionDTO;
import com.ziyao.ideal.uaa.domain.entity.UserLoginRestriction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * <p>
 * 用户登录限制表 服务类
 * </p>
 *
 * @author ziyao
 */
public interface UserLoginRestrictionService {

    /**
     * 保存 用户登录限制表
     *
     * @param userLoginRestrictionDTO 待存储对象
     */
    void save(UserLoginRestrictionDTO userLoginRestrictionDTO);

    /**
     * 通过主键修改 用户登录限制表
     *
     * @param userLoginRestrictionDTO 待修改对象
     */
    void updateById(UserLoginRestrictionDTO userLoginRestrictionDTO);

    /**
     * 通过主键删除 用户登录限制表
     *
     * @param id 主键id
     */
    void deleteById(Integer id);

    /**
     * 分页查询 用户登录限制表
     *
     * @param userLoginRestrictionDTO 查询对象
     * @param pageable                分页对象
     * @return 返回分页对象
     */
    Page<UserLoginRestriction> page(UserLoginRestrictionDTO userLoginRestrictionDTO, Pageable pageable);
}
