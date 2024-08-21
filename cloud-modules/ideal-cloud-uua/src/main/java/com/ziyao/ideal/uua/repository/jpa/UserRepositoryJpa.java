package com.ziyao.ideal.uua.repository.jpa;

import com.ziyao.ideal.uua.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ziyao
 */
@Repository
public interface UserRepositoryJpa extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
