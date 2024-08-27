package com.ziyao.ideal.generator.config;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.core.lang.Nullable;
import com.ziyao.ideal.generator.config.builder.*;
import com.ziyao.ideal.generator.config.po.LikeTable;
import lombok.Getter;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 策略配置项
 */
public class StrategyConfig {

    private StrategyConfig() {
    }

    /**
     * 是否大写命名（默认 false）
     */
    private boolean isCapitalMode;

    /**
     * 是否跳过视图（默认 false）
     */
    @Getter
    private boolean skipView;

    /**
     * 过滤表前缀
     * example: addTablePrefix("t_")
     * result: t_simple -> Simple
     */
    private final Set<String> tablePrefix = new HashSet<>();

    /**
     * 过滤表后缀
     * example: addTableSuffix("_0")
     * result: t_simple_0 -> Simple
     */
    private final Set<String> tableSuffix = new HashSet<>();

    /**
     * 过滤字段前缀
     * example: addFieldPrefix("is_")
     * result: is_deleted -> deleted
     */
    private final Set<String> fieldPrefix = new HashSet<>();

    /**
     * 过滤字段后缀
     * example: addFieldSuffix("_flag")
     * result: deleted_flag -> deleted
     */
    private final Set<String> fieldSuffix = new HashSet<>();

    /**
     * 需要包含的表名，允许正则表达式（与exclude二选一配置）<br/>
     * 当{@link #enableSqlFilter}为true时，正则表达式无效.
     */
    private final Set<String> include = new HashSet<>();

    /**
     * 需要排除的表名，允许正则表达式<br/>
     * 当{@link #enableSqlFilter}为true时，正则表达式无效.
     */
    private final Set<String> exclude = new HashSet<>();

    /**
     * 启用sql过滤，语法不能支持使用sql过滤表的话，可以考虑关闭此开关.
     */
    @Getter
    private boolean enableSqlFilter = true;

    /**
     * 启用 schema 默认 false
     */
    @Getter
    private boolean enableSchema;

    /**
     * 包含表名
     */
    private LikeTable likeTable;

    /**
     * 不包含表名
     * <p>
     * 只在{@link com.ziyao.ideal.generator.query.SQLQuery}模式下生效.
     * </p>
     */
    private LikeTable notLikeTable;

    private final Entity.Builder entityBuilder = new Entity.Builder(this);

    private final Controller.Builder controllerBuilder = new Controller.Builder(this);

    private final Mapper.Builder mapperBuilder = new Mapper.Builder(this);

    private final Service.Builder serviceBuilder = new Service.Builder(this);

    private final Repository.Builder repositoryBuilder = new Repository.Builder(this);

    private Entity entity;

    private Controller controller;

    private Mapper mapper;

    private Service service;

    private Repository repository;

    private IOutputFile outputFile = (path, ot) -> new File(path);

    /**
     * 实体配置构建者
     *
     * @return 实体配置构建者
     */
    @NonNull
    public Entity.Builder entityBuilder() {
        return entityBuilder;
    }

    /**
     * 实体配置
     *
     * @return 实体配置
     */
    @NonNull
    public Entity entity() {
        if (entity == null) {
            this.entity = entityBuilder.get();
        }
        return entity;
    }

    /**
     * 控制器配置构建者
     *
     * @return 控制器配置构建者
     */
    @NonNull
    public Controller.Builder controllerBuilder() {
        return controllerBuilder;
    }

    /**
     * 控制器配置
     *
     * @return 控制器配置
     */
    @NonNull
    public Controller controller() {
        if (controller == null) {
            this.controller = controllerBuilder.get();
        }
        return controller;
    }

    /**
     * Mapper配置构建者
     *
     * @return Mapper配置构建者
     */
    @NonNull
    public Mapper.Builder mapperBuilder() {
        return mapperBuilder;
    }

    /**
     * Mapper配置
     *
     * @return Mapper配置
     */
    @NonNull
    public Mapper mapper() {
        if (mapper == null) {
            this.mapper = mapperBuilder.get();
        }
        return mapper;
    }

    /**
     * Service配置构建者
     *
     * @return Service配置构建者
     */
    @NonNull
    public Service.Builder serviceBuilder() {
        return serviceBuilder;
    }

    /**
     * Service配置
     *
     * @return Service配置
     */
    @NonNull
    public Service service() {
        if (service == null) {
            this.service = serviceBuilder.get();
        }
        return service;
    }

    public Repository.Builder repositoryBuilder() {
        return repositoryBuilder;
    }

    public Repository repository() {
        if (repository == null) {
            this.repository = repositoryBuilder.get();
        }
        return repository;
    }

    /**
     * 大写命名、字段符合大写字母数字下划线命名
     *
     * @param word 待判断字符串
     */
    public boolean isCapitalModeNaming(@NonNull String word) {
        return isCapitalMode && Strings.isCapitalMode(word);
    }

    /**
     * 表名称匹配过滤表前缀
     *
     * @param tableName 表名称
     */
    public boolean startsWithTablePrefix(@NonNull String tableName) {
        return this.tablePrefix.stream().anyMatch(tableName::startsWith);
    }

    /**
     * 验证配置项
     */
    public void validate() {
        boolean isInclude = !this.getInclude().isEmpty();
        boolean isExclude = !this.getExclude().isEmpty();
        if (isInclude && isExclude) {
            throw new IllegalArgumentException("<strategy> 标签中 <include> 与 <exclude> 只能配置一项！");
        }
        if (this.getNotLikeTable() != null && this.getLikeTable() != null) {
            throw new IllegalArgumentException("<strategy> 标签中 <likeTable> 与 <notLikeTable> 只能配置一项！");
        }
    }

    /**
     * 包含表名匹配
     *
     * @param tableName 表名
     * @return 是否匹配
     */
    public boolean matchIncludeTable(@NonNull String tableName) {
        return matchTable(tableName, this.getInclude());
    }

    /**
     * 排除表名匹配
     *
     * @param tableName 表名
     * @return 是否匹配
     */
    public boolean matchExcludeTable(@NonNull String tableName) {
        return matchTable(tableName, this.getExclude());
    }

    /**
     * 表名匹配
     *
     * @param tableName   表名
     * @param matchTables 匹配集合
     * @return 是否匹配
     */
    private boolean matchTable(@NonNull String tableName, @NonNull Set<String> matchTables) {
        return matchTables.stream().anyMatch(t -> tableNameMatches(t, tableName));
    }

    /**
     * 表名匹配
     *
     * @param matchTableName 匹配表名
     * @param dbTableName    数据库表名
     * @return 是否匹配
     */
    private boolean tableNameMatches(@NonNull String matchTableName, @NonNull String dbTableName) {
        return matchTableName.equalsIgnoreCase(dbTableName) || Strings.matches(matchTableName, dbTableName);
    }

    public boolean isCapitalMode() {
        return isCapitalMode;
    }

    @NonNull
    public Set<String> getTablePrefix() {
        return tablePrefix;
    }

    @NonNull
    public Set<String> getTableSuffix() {
        return tableSuffix;
    }

    @NonNull
    public Set<String> getFieldPrefix() {
        return fieldPrefix;
    }

    @NonNull
    public Set<String> getFieldSuffix() {
        return fieldSuffix;
    }

    @NonNull
    public Set<String> getInclude() {
        return include;
    }

    @NonNull
    public Set<String> getExclude() {
        return exclude;
    }

    @Nullable
    public LikeTable getLikeTable() {
        return likeTable;
    }

    @Nullable
    public LikeTable getNotLikeTable() {
        return notLikeTable;
    }

    @NonNull
    public IOutputFile getOutputFile() {
        return outputFile;
    }

    /**
     * 策略配置构建者
     */
    public static class Builder extends BaseBuilder {

        private final StrategyConfig strategyConfig;

        public Builder() {
            super(new StrategyConfig());
            strategyConfig = super.build();
        }

        /**
         * 开启大写命名
         *
         * @return this
         */
        public Builder enableCapitalMode() {
            this.strategyConfig.isCapitalMode = true;
            return this;
        }

