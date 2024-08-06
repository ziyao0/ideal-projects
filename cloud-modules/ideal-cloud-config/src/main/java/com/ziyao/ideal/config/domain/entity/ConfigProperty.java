package com.ziyao.ideal.config.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
@Setter
public class ConfigProperty {

    private Integer id;

    private Integer dataId;

    private String group;

    private Set<ConfigContent> configContents;

    @Getter
    @Setter
    public static class ConfigContent {

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
}
