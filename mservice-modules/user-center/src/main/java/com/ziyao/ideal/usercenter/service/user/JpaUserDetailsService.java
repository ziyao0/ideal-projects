package com.ziyao.ideal.usercenter.service.user;

import com.ziyao.ideal.security.core.UserDetails;
import com.ziyao.ideal.security.core.UserInfo;
import com.ziyao.ideal.usercenter.authentication.core.SimpleGrantedAuthority;
import com.ziyao.ideal.usercenter.domain.entity.User;
import com.ziyao.ideal.usercenter.repository.jpa.UserRepositoryJpa;
import com.ziyao.ideal.usercenter.repository.jpa.UserRoleRepositoryJpa;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author ziyao
 */
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepositoryJpa userRepositoryJpa;
    private final UserRoleRepositoryJpa userRoleRepositoryJpa;

    public JpaUserDetailsService(UserRepositoryJpa userRepositoryJpa, UserRoleRepositoryJpa userRoleRepositoryJpa) {
        this.userRepositoryJpa = userRepositoryJpa;
        this.userRoleRepositoryJpa = userRoleRepositoryJpa;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepositoryJpa.findByUsername(username)
                .map(user -> toObject(user, userRoleRepositoryJpa.findByUserId(user.getId())))
                .orElse(null);
    }


    private UserInfo toObject(User user, Set<String> roles) {
        return UserInfo.withId(user.getId())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .password(user.getPassword())
                .status(user.getStatus())
                .authorities(
                        authorities -> roles.forEach(
                                role -> authorities.add(new SimpleGrantedAuthority(role))
                        )
                )
                .build();
    }
}
