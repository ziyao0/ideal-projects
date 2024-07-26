package com.ziyao.ideal.usercenter.repository.jpa;

import com.ziyao.ideal.usercenter.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ziyao
 */
@Repository
public interface UserRepositoryJpa extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
