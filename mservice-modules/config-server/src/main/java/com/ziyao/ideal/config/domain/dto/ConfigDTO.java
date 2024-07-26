package com.ziyao.ideal.config.domain.dto;

import com.ziyao.ideal.config.domain.entity.Config;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Config}
 */
@Value
public class ConfigDTO implements Serializable {
    Integer id;
    Integer groupId;
    String dataId;
    String configType;
    String description;
}