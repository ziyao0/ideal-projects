package com.ziyao.ideal.uaa.service.user;

import com.ziyao.ideal.security.core.User;
import com.ziyao.ideal.uaa.repository.jpa.UserRepositoryJpa;
import org.springframework.stereotype.Service;

/**
 * @author ziyao
 */
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepositoryJpa userRepositoryJpa;

    public JpaUserDetailsService(UserRepositoryJpa userRepositoryJpa) {
        this.userRepositoryJpa = userRepositoryJpa;
    }

    @Override
    public User loadUserByUsername(String username) {
        return userRepositoryJpa.findByUsername(username)
                .map(this::toObject)
                .orElse(null);
    }


    private User toObject(com.ziyao.ideal.uaa.domain.entity.User user) {
        return User.withId(user.getId())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .password(user.getPassword())
                .status(user.getStatus())
                .mobile(user.getMobile())
                .gender(user.getGender())
                .address(user.getAddress())
                .idCardName(user.getIdCardName())
                .lastLogin(user.getLastLogin())
                .build();
    }
}
