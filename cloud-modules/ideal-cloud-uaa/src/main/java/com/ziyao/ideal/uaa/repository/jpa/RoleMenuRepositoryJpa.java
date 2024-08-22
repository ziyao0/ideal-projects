package com.ziyao.ideal.uaa.repository.jpa;

import com.ziyao.ideal.uaa.domain.entity.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
*
* @author ziyao
*/
@Repository
public interface RoleMenuRepositoryJpa extends JpaRepository<RoleMenu, Integer> {
}