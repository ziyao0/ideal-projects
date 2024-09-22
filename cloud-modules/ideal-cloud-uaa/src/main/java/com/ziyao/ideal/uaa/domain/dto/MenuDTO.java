package com.ziyao.ideal.uaa.domain.dto;

import com.ziyao.ideal.uaa.domain.entity.Menu;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 菜单资源表
 * </p>
 *
 * @author ziyao
 */
@Data
@Schema(description = "菜单资源表")
public class MenuDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 资源ID
     */
    @Schema(description = "资源ID")
    private Integer id;

    /**
     * 系统id
     */
    @Schema(description = "系统id")
    private Integer appId;

    /**
     * 资源名称
     */
    @Schema(description = "资源名称")
    private String name;

    /**
     * 菜单编码
     */
    @Schema(description = "菜单编码")
    private String code;

    /**
     * 资源URL
     */
    @Schema(description = "资源URL")
    private String url;

    /**
     * 资源图标
     */
    @Schema(description = "资源图标")
    private String icon;

    /**
     * 上级资源ID
     */
    @Schema(description = "上级资源ID")
    private Integer parentId;

    /**
     * 资源级别
     */
    @Schema(description = "资源级别")
    private Byte level;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Short sort;

    /**
     * 创建人ID
     */
    @Schema(description = "创建人ID")
    private Integer createdBy;

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
     * 更新人ID
     */
    @Schema(description = "更新人ID")
    private Integer updatedBy;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    /**
     * start time for 更新时间
     */
    @Schema(description = "更新时间-开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startUpdatedAt;

    /**
     * end time for 更新时间
     */
    @Schema(description = "更新时间-结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endUpdatedAt;

    public Menu toEntity() {
        Menu menu = new Menu();
        menu.setId(this.id);
        menu.setAppId(this.appId);
        menu.setName(this.name);
        menu.setCode(this.code);
        menu.setUrl(this.url);
        menu.setIcon(this.icon);
        menu.setParentId(this.parentId);
        menu.setLevel(this.level);
        menu.setSort(this.sort);
        menu.setCreatedBy(this.createdBy);
        menu.setCreatedAt(this.createdAt);
        menu.setUpdatedBy(this.updatedBy);
        menu.setUpdatedAt(this.updatedAt);
        return menu;
    }
}
