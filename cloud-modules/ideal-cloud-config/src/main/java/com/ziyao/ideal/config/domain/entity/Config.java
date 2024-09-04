package com.ziyao.ideal.config.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 配置表
 * </p>
 *
 * @author ziyao
 */
@Getter
@Setter
@Builder
@javax.persistence.Entity(name = "config")
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "配置表")
public class Config implements Serializable {

    
    private static final long serialVersionUID = 1L;

    /**
     * id:主键ID
     */
    @Id
    @Schema(description = "主键ID")
    private Integer id;

    /**
     * group:配置组
     */
    @Schema(description = "配置组")
    private String group;

    /**
     * dataId:配置ID
     */
    @Schema(description = "配置ID")
    private String dataId;

    /**
     * configType:配置类型 yaml、properties
     */
    @Schema(description = "配置类型 yaml、properties")
    private String configType;

    /**
     * description:描述
     */
    @Schema(description = "描述")
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

        private final Config config = new Config();

        public Builder id(Integer id) {
            this.config.id = id;
            return this;
        }

        public Builder group(String group) {
            this.config.group = group;
            return this;
        }

        public Builder dataId(String dataId) {
            this.config.dataId = dataId;
            return this;
        }

        public Builder configType(String configType) {
            this.config.configType = configType;
            return this;
        }

        public Builder description(String description) {
            this.config.description = description;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.config.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.config.updatedAt = updatedAt;
            return this;
        }


        public Config build() {
            return this.config;
        }
    }
}
