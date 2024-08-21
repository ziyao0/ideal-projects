package com.ziyao.ideal.uua.domain.convertor;

import com.ziyao.ideal.uua.domain.entity.Department;
import com.ziyao.ideal.uua.domain.dto.DepartmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 */
@Mapper
public interface DepartmentConvertor {

    DepartmentConvertor INSTANCE = Mappers.getMapper(DepartmentConvertor.class);

    /**
     * 转换
     */
    Department convert(DepartmentDTO departmentDTO);
}
