package com.ziyao.ideal.config.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.*;
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
@Getter
@Setter
@Builder
@Entity(name = "config_property")
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "配置属性")
public class ConfigProperty implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id:主键ID
     */
    @Id
    @Schema(description = "主键ID")
    private Integer id;

    /**
     * configId:配置ID
     */
    @Schema(description = "配置ID")
    private Integer configId;

    /**
     * propertyKey:属性键
     */
    @Schema(description = "属性键")
    private String propertyKey;

    /**
     * propertyValue:属性值
     */
    @Schema(description = "属性值")
    private String propertyValue;

    /**
     * propertyType:属性类型
     */
    @Schema(description = "属性类型")
    private String propertyType;

    /**
     * configLevel:配置等级
     */
    @Schema(description = "配置等级")
    private String configLevel;

    /**
     * description:属性描述
     */
    @Schema(description = "属性描述")
    private String description;

    /**
     * createdAt:创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createdAt;

    /**
     * updatedAt:修改时间
     */
    @Schema(description = "修改时间")
    private LocalDateTime updatedAt;


    public static class Builder {

        private final ConfigProperty configProperty = new ConfigProperty();

        public Builder id(Integer id) {
            this.configProperty.id = id;
            return this;
        }

        public Builder configId(Integer configId) {
            this.configProperty.configId = configId;
            return this;
        }

        public Builder propertyKey(String propertyKey) {
            this.configProperty.propertyKey = propertyKey;
            return this;
        }

        public Builder propertyValue(String propertyValue) {
            this.configProperty.propertyValue = propertyValue;
            return this;
        }

        public Builder propertyType(String propertyType) {
            this.configProperty.propertyType = propertyType;
            return this;
        }

        public Builder configLevel(String configLevel) {
            this.configProperty.configLevel = configLevel;
            return this;
        }

        public Builder description(String description) {
            this.configProperty.description = description;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.configProperty.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.configProperty.updatedAt = updatedAt;
            return this;
        }


        public ConfigProperty build(){
            return this.configProperty;
        }
    }
}
