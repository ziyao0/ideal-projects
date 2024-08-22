package com.ziyao.ideal.uaa.repository.jpa;

import com.ziyao.ideal.uaa.domain.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
*
* @author ziyao
*/
@Repository
public interface MenuRepositoryJpa extends JpaRepository<Menu, Integer> {
}
