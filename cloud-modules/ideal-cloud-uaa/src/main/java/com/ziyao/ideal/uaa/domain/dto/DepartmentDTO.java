package com.ziyao.ideal.uaa.domain.dto;

import com.ziyao.ideal.uaa.domain.entity.Department;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
@Schema(description = "部门表")
public class DepartmentDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    @Schema(description = "主键id")
    private Integer id;

    /**
     * 系统id
     */
    @Schema(description = "系统id")
    private Integer appId;

    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    private String deptName;

    /**
     * 上级部门id
     */
    @Schema(description = "上级部门id")
    private Integer parentId;

    /**
     * 创建人id
     */
    @Schema(description = "创建人id")
    private Integer createdBy;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    /**
     * start time for 创建时间
     */
    @Schema(description = "创建时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startCreatedAt;

    /**
     * end time for 创建时间
     */
    @Schema(description = "创建时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endCreatedAt;

    /**
     * 修改人id
     */
    @Schema(description = "修改人id")
    private Integer modifiedBy;

    /**
     * 修改时间
     */
    @Schema(description = "修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;
    /**
     * start time for 修改时间
     */
    @Schema(description = "修改时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startModifiedAt;

    /**
     * end time for 修改时间
     */
    @Schema(description = "修改时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endModifiedAt;

    public Department toEntity() {
        Department department = new Department();
        department.setId(this.id);
        department.setAppId(this.appId);
        department.setDeptName(this.deptName);
        department.setParentId(this.parentId);
        department.setCreatedBy(this.createdBy);
        department.setCreatedAt(this.createdAt);
        department.setModifiedBy(this.modifiedBy);
        department.setModifiedAt(this.modifiedAt);
        return department;
    }
}
