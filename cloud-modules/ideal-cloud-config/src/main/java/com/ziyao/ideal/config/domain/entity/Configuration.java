package com.ziyao.ideal.config.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Configuration {

    private String dataId;

    private String group;

    private String configType;

    /**
     * configId:
     */
    private Integer configId;

    /**
     * configKey:
     */
    private String configKey;

    /**
     * configValue:
     */
    private String configValue;

    /**
     * valueType:
     */
    private String valueType;

    /**
     * configLevel:
     */
    private String configLevel;
}
