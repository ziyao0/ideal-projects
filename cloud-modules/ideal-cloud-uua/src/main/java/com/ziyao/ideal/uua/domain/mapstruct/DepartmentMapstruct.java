package com.ziyao.ideal.uua.domain.mapstruct;

import com.ziyao.ideal.uua.domain.entity.Department;
import com.ziyao.ideal.uua.domain.dto.DepartmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 */
@Mapper
public interface DepartmentMapstruct {

    DepartmentMapstruct INSTANCE = Mappers.getMapper(DepartmentMapstruct.class);

    /**
     * 转换
     */
    Department of(DepartmentDTO departmentDTO);
}
