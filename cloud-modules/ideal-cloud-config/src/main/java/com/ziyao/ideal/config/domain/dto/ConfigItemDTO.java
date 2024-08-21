package com.ziyao.ideal.config.domain.dto;

import com.ziyao.ideal.config.domain.entity.ConfigItem;
import com.ziyao.ideal.config.domain.mapstruct.ConfigItemMapstruct;
import com.ziyao.ideal.web.orm.EntityDTO;
import lombok.Data;


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
public class ConfigItemDTO implements EntityDTO<ConfigItem>, Serializable {

    
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Integer id;
    /**
     *
     */
    private Integer configId;
    /**
     *
     */
    private String configKey;
    /**
     *
     */
    private String configValue;
    /**
     *
     */
    private String valueType;
    /**
     *
     */
    private String configLevel;
    /**
     *
     */
    private String description;
    /**
     *
     */
    private LocalDateTime createdAt;
    /**
     *
     */
    private LocalDateTime updatedAt;

    public ConfigItem of() {
        return ConfigItemMapstruct.INSTANCE.of(this);
    }
}
