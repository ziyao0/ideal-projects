package com.ziyao.ideal.generator.config.builder;

import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.core.lang.Nullable;
import com.ziyao.ideal.generator.config.*;
import com.ziyao.ideal.generator.config.po.TableInfo;
import com.ziyao.ideal.generator.query.IDatabaseQuery;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 配置汇总 传递给文件生成工具
 */
public class ConfigBuilder {

    /**
     * 数据库表信息
     */
    private final List<TableInfo> tableInfoList = new ArrayList<>();

    /**
     * 路径配置信息
     */
    private final Map<OutputFile, String> pathInfo = new HashMap<>();

    /**
     * 策略配置信息
     */
    private StrategyConfig strategyConfig;

    /**
     * 全局配置信息
     */
    private GlobalConfig globalConfig;

    /**
     * 注入配置信息
     */
    private InjectionConfig injectionConfig;

    /**
     * 过滤正则
     */
    private static final Pattern REGX = Pattern.compile("[~!/@#$%^&*()+\\\\\\[\\]|{};:'\",<.>?]+");

    /**
     * 包配置信息
     */
    private final PackageConfig packageConfig;

    /**
     * 数据库配置信息
     */
    private final DataSourceConfig dataSourceConfig;

    /**
     * 数据查询实例
     */
    private final IDatabaseQuery databaseQuery;

    /**
     * 在构造器中处理配置
     *
     * @param packageConfig    包配置
     * @param dataSourceConfig 数据源配置
     * @param strategyConfig   表配置
     * @param globalConfig     全局配置
     */
    public ConfigBuilder(@Nullable PackageConfig packageConfig, @NonNull DataSourceConfig dataSourceConfig,
                         @Nullable StrategyConfig strategyConfig, @Nullable GlobalConfig globalConfig,
                         @Nullable InjectionConfig injectionConfig) {
        this.dataSourceConfig = dataSourceConfig;
        this.strategyConfig = Optional.ofNullable(strategyConfig).orElseGet(GeneratorBuilder::strategyConfig);
        this.globalConfig = Optional.ofNullable(globalConfig).orElseGet(GeneratorBuilder::globalConfig);
        this.packageConfig = Optional.ofNullable(packageConfig).orElseGet(GeneratorBuilder::packageConfig);
        this.injectionConfig = Optional.ofNullable(injectionConfig).orElseGet(GeneratorBuilder::injectionConfig);
        this.pathInfo.putAll(new PathInfoHandler(this.globalConfig, this.strategyConfig, this.packageConfig).getPathInfo());
        Class<? extends IDatabaseQuery> databaseQueryClass = dataSourceConfig.getDatabaseQueryClass();
        try {
            Constructor<? extends IDatabaseQuery> declaredConstructor = databaseQueryClass.getDeclaredConstructor(this.getClass());
            this.databaseQuery = declaredConstructor.newInstance(this);
        } catch (ReflectiveOperationException exception) {
            throw new RuntimeException("创建IDatabaseQuery实例出现错误:", exception);
        }
    }

    /**
     * 判断表名是否为正则表名(这表名规范比较随意,只能尽量匹配上特殊符号)
     *
     * @param tableName 表名
     * @return 是否正则
     */
    public static boolean matcherRegTable(@NonNull String tableName) {
        return REGX.matcher(tableName).find();
    }

    @NonNull
    public ConfigBuilder setStrategyConfig(@NonNull StrategyConfig strategyConfig) {
        this.strategyConfig = strategyConfig;
        return this;
    }

    @NonNull
    public ConfigBuilder setGlobalConfig(@NonNull GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
        return this;
    }

    @NonNull
    public ConfigBuilder setInjectionConfig(@NonNull InjectionConfig injectionConfig) {
        this.injectionConfig = injectionConfig;
        return this;
    }

    @NonNull
    public List<TableInfo> getTableInfoList() {
        if (tableInfoList.isEmpty()) {
            List<TableInfo> tableInfos = this.databaseQuery.queryTables();
            if (!tableInfos.isEmpty()) {
                this.tableInfoList.addAll(tableInfos);
            }
        }
        return tableInfoList;
    }

    @NonNull
    public Map<OutputFile, String> getPathInfo() {
        return pathInfo;
    }

    @NonNull
    public StrategyConfig getStrategyConfig() {
        return strategyConfig;
    }

    @NonNull
    public GlobalConfig getGlobalConfig() {
        return globalConfig;
    }

    @Nullable
    public InjectionConfig getInjectionConfig() {
        return injectionConfig;
    }

    @NonNull
    public PackageConfig getPackageConfig() {
        return packageConfig;
    }

    @NonNull
    public DataSourceConfig getDataSourceConfig() {
        return dataSourceConfig;
    }
}
