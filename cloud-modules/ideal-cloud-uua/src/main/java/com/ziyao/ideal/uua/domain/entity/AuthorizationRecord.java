package com.ziyao.ideal.uua.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
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
public class AuthorizationRecord implements Serializable {


    private static final long serialVersionUID = 1L;


    /**
     * id:
     */
    @Id
    private Integer id;

    /**
     * principal:
     */
    private String principal;
}
