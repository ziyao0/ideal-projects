package com.ziyao.ideal.config.domain.dto;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ziyao.ideal.web.orm.EntityDTO;
import com.ziyao.ideal.config.domain.entity.ConfigItem;
import lombok.Data;
import java.util.Objects;
import com.ziyao.ideal.core.Strings;

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
@Data
public class ConfigItemDTO implements EntityDTO<ConfigItem>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private Long id;
    /**
     * 
     */
    private Long configId;
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

    /**
     * 组装查询条件，可根据具体情况做出修改
     *
     * @see LambdaQueryWrapper
     */
    public LambdaQueryWrapper<ConfigItem> initWrapper() {

        return Wrappers.lambdaQuery(ConfigItem.class)
                // 
                .eq(Objects.nonNull(configId), ConfigItem::getConfigId, configId)
                // 
                .likeRight(Strings.hasLength(configKey), ConfigItem::getConfigKey, configKey)
                // 
                .likeRight(Strings.hasLength(configValue), ConfigItem::getConfigValue, configValue)
                // 
                .likeRight(Strings.hasLength(valueType), ConfigItem::getValueType, valueType)
                // 
                .likeRight(Strings.hasLength(configLevel), ConfigItem::getConfigLevel, configLevel)
                // 
                .likeRight(Strings.hasLength(description), ConfigItem::getDescription, description)
                // 
                .eq(Objects.nonNull(createdAt), ConfigItem::getCreatedAt, createdAt)
                // 
                .eq(Objects.nonNull(updatedAt), ConfigItem::getUpdatedAt, updatedAt)
                ;
    }

    @Override
    public ConfigItem getEntity() {
        return new ConfigItem();
    }
}
