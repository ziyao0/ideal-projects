package com.ziyao.ideal.generator.mysql.query;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.generator.ConfigManager;
import com.ziyao.ideal.generator.core.metadata.Column;
import com.ziyao.ideal.generator.core.metadata.MetaInfo;
import com.ziyao.ideal.generator.core.metadata.Table;
import com.ziyao.ideal.generator.mysql.SqlScriptExecutor;
import com.ziyao.ideal.generator.properties.DataSourceProperties;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */

public class DefaultMetaInfoQuery implements MetadataQuery {

    private final ConfigManager configManager;
    private final SqlScriptExecutor executor;

    public DefaultMetaInfoQuery(ConfigManager configManager) {
        this.configManager = configManager;
        DataSourceProperties dataSourceProperties = configManager.getDataSourceProperties();
        this.executor = new SqlScriptExecutor(
                dataSourceProperties.getUrl(), dataSourceProperties.getUsername(), dataSourceProperties.getPassword());
    }

    @Override
    public List<MetaInfo> query() {
        try {
            List<MetaInfo> metaInfos = new ArrayList<>();
            List<Table> tables = getTables();
            for (Table table : tables) {
                if (Strings.hasText(table.getName())) {
                    MetaInfo metaInfo = new MetaInfo(this.configManager, table.getName());
                    metaInfo.setComment(table.getComment());
                    metaInfos.add(metaInfo);
                }
            }
            filter(metaInfos);
            metaInfos.forEach(this::processField);
            return metaInfos;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            executor.releaseConnection();
        }

    }

    /**
     * 过滤表数据
     *
     * @param metaInfos 元数据信息
     */
    private void filter(List<MetaInfo> metaInfos) {

    }

    private void processField(MetaInfo metaInfo) {
        String tableName = metaInfo.getTableName();
        List<Column> columns = executor.getColumns(tableName);
        for (Column column : columns) {
            if (Strings.hasText(column.getName())) {
                metaInfo.addField(column);
            }
        }
        metaInfo.process();
    }

    private List<Table> getTables() throws SQLException {
        String tableNamePattern = null;
        return executor.getTables(null, false);
    }
}