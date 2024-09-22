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
@Schema(description = "")
public class UserRole implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * userId:
     */
    @Id
    @Schema(description = "")
    private Integer userId;

    /**
     * roleId:
     */
    @Schema(description = "")
    private Integer roleId;

    /**
     * role:
     */
    @Schema(description = "")
    private String role;

    /**
     * category:
     */
    @Schema(description = "")
    private Integer category;

    /**
     * accessLevel:
     */
    @Schema(description = "")
    private String accessLevel;

    /**
     * createdAt:
     */
    @Schema(description = "")
    private LocalDateTime createdAt;


    public static class Builder {

        private final UserRole userRole = new UserRole();

        public Builder userId(Integer userId) {
            this.userRole.userId = userId;
            return this;
        }

        public Builder roleId(Integer roleId) {
            this.userRole.roleId = roleId;
            return this;
        }

        public Builder role(String role) {
            this.userRole.role = role;
            return this;
        }

        public Builder category(Integer category) {
            this.userRole.category = category;
            return this;
        }

        public Builder accessLevel(String accessLevel) {
            this.userRole.accessLevel = accessLevel;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.userRole.createdAt = createdAt;
            return this;
        }


        public UserRole build() {
            return this.userRole;
        }
    }
}
