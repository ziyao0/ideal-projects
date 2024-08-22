package com.ziyao.ideal.uaa.domain.entity;

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
public class Menu implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * id:资源ID
     */
    @Id
    private Integer id;

    /**
     * appId:系统id
     */
    private Integer appId;

    /**
     * name:资源名称
     */
    private String name;

    /**
     * code:菜单编码
     */
    private String code;

    /**
     * url:资源URL
     */
    private String url;

    /**
     * icon:资源图标
     */
    private String icon;

    /**
     * parentId:上级资源ID
     */
    private Integer parentId;

    /**
     * level:资源级别
     */
    private Byte level;

    /**
     * sort:排序
     */
    private Integer sort;

    /**
     * createdBy:创建人ID
     */
    private Integer createdBy;

    /**
     * createdAt:创建时间
     */
    private LocalDateTime createdAt;

    /**
     * updatedBy:更新人ID
     */
    private Integer updatedBy;

    /**
     * updatedAt:更新时间
     */
    private LocalDateTime updatedAt;
}
