package com.ziyao.ideal.config.domain.dto;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ziyao.ideal.web.orm.EntityDTO;
import com.ziyao.ideal.config.domain.entity.Config;
import com.ziyao.ideal.config.domain.mapstruct.ConfigMapstruct;
import lombok.Data;
import java.util.Objects;
import com.ziyao.ideal.core.Strings;


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

    /**
     * 组装查询条件，可根据具体情况做出修改
     *
     * @see LambdaQueryWrapper
     */
    public LambdaQueryWrapper<Config> initWrapper() {

        return Wrappers.lambdaQuery(Config.class)
                // 组
                .likeRight(Strings.hasLength(group), Config::getGroup, group)
                // 
                .likeRight(Strings.hasLength(dataId), Config::getDataId, dataId)
                // 
                .likeRight(Strings.hasLength(configType), Config::getConfigType, configType)
                // 
                .likeRight(Strings.hasLength(description), Config::getDescription, description)
                // 
                .eq(Objects.nonNull(createdAt), Config::getCreatedAt, createdAt)
                // 
                .eq(Objects.nonNull(updatedAt), Config::getUpdatedAt, updatedAt)
                ;
    }

    public Config of() {
        return ConfigMapstruct.INSTANCE.of(this);
    }
}
