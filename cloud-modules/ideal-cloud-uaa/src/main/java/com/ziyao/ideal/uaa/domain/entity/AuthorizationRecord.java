package com.ziyao.ideal.uaa.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 授权记录表
 * </p>
 *
 * @author ziyao
 */
@Getter
@Setter
@Builder
@Entity(name = "authorization_record")
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "授权记录表")
public class AuthorizationRecord implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id:
     */
    @Id
    @Schema(description = "")
    private Integer id;

    /**
     * principal:
     */
    @Schema(description = "")
    private String principal;


    public static class Builder {

        private final AuthorizationRecord authorizationRecord = new AuthorizationRecord();

        public Builder id(Integer id) {
            this.authorizationRecord.id = id;
            return this;
        }

        public Builder principal(String principal) {
            this.authorizationRecord.principal = principal;
            return this;
        }


        public AuthorizationRecord build() {
            return this.authorizationRecord;
        }
    }
}
