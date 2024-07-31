package com.ziyao.ideal.uua.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ziyao.ideal.uua.domain.dto.UserDTO;
import com.ziyao.ideal.uua.domain.entity.User;
import com.ziyao.ideal.uua.repository.jpa.UserRepositoryJpa;
import com.ziyao.ideal.uua.repository.mapper.UserMapper;
import com.ziyao.ideal.uua.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zhangziyao
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;
    private final UserRepositoryJpa userRepositoryJpa;

    @Override
    public Page<User> page(Page<User> page, UserDTO userDTO) {
        LambdaQueryWrapper<User> wrapper = userDTO.initWrapper();
        // to do 2023/5/6 默认排序字段 sort/sorted(默认是为ASC)值越小、越往前
        return userMapper.selectPage(page, wrapper);
    }

    @Override
    public User loadUserDetails(String accessKey) {
        // 通过appid和accessKey获取用户信息
        return userMapper.selectOne(
                Wrappers.lambdaQuery(User.class)
                        .eq(User::getUsername, accessKey));

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
