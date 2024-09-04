package com.ziyao.ideal.generator;

import com.ziyao.ideal.core.Collections;
import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.core.lang.Nullable;
import com.ziyao.ideal.generator.core.OutputType;
import com.ziyao.ideal.generator.core.GeneratorContext;
import com.ziyao.ideal.generator.mysql.query.DefaultMetaInfoQuery;
import com.ziyao.ideal.generator.mysql.query.MetadataQuery;
import com.ziyao.ideal.generator.settings.DataSourceSettings;
import com.ziyao.ideal.generator.settings.GlobalSettings;
import com.ziyao.ideal.generator.settings.PackageSettings;
import com.ziyao.ideal.generator.settings.StrategySettings;
import com.ziyao.ideal.generator.utils.SettingsUtils;
import com.ziyao.ideal.generator.utils.OutputFileUtils;
import lombok.Getter;

import java.util.*;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public class ConfigSettings {

    private final List<GeneratorContext> generatorContexts = new ArrayList<>();

    /**
     * 全局配置信息
     */
    private final GlobalSettings globalSettings;
    /**
     * 策略配置信息
     */
    private final StrategySettings strategySettings;
    /**
     * 数据库配置信息
     */
    private final DataSourceSettings dataSourceSettings;

    /**
     * 包配置信息
     */
    private final PackageSettings packageSettings;

    private final MetadataQuery metadataQuery;

    private final Map<OutputType, String> outputPackages = new HashMap<>();


    public ConfigSettings(@Nullable GlobalSettings globalSettings,
                          @NonNull DataSourceSettings dataSourceSettings,
                          @Nullable StrategySettings strategySettings,
                          @Nullable PackageSettings packageSettings) {
        this.dataSourceSettings = dataSourceSettings;
        this.strategySettings = Optional.ofNullable(strategySettings).orElseGet(SettingsUtils::strategyProperties);
        this.globalSettings = Optional.ofNullable(globalSettings).orElseGet(SettingsUtils::globalProperties);
        this.packageSettings = Optional.ofNullable(packageSettings).orElseGet(SettingsUtils::packageProperties);
        // 初始化输出路径
        outputPackages.putAll(OutputFileUtils.initialize(globalSettings, strategySettings, packageSettings));
        this.metadataQuery = new DefaultMetaInfoQuery(this);
        if (Collections.isEmpty(this.getGeneratorContexts())) {
            this.generatorContexts.addAll(this.metadataQuery.query());
        }
    }

}
