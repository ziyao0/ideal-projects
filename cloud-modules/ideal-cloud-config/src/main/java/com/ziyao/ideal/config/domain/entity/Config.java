package com.ziyao.ideal.config.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


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
@Entity(name = "config")
@NoArgsConstructor
@AllArgsConstructor
public class Config implements Serializable {

    
    private static final long serialVersionUID = 1L;


    /**
     * id:
     */
    @Id
    private Integer id;

    /**
     * group:组
     */
    private String group;

    /**
     * dataId:
     */
    private String dataId;

    /**
     * configType:
     */
    private String configType;

    /**
     * description:
     */
    private String description;

    /**
     * createdAt:
     */
    private LocalDateTime createdAt;

    /**
     * updatedAt:
     */
    private LocalDateTime updatedAt;
}
