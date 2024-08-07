package com.ziyao.ideal.uua.repository.jpa;

import com.ziyao.ideal.uua.domain.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
*
* @author ziyao
*/
@Repository
public interface DepartmentRepositoryJpa extends JpaRepository<Department, Integer> {
}
