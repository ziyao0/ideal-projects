package com.ziyao.ideal.generator.mysql;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.generator.core.DataType;
import com.ziyao.ideal.generator.core.meta.Column;
import com.ziyao.ideal.generator.core.meta.Table;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public class SqlScriptExecutor {

    private static final Logger log = LoggerFactory.getLogger(SqlScriptExecutor.class);

    private final Connection connection;
    private final DatabaseMetaData metadata;
    private final String schema;
    private final String catalog;

    public SqlScriptExecutor(String url, String username, String password) {
        try {
            this.connection = ConnectionInitializer.initialize(url, username, password);
            this.metadata = connection.getMetaData();
            this.schema = this.connection.getSchema();
            this.catalog = connection.getCatalog();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void execute(String tableName) throws SQLException {
        ResultSet primaryKeysResultSet = metadata.getColumns(this.catalog, this.schema, tableName, "%");
        System.out.println(primaryKeysResultSet);
        while (primaryKeysResultSet.next()) {
            String columnName = primaryKeysResultSet.getString("COLUMN_NAME");
            primaryKeysResultSet.getString("TYPE_NAME");
            System.out.println(columnName);

        }
    }

    public List<Column> getColumns(String tableName) {
        try {
            List<String> primaryKeyNames = new ArrayList<>();
            ResultSet primaryKeys = metadata.getPrimaryKeys(this.catalog, this.schema, tableName);

            while (primaryKeys.next()) {
                primaryKeyNames.add(primaryKeys.getString("COLUMN_NAME"));
            }

            List<Column> columns = new ArrayList<>();
            ResultSet resultSet = metadata.getColumns(this.catalog, this.schema, tableName, "%");
            while (resultSet.next()) {
                columns.add(createColumn(primaryKeyNames, resultSet));
            }
            return List.copyOf(columns);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Column createColumn(List<String> primaryKeyNames, ResultSet resultSet) throws SQLException {
        String columnName = resultSet.getString("COLUMN_NAME");
        String typeName = resultSet.getString("TYPE_NAME");

        int dataType = resultSet.getInt("DATA_TYPE");
        DataType type = DataType.forCode(dataType);
        if (type == null) {
            // 不标准的类型,统一转为OTHER
            type = DataType.OTHER;
        }
        boolean isPrimaryKey = primaryKeyNames.contains(columnName);
        int columnSize = resultSet.getInt("COLUMN_SIZE");
        int scale = resultSet.getInt("DECIMAL_DIGITS");
        String defaultValue = resultSet.getString("COLUMN_DEF");
        boolean nullable = resultSet.getInt("NULLABLE") == DatabaseMetaData.columnNullable;
        boolean generatedColumn = isGeneratedOrAutoIncrementColumn(resultSet, "IS_GENERATEDCOLUMN");
        boolean autoIncrement = isGeneratedOrAutoIncrementColumn(resultSet, "IS_AUTOINCREMENT");
        String comment = formatComment(resultSet.getString("REMARKS"));

        return Column.builder().name(columnName)
                .primary(isPrimaryKey)
                .autoIncrement(autoIncrement)
                .defaultValue(defaultValue)
                .dataType(type)
                .nullable(nullable)
                .generatedColumn(generatedColumn)
                .scale(scale)
                .length(columnSize)
                .typeName(typeName)
                .comment(comment)
                .build();
    }

    public List<Table> getTables(String tableNamePattern, boolean isView) throws SQLException {
        ResultSet resultSet = metadata.getTables(this.catalog, this.schema, tableNamePattern,
                isView ? new String[]{"TABLE", "VIEW"} : new String[]{"TABLE"});

        List<Table> tables = new ArrayList<>();
        while (resultSet.next()) {
            String tableName = resultSet.getString("TABLE_NAME");
            String typeName = resultSet.getString("TYPE_NAME");
            String remarks = resultSet.getString("REMARKS");
            tables.add(Table.of(tableName, remarks, typeName));
        }
        return tables;
    }

    private boolean isGeneratedOrAutoIncrementColumn(ResultSet resultSet, String columnLabel) {
        try {
            return "YES".equals(resultSet.getString(columnLabel));
        } catch (SQLException e) {
            // ignore
        }
        return false;
    }

    public String formatComment(String comment) {
        return Strings.isEmpty(comment) ? Strings.EMPTY : comment.replaceAll("\r\n", "\t");
    }

    public void releaseConnection() {
        Optional.ofNullable(connection).ifPresent((con) -> {
            try {
                con.close();
            } catch (SQLException e) {
                log.error("Error closing connection", e);
            }
        });
    }
}
