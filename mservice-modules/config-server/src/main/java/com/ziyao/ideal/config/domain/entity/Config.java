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
@Table(name = "config")
public class Config {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "group_id", nullable = false)
    private Integer groupId;

    @Size(max = 255)
    @NotNull
    @Column(name = "data_id", nullable = false)
    private String dataId;

    @Size(max = 20)
    @NotNull
    @Column(name = "config_type", nullable = false, length = 20)
    private String configType;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

}