package com.ziyao.ideal.uaa.domain.dto;

import com.ziyao.ideal.uaa.domain.entity.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
@Data
@Schema(description = "")
public class UserRoleDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     *
     */
    @Schema(description = "")
    private Integer userId;

    /**
     *
     */
    @Schema(description = "")
    private Integer roleId;

    /**
     *
     */
    @Schema(description = "")
    private String role;

    /**
     *
     */
    @Schema(description = "")
    private Integer category;

    /**
     *
     */
    @Schema(description = "")
    private String accessLevel;

    /**
     *
     */
    @Schema(description = "")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    /**
     * start time for
     */
    @Schema(description = "-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startCreatedAt;

    /**
     * end time for
     */
    @Schema(description = "-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endCreatedAt;

    public UserRole toEntity() {
        UserRole userRole = new UserRole();
        userRole.setUserId(this.userId);
        userRole.setRoleId(this.roleId);
        userRole.setRole(this.role);
        userRole.setCategory(this.category);
        userRole.setAccessLevel(this.accessLevel);
        userRole.setCreatedAt(this.createdAt);
        return userRole;
    }
}
