package com.ziyao.ideal.generator.mysql.query;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.generator.ConfigSettings;
import com.ziyao.ideal.generator.core.meta.Column;
import com.ziyao.ideal.generator.core.meta.Table;
import com.ziyao.ideal.generator.core.meta.TemplateContext;
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
    public List<TemplateContext> query() {
        try {
            List<TemplateContext> templateContexts = new ArrayList<>();
            List<Table> tables = getTables();
            for (Table table : tables) {
                if (Strings.hasText(table.getName())) {
                    TemplateContext templateContext = new TemplateContext(this.configSettings, table.getName());
                    templateContext.setComment(table.getComment());
                    templateContexts.add(templateContext);
                }
            }
            filter(templateContexts);
            templateContexts.forEach(this::processField);
            return templateContexts;
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
    private void filter(List<TemplateContext> contexts) {
        StrategySettings strategySettings = this.configSettings.getStrategySettings();
        Set<String> excludes = strategySettings.getExcludes();
        Set<String> includes = strategySettings.getIncludes();
        contexts.removeIf(context -> !includes.contains(context.getTableName()));
        contexts.removeIf(context -> excludes.contains(context.getTableName()));
    }

    private void processField(TemplateContext templateContext) {
        String tableName = templateContext.getTableName();
        List<Column> columns = executor.getColumns(tableName);
        for (Column column : columns) {
            if (Strings.hasText(column.getName())) {
                templateContext.addField(column);
            }
        }
        templateContext.process();
    }

    private List<Table> getTables() throws SQLException {
        String tableNamePattern = null;
        return executor.getTables(null, false);
    }
}
