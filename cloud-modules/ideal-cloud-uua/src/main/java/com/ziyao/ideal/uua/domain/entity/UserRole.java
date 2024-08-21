package com.ziyao.ideal.uua.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serial;
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

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * userId:
     */
    @Id
    private Integer userId;

    /**
     * roleId:
     */
    @Id
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
