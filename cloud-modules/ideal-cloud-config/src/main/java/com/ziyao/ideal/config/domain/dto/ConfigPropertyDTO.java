package com.ziyao.ideal.config.domain.dto;

import com.ziyao.ideal.config.domain.entity.ConfigProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 配置属性
 * </p>
 *
 * @author ziyao
 */
@Data
@Schema(description = "配置属性")
public class ConfigPropertyDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


     /**
      * 主键ID
      */
     @Schema(description = "主键ID")
     private Integer id;

     /**
      * 配置ID
      */
     @Schema(description = "配置ID")
     private Integer configId;

     /**
      * 属性键
      */
     @Schema(description = "属性键")
     private String propertyKey;

     /**
      * 属性值
      */
     @Schema(description = "属性值")
     private String propertyValue;

     /**
      * 属性类型
      */
     @Schema(description = "属性类型")
     private String propertyType;

     /**
      * 配置等级
      */
     @Schema(description = "配置等级")
     private String configLevel;

     /**
      * 属性描述
      */
     @Schema(description = "属性描述")
     private String description;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    /**
     * start time for 创建时间
     */
    @Schema(description = "创建时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startCreatedAt;

     /**
      * end time for 创建时间
      */
     @Schema(description = "创建时间-结束时间")
     @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
     private LocalDateTime endCreatedAt;

    /**
     * 修改时间
     */
    @Schema(description = "修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    /**
     * start time for 修改时间
     */
    @Schema(description = "修改时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startUpdatedAt;

     /**
      * end time for 修改时间
      */
     @Schema(description = "修改时间-结束时间")
     @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
     private LocalDateTime endUpdatedAt;

    public ConfigProperty toEntity(){
        ConfigProperty configProperty = new ConfigProperty();
        configProperty.setId(this.id);
        configProperty.setConfigId(this.configId);
        configProperty.setPropertyKey(this.propertyKey);
        configProperty.setPropertyValue(this.propertyValue);
        configProperty.setPropertyType(this.propertyType);
        configProperty.setConfigLevel(this.configLevel);
        configProperty.setDescription(this.description);
        configProperty.setCreatedAt(this.createdAt);
        configProperty.setUpdatedAt(this.updatedAt);
        return configProperty;
    }
}
