package com.ziyao.ideal.uaa.service.impl;

import com.ziyao.ideal.uaa.domain.dto.UserDTO;
import com.ziyao.ideal.uaa.domain.entity.User;
import com.ziyao.ideal.uaa.repository.jpa.UserRepositoryJpa;
import com.ziyao.ideal.uaa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepositoryJpa userRepositoryJpa;

    /**
     * 保存 用户表
     *
     * @param userDTO 待存储对象
     */
    @Override
    public void save(UserDTO userDTO) {
        userRepositoryJpa.save(userDTO.toEntity());
    }

    /**
     * 通过主键修改 用户表
     *
     * @param userDTO 待修改对象
     */
    @Override
    public void updateById(UserDTO userDTO) {
        userRepositoryJpa.save(userDTO.toEntity());
    }

    /**
     * 通过主键删除 用户表
     *
     * @param id 主键id
     */
    @Override
    public void deleteById(Integer id) {
        userRepositoryJpa.deleteById(id);
    }

    /**
     * 分页查询 用户表
     *
     * @param userDTO  查询对象
     * @param pageable 分页对象
     * @return 返回分页对象
     */
    @Override
    public Page<User> page(UserDTO userDTO, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); // Use CONTAINING for partial matches
        Example<User> example = Example.of(userDTO.toEntity(), matcher);
        return userRepositoryJpa.findAll(example, pageable);
    }

    @Override
    public boolean lock(String username) {
        Optional<User> userOptional = userRepositoryJpa.findByUsername(username);
        return userOptional.map(lockoutuser -> {
            lockoutuser.setStatus(4);
            userRepositoryJpa.save(lockoutuser);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean unlock(String username) {
        Optional<User> userOptional = userRepositoryJpa.findByUsername(username);
        return userOptional.map(lockoutuser -> {
            lockoutuser.setStatus(1);
            userRepositoryJpa.save(lockoutuser);
            return true;
        }).orElse(false);
    }
}