package com.ziyao.ideal.config.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

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
@Entity(name = "config_item")
public class ConfigItem implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private Long configId;

    private String configKey;

    private String configValue;

    private String valueType;

    private String configLevel;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
