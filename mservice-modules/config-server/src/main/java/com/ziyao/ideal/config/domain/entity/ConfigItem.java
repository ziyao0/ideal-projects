package com.ziyao.ideal.config.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "config_item")
public class ConfigItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "config_id", nullable = false)
    private Integer configId;

    @Size(max = 255)
    @NotNull
    @Column(name = "config_key", nullable = false)
    private String configKey;

    @Lob
    @Column(name = "config_value")
    private String configValue;

    @Size(max = 50)
    @NotNull
    @Column(name = "value_type", nullable = false, length = 50)
    private String valueType;

    @Size(max = 50)
    @NotNull
    @Column(name = "config_level", nullable = false, length = 50)
    private String configLevel;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

}