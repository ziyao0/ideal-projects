package com.ziyao.ideal.config.domain.dto;

import com.ziyao.ideal.config.domain.entity.Config;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 配置表
 * </p>
 *
 * @author ziyao
 */
@Data
@Schema(description = "配置表")
public class ConfigDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


     /**
      * 主键ID
      */
     @Schema(description = "主键ID")
     private Integer id;

     /**
      * 配置组
      */
     @Schema(description = "配置组")
     private String group;

     /**
      * 配置ID
      */
     @Schema(description = "配置ID")
     private String dataId;

     /**
      * 配置类型 yaml、properties
      */
     @Schema(description = "配置类型 yaml、properties")
     private String configType;

     /**
      * 描述
      */
     @Schema(description = "描述")
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

    public Config toEntity(){
        Config config = new Config();
        config.setId(this.id);
        config.setGroup(this.group);
        config.setDataId(this.dataId);
        config.setConfigType(this.configType);
        config.setDescription(this.description);
        config.setCreatedAt(this.createdAt);
        config.setUpdatedAt(this.updatedAt);
        return config;
    }
}
