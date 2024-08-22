package com.ziyao.ideal.uaa.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
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
public class Department implements Serializable {


    private static final long serialVersionUID = 1L;


    /**
     * id:主键id
     */
    @Id
    private Integer id;

    /**
     * appId:系统id
     */
    private Integer appId;

    /**
     * deptName:部门名称
     */
    private String deptName;

    /**
     * parentId:上级部门id
     */
    private Integer parentId;

    /**
     * createdBy:创建人id
     */
    private Integer createdBy;

    /**
     * createdAt:创建时间
     */
    private LocalDateTime createdAt;

    /**
     * modifiedBy:修改人id
     */
    private Integer modifiedBy;

    /**
     * modifiedAt:修改时间
     */
    private LocalDateTime modifiedAt;
}