        /**
         * 开启跳过视图
         *
         * @return this
         */
        public Builder enableSkipView() {
            this.strategyConfig.skipView = true;
            return this;
        }

        /**
         * 禁用sql过滤
         *
         * @return this
         */
        public Builder disableSqlFilter() {
            this.strategyConfig.enableSqlFilter = false;
            return this;
        }

        /**
         * 启用 schema
         *
         * @return this
         */
        public Builder enableSchema() {
            this.strategyConfig.enableSchema = true;
            return this;
        }

        /**
         * 增加过滤表前缀
         *
         * @param tablePrefix 过滤表前缀
         * @return this
         */
        public Builder addTablePrefix(@NonNull String... tablePrefix) {
            return addTablePrefix(Arrays.asList(tablePrefix));
        }

        public Builder addTablePrefix(@NonNull List<String> tablePrefixList) {
            this.strategyConfig.tablePrefix.addAll(tablePrefixList);
            return this;
        }

        /**
         * 增加过滤表后缀
         *
         * @param tableSuffix 过滤表后缀
         * @return this
         */
        public Builder addTableSuffix(String... tableSuffix) {
            return addTableSuffix(Arrays.asList(tableSuffix));
        }

        public Builder addTableSuffix(@NonNull List<String> tableSuffixList) {
            this.strategyConfig.tableSuffix.addAll(tableSuffixList);
            return this;
        }

        /**
         * 增加过滤字段前缀
         *
         * @param fieldPrefix 过滤字段前缀
         * @return this
         */
        public Builder addFieldPrefix(@NonNull String... fieldPrefix) {
            return addFieldPrefix(Arrays.asList(fieldPrefix));
        }

        public Builder addFieldPrefix(@NonNull List<String> fieldPrefix) {
            this.strategyConfig.fieldPrefix.addAll(fieldPrefix);
            return this;
        }

        /**
         * 增加过滤字段后缀
         *
         * @param fieldSuffix 过滤字段后缀
         * @return this
         */
        public Builder addFieldSuffix(@NonNull String... fieldSuffix) {
            return addFieldSuffix(Arrays.asList(fieldSuffix));
        }

        public Builder addFieldSuffix(@NonNull List<String> fieldSuffixList) {
            this.strategyConfig.fieldSuffix.addAll(fieldSuffixList);
            return this;
        }

        /**
         * 增加包含的表名
         *
         * @param include 包含表
         * @return this
         */
        public Builder addInclude(@NonNull String... include) {
            this.strategyConfig.include.addAll(Arrays.asList(include));
            return this;
        }

        public Builder addInclude(@NonNull List<String> includes) {
            this.strategyConfig.include.addAll(includes);
            return this;
        }

        public Builder addInclude(@NonNull String include) {
            this.strategyConfig.include.addAll(Arrays.asList(include.split(",")));
            return this;
        }

        /**
         * 增加排除表
         *
         * @param exclude 排除表
         * @return this
         */
        public Builder addExclude(@NonNull String... exclude) {
            return addExclude(Arrays.asList(exclude));
        }

        public Builder addExclude(@NonNull List<String> excludeList) {
            this.strategyConfig.exclude.addAll(excludeList);
            return this;
        }

        /**
         * 包含表名
         *
         * @return this
         */
        public Builder likeTable(@NonNull LikeTable likeTable) {
            this.strategyConfig.likeTable = likeTable;
            return this;
        }

        /**
         * 不包含表名
         *
         * @return this
         */
        public Builder notLikeTable(@NonNull LikeTable notLikeTable) {
            this.strategyConfig.notLikeTable = notLikeTable;
            return this;
        }

        /**
         * 输出文件处理
         *
         * @return this
         */
        public Builder outputFile(@NonNull IOutputFile outputFile) {
            this.strategyConfig.outputFile = outputFile;
            return this;
        }

        @Override
        @NonNull
        public StrategyConfig build() {
            this.strategyConfig.validate();
            return strategyConfig;
        }
    }
}
