package com.ziyao.ideal.uaa.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

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
@Getter
@Setter
@Builder
@Entity(name = "menu")
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "菜单资源表")
public class Menu implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id:资源ID
     */
    @Id
    @Schema(description = "资源ID")
    private Integer id;

    /**
     * appId:系统id
     */
    @Schema(description = "系统id")
    private Integer appId;

    /**
     * name:资源名称
     */
    @Schema(description = "资源名称")
    private String name;

    /**
     * code:菜单编码
     */
    @Schema(description = "菜单编码")
    private String code;

    /**
     * url:资源URL
     */
    @Schema(description = "资源URL")
    private String url;

    /**
     * icon:资源图标
     */
    @Schema(description = "资源图标")
    private String icon;

    /**
     * parentId:上级资源ID
     */
    @Schema(description = "上级资源ID")
    private Integer parentId;

    /**
     * level:资源级别
     */
    @Schema(description = "资源级别")
    private Byte level;

    /**
     * sort:排序
     */
    @Schema(description = "排序")
    private Short sort;

    /**
     * createdBy:创建人ID
     */
    @Schema(description = "创建人ID")
    private Integer createdBy;

    /**
     * createdAt:创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createdAt;

    /**
     * updatedBy:更新人ID
     */
    @Schema(description = "更新人ID")
    private Integer updatedBy;

    /**
     * updatedAt:更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updatedAt;


    public static class Builder {

        private final Menu menu = new Menu();

        public Builder id(Integer id) {
            this.menu.id = id;
            return this;
        }

        public Builder appId(Integer appId) {
            this.menu.appId = appId;
            return this;
        }

        public Builder name(String name) {
            this.menu.name = name;
            return this;
        }

        public Builder code(String code) {
            this.menu.code = code;
            return this;
        }

        public Builder url(String url) {
            this.menu.url = url;
            return this;
        }

        public Builder icon(String icon) {
            this.menu.icon = icon;
            return this;
        }

        public Builder parentId(Integer parentId) {
            this.menu.parentId = parentId;
            return this;
        }

        public Builder level(Byte level) {
            this.menu.level = level;
            return this;
        }

        public Builder sort(Short sort) {
            this.menu.sort = sort;
            return this;
        }

        public Builder createdBy(Integer createdBy) {
            this.menu.createdBy = createdBy;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.menu.createdAt = createdAt;
            return this;
        }

        public Builder updatedBy(Integer updatedBy) {
            this.menu.updatedBy = updatedBy;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            this.menu.updatedAt = updatedAt;
            return this;
        }


        public Menu build() {
            return this.menu;
        }
    }
}
