package com.ziyao.ideal.uua.domain.dto;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ziyao.ideal.web.orm.EntityDTO;
import com.ziyao.ideal.uua.domain.entity.Department;
import com.ziyao.ideal.uua.domain.mapstruct.DepartmentMapstruct;
import lombok.Data;
import java.util.Objects;
import com.ziyao.ideal.core.Strings;

import java.io.Serial;
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

    @Serial
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

    /**
     * 组装查询条件，可根据具体情况做出修改
     *
     * @see LambdaQueryWrapper
     */
    public LambdaQueryWrapper<Department> initWrapper() {

        return Wrappers.lambdaQuery(Department.class)
                // 系统id
                .eq(Objects.nonNull(appId), Department::getAppId, appId)
                // 部门名称
                .likeRight(Strings.hasLength(deptName), Department::getDeptName, deptName)
                // 上级部门id
                .eq(Objects.nonNull(parentId), Department::getParentId, parentId)
                // 创建人id
                .eq(Objects.nonNull(createdBy), Department::getCreatedBy, createdBy)
                // 创建时间
                .eq(Objects.nonNull(createdAt), Department::getCreatedAt, createdAt)
                // 修改人id
                .eq(Objects.nonNull(modifiedBy), Department::getModifiedBy, modifiedBy)
                // 修改时间
                .eq(Objects.nonNull(modifiedAt), Department::getModifiedAt, modifiedAt)
                ;
    }

    public Department of() {
        return DepartmentMapstruct.INSTANCE.of(this);
    }
}
