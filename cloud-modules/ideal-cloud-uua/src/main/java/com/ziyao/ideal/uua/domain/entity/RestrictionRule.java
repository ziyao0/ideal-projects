package com.ziyao.ideal.uua.domain.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

/**
 * <p>
 * 限制规则表
 * </p>
 *
 * @author ziyao
 */
@Getter
@Setter
@Builder
@Entity(name = "restriction_rule")
@NoArgsConstructor
@AllArgsConstructor
public class RestrictionRule implements Serializable {

    
    private static final long serialVersionUID = 1L;


    /**
     * id:规则ID
     */
    @Id
    private Integer id;

    /**
     * ruleName:规则名称
     */
    private String ruleName;

    /**
     * ruleType:规则类型（例如：IP_BLOCK, LOGIN_ATTEMPT_LIMIT等）
     */
    private String ruleType;

    /**
     * parameters:规则参数，存储具体的规则配置，以JSON格式记录
     */
    private String parameters;

    /**
     * description:规则描述
     */
    private String description;

    /**
     * createdAt:规则创建时间
     */
    private LocalDateTime createdAt;

    /**
     * updatedAt:规则更新时间
     */
    private LocalDateTime updatedAt;
}
