package com.ziyao.ideal.uaa.service;

import com.ziyao.ideal.uaa.domain.dto.UserDTO;
import com.ziyao.ideal.uaa.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author ziyao
 */
public interface UserService {

    /**
     * 保存 用户表
     *
     * @param userDTO 待存储对象
     */
    void save(UserDTO userDTO);

    /**
     * 通过主键修改 用户表
     *
     * @param userDTO 待修改对象
     */
    void updateById(UserDTO userDTO);

    /**
     * 通过主键删除 用户表
     *
     * @param id 主键id
     */
    void deleteById(Integer id);

    /**
     * 分页查询 用户表
     *
     * @param userDTO  查询对象
     * @param pageable 分页对象
     * @return 返回分页对象
     */
    Page<User> page(UserDTO userDTO, Pageable pageable);

    /**
     * 锁定账号
     *
     * @param username 账号
     * @return true 锁定成功
     */
    boolean lock(String username);

    /**
     * 解除账号锁定
     *
     * @param username 待解除锁定账号
     * @return true 解锁成功
     */
    boolean unlock(String username);
}
