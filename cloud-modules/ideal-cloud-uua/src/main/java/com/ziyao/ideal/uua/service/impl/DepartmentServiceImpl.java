package com.ziyao.ideal.uua.service.impl;

import com.ziyao.ideal.uua.repository.jpa.DepartmentRepositoryJpa;
import com.ziyao.ideal.jpa.extension.service.impl.JapServiceImpl;
import com.ziyao.ideal.uua.domain.entity.Department;
import com.ziyao.ideal.uua.service.DepartmentService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
/**
* <p>
    * 部门表 服务实现类
 * </p>
 *
 * @author ziyao
 */
@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl extends
    JapServiceImpl< DepartmentRepositoryJpa, Department,Integer> implements DepartmentService {

    private final DepartmentRepositoryJpa departmentRepositoryJpa;

}
