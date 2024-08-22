package com.ziyao.ideal.uua.domain.dto;

import com.ziyao.ideal.uua.domain.convertor.DepartmentConvertor;
import com.ziyao.ideal.uua.domain.entity.Department;
import com.ziyao.ideal.web.orm.EntityDTO;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author ziyao
 */
@Data
public class DepartmentDTO implements EntityDTO<Department>, Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 系统id
     */
    private Integer appId;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 上级部门id
     */
    private Integer parentId;
    /**
     * 创建人id
     */
    private Integer createdBy;
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    /**
     * 修改人id
     */
    private Integer modifiedBy;
    /**
     * 修改时间
     */
    private LocalDateTime modifiedAt;

    public Department convert() {
        return DepartmentConvertor.INSTANCE.convert(this);
    }
}
