package com.ziyao.ideal.generator;

import com.ziyao.ideal.core.Collections;
import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.core.lang.Nullable;
import com.ziyao.ideal.generator.core.OutputType;
import com.ziyao.ideal.generator.core.metadata.MetaInfo;
import com.ziyao.ideal.generator.mysql.query.DefaultMetaInfoQuery;
import com.ziyao.ideal.generator.mysql.query.MetadataQuery;
import com.ziyao.ideal.generator.properties.DataSourceProperties;
import com.ziyao.ideal.generator.properties.GlobalProperties;
import com.ziyao.ideal.generator.properties.PackageProperties;
import com.ziyao.ideal.generator.properties.StrategyProperties;
import com.ziyao.ideal.generator.utils.ConfigBuilderUtils;
import com.ziyao.ideal.generator.utils.OutputFileUtils;
import lombok.Getter;

import java.util.*;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public class ConfigManager {

    private final List<MetaInfo> metaInfos = new ArrayList<>();
    private final Map<OutputType, String> pathInfo = new HashMap<>();
    /**
     * 全局配置信息
     */
    private GlobalProperties globalProperties;
    /**
     * 策略配置信息
     */
    private StrategyProperties strategyProperties;
    /**
     * 数据库配置信息
     */
    private final DataSourceProperties dataSourceProperties;

    /**
     * 包配置信息
     */
    private final PackageProperties packageProperties;

    private final MetadataQuery metadataQuery;

    private final Map<OutputType, String> outputPackages = new HashMap<>();


    public ConfigManager( @Nullable GlobalProperties globalProperties,
                          @NonNull DataSourceProperties dataSourceProperties,
                          @Nullable StrategyProperties strategyProperties,
                          @Nullable PackageProperties packageProperties
                        ) {
        this.dataSourceProperties = dataSourceProperties;
        this.strategyProperties = Optional.ofNullable(strategyProperties).orElseGet(ConfigBuilderUtils::strategyProperties);
        this.globalProperties = Optional.ofNullable(globalProperties).orElseGet(ConfigBuilderUtils::globalProperties);
        this.packageProperties = Optional.ofNullable(packageProperties).orElseGet(ConfigBuilderUtils::packageProperties);
        // 初始化输出路径
        outputPackages.putAll(OutputFileUtils.initialize(globalProperties, strategyProperties, packageProperties));
        this.metadataQuery = new DefaultMetaInfoQuery(this);
        if (Collections.isEmpty(this.getMetaInfos())) {
            this.metaInfos.addAll(this.metadataQuery.query());
        }
    }


}
