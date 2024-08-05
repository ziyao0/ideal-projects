package com.ziyao.ideal.config.domain.dto;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ziyao.ideal.config.domain.entity.Config;
import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.web.orm.EntityDTO;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author ziyao
 */
@Data
public class ConfigDTO implements EntityDTO<Config>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Long id;
    /**
     *
     */
    private Long groupId;
    /**
     *
     */
    private Long dataId;
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
                // 
                .eq(Objects.nonNull(groupId), Config::getGroupId, groupId)
                // 
                .eq(Objects.nonNull(dataId), Config::getDataId, dataId)
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

    @Override
    public Config getEntity() {
        return new Config();
    }
}
