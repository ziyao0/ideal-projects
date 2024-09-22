package com.ziyao.ideal.uaa.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

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
@Getter
@Setter
@Builder
@Entity(name = "department")
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "部门表")
public class Department implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id:主键id
     */
    @Id
    @Schema(description = "主键id")
    private Integer id;

    /**
     * appId:系统id
     */
    @Schema(description = "系统id")
    private Integer appId;

    /**
     * deptName:部门名称
     */
    @Schema(description = "部门名称")
    private String deptName;

    /**
     * parentId:上级部门id
     */
    @Schema(description = "上级部门id")
    private Integer parentId;

    /**
     * createdBy:创建人id
     */
    @Schema(description = "创建人id")
    private Integer createdBy;

    /**
     * createdAt:创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createdAt;

    /**
     * modifiedBy:修改人id
     */
    @Schema(description = "修改人id")
    private Integer modifiedBy;

    /**
     * modifiedAt:修改时间
     */
    @Schema(description = "修改时间")
    private LocalDateTime modifiedAt;


    public static class Builder {

        private final Department department = new Department();

        public Builder id(Integer id) {
            this.department.id = id;
            return this;
        }

        public Builder appId(Integer appId) {
            this.department.appId = appId;
            return this;
        }

        public Builder deptName(String deptName) {
            this.department.deptName = deptName;
            return this;
        }

        public Builder parentId(Integer parentId) {
            this.department.parentId = parentId;
            return this;
        }

        public Builder createdBy(Integer createdBy) {
            this.department.createdBy = createdBy;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.department.createdAt = createdAt;
            return this;
        }

        public Builder modifiedBy(Integer modifiedBy) {
            this.department.modifiedBy = modifiedBy;
            return this;
        }

        public Builder modifiedAt(LocalDateTime modifiedAt) {
            this.department.modifiedAt = modifiedAt;
            return this;
        }


        public Department build() {
            return this.department;
        }
    }
}
