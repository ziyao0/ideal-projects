package com.ziyao.ideal.uaa.service.impl;

import com.ziyao.ideal.jpa.extension.service.impl.JpaServiceImpl;
import com.ziyao.ideal.uaa.domain.entity.User;
import com.ziyao.ideal.uaa.repository.jpa.UserRepositoryJpa;
import com.ziyao.ideal.uaa.service.UserService;
import lombok.RequiredArgsConstructor;
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
public class UserServiceImpl extends JpaServiceImpl<UserRepositoryJpa, User, Integer> implements UserService {

    private final UserRepositoryJpa userRepositoryJpa;


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
