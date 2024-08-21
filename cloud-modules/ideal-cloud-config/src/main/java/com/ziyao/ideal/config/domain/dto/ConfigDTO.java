package com.ziyao.ideal.config.domain.dto;

import com.ziyao.ideal.config.domain.entity.Config;
import com.ziyao.ideal.config.domain.mapstruct.ConfigMapstruct;
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
public class ConfigDTO implements EntityDTO<Config>, Serializable {

    
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Integer id;
    /**
     * 组
     */
    private String group;
    /**
     *
     */
    private String dataId;
    /**
     *
     */
    private String configType;
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

    public Config of() {
        return ConfigMapstruct.INSTANCE.of(this);
    }
}
