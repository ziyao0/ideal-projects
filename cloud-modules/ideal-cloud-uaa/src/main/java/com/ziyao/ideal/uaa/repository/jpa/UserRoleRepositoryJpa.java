package com.ziyao.ideal.uaa.repository.jpa;

import com.ziyao.ideal.uaa.domain.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author ziyao
 */
@Repository
public interface UserRoleRepositoryJpa extends JpaRepository<UserRole, Integer> {


    @Query("select role from user_role where userId=:userId")
    Set<String> findByUserId(@Param("userId") Integer userId);

}
