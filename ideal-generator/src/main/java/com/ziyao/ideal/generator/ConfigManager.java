package com.ziyao.ideal.generator;

import com.ziyao.ideal.core.Collections;
import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.core.lang.Nullable;
import com.ziyao.ideal.generator.config.*;
import com.ziyao.ideal.generator.config.builder.GeneratorBuilder;
import com.ziyao.ideal.generator.database.query.DefaultMetaInfoQuery;
import com.ziyao.ideal.generator.database.query.MetadataQuery;
import com.ziyao.ideal.generator.metadata.MetaInfo;
import com.ziyao.ideal.generator.template.TemplateStrategy;
import lombok.Getter;

import java.util.*;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public class ConfigManager {

    private final List<MetaInfo> metaInfos = new ArrayList<>();
    private final Map<OutputFile, String> pathInfo = new HashMap<>();
    /**
     * 全局配置信息
     */
    private GlobalConfig globalConfig;
    /**
     * 策略配置信息
     */
    private TemplateStrategy strategy;
    /**
     * 数据库配置信息
     */
    private final DataSourceConfig dataSourceConfig;

    /**
     * 包配置信息
     */
    private final PackageConfig packageConfig;

    private final MetadataQuery metadataQuery;


    public ConfigManager(@Nullable PackageConfig packageConfig,
                         @NonNull DataSourceConfig dataSourceConfig,
                         @Nullable TemplateStrategy strategy,
                         @Nullable GlobalConfig globalConfig) {
        this.dataSourceConfig = dataSourceConfig;
        this.strategy = strategy;
        this.globalConfig = Optional.ofNullable(globalConfig).orElseGet(GeneratorBuilder::globalConfig);
        this.packageConfig = Optional.ofNullable(packageConfig).orElseGet(GeneratorBuilder::packageConfig);
        this.metadataQuery = new DefaultMetaInfoQuery(this);
        if (Collections.isEmpty(this.getMetaInfos())) {
            this.metaInfos.addAll(this.metadataQuery.query());
        }
    }


}
