package com.ziyao.ideal.uaa.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author ziyao
 */
@Getter
@Setter
@Builder
@Entity(name = "user_role")
@NoArgsConstructor
@AllArgsConstructor
public class UserRole implements Serializable {


    private static final long serialVersionUID = 1L;


    /**
     * userId:
     */
    @Id
    private Integer userId;

    /**
     * roleId:
     */
    private Integer roleId;

    /**
     * role:
     */
    private String role;

    /**
     * category:
     */
    private Integer category;

    /**
     * accessLevel:
     */
    private String accessLevel;

    /**
     * createdAt:
     */
    private LocalDateTime createdAt;
}
