package com.ziyao.ideal.config.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
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
@Entity(name = "config_item")
@NoArgsConstructor
@AllArgsConstructor
public class ConfigItem implements Serializable {

    
    private static final long serialVersionUID = 1L;


    /**
     * id:
     */
    @Id
    private Integer id;

    /**
     * configId:
     */
    private Integer configId;

    /**
     * configKey:
     */
    private String configKey;

    /**
     * configValue:
     */
    private String configValue;

    /**
     * valueType:
     */
    private String valueType;

    /**
     * configLevel:
     */
    private String configLevel;

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
