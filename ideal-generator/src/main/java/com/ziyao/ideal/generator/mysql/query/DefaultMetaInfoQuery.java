package com.ziyao.ideal.generator.mysql.query;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.generator.ConfigSettings;
import com.ziyao.ideal.generator.core.meta.Column;
import com.ziyao.ideal.generator.core.meta.Table;
import com.ziyao.ideal.generator.core.GeneratorContext;
import com.ziyao.ideal.generator.mysql.SqlScriptExecutor;
import com.ziyao.ideal.generator.settings.DataSourceSettings;
import com.ziyao.ideal.generator.settings.StrategySettings;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */

public class DefaultMetaInfoQuery implements MetadataQuery {

    private final ConfigSettings configSettings;
    private final SqlScriptExecutor executor;

    public DefaultMetaInfoQuery(ConfigSettings configSettings) {
        this.configSettings = configSettings;
        DataSourceSettings dataSourceSettings = configSettings.getDataSourceSettings();
        this.executor = new SqlScriptExecutor(
                dataSourceSettings.getUrl(), dataSourceSettings.getUsername(), dataSourceSettings.getPassword());
    }

    @Override
    public List<GeneratorContext> query() {
        try {
            List<GeneratorContext> generatorContexts = new ArrayList<>();
            List<Table> tables = getTables();
            for (Table table : tables) {
                if (Strings.hasText(table.getName())) {
                    GeneratorContext generatorContext = new GeneratorContext(this.configSettings, table.getName());
                    generatorContext.setComment(table.getComment());
                    generatorContexts.add(generatorContext);
                }
            }
            filter(generatorContexts);
            generatorContexts.forEach(this::processField);
            return generatorContexts;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            executor.releaseConnection();
        }

    }

    /**
     * 过滤表数据
     *
     * @param contexts 元数据信息
     */
    private void filter(List<GeneratorContext> contexts) {
        StrategySettings strategySettings = this.configSettings.getStrategySettings();
        Set<String> excludes = strategySettings.getExcludes();
        Set<String> includes = strategySettings.getIncludes();
        contexts.removeIf(context -> !includes.contains(context.getTableName()));
        contexts.removeIf(context -> excludes.contains(context.getTableName()));
    }

    private void processField(GeneratorContext generatorContext) {
        String tableName = generatorContext.getTableName();
        List<Column> columns = executor.getColumns(tableName);
        for (Column column : columns) {
            if (Strings.hasText(column.getName())) {
                generatorContext.addField(column);
            }
        }
        generatorContext.process();
    }

    private List<Table> getTables() throws SQLException {
        String tableNamePattern = null;
        return executor.getTables(null, false);
    }
}
