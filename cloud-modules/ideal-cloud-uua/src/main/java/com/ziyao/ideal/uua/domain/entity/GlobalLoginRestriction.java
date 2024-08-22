package com.ziyao.ideal.uua.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 全局登录限制表
 * </p>
 *
 * @author ziyao
 */
@Getter
@Setter
@Builder
@Entity(name = "global_login_restriction")
@NoArgsConstructor
@AllArgsConstructor
public class GlobalLoginRestriction implements Serializable {


    private static final long serialVersionUID = 1L;


    /**
     * id:记录ID
     */
    @Id
    private Integer id;

    /**
     * ruleId:限制规则ID，关联restriction_rules表
     */
    private Integer ruleId;

    /**
     * restrictionStart:限制开始时间
     */
    private LocalDateTime restrictionStart;

    /**
     * restrictionEnd:限制结束时间
     */
    private LocalDateTime restrictionEnd;

    /**
     * reason:限制原因
     */
    private String reason;

    /**
     * status:限制状态（例如：ACTIVE, INACTIVE）
     */
    private String status;

    /**
     * createdAt:记录创建时间
     */
    private LocalDateTime createdAt;

    /**
     * updatedAt:记录更新时间
     */
    private LocalDateTime updatedAt;
}
