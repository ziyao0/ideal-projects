package com.ziyao.ideal.config.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
@Entity
public class Config implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private Long groupId;

    private Long dataId;

    private String configType;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
