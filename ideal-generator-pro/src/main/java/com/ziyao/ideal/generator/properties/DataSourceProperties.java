package com.ziyao.ideal.generator.properties;

import lombok.Getter;

import javax.sql.DataSource;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public class DataSourceProperties {
    /**
     * schemaName
     */
    private String schemaName;


    /**
     * 驱动连接的URL
     */
    private String url;

    /**
     * 数据库连接用户名
     */
    private String username;

    /**
     * 数据库连接密码
     */
    private String password;

    /**
     * 数据源实例
     */
    private DataSource dataSource;

    public static class Builder {
        private final DataSourceProperties dataSourceProperties;

        public Builder() {
            this.dataSourceProperties = new DataSourceProperties();
        }

        public Builder(String url, String username, String password) {
            this();
            this.dataSourceProperties.url = url;
            this.dataSourceProperties.username = username;
            this.dataSourceProperties.password = password;
        }

        public Builder schemaName(String schemaName) {
            this.dataSourceProperties.schemaName = schemaName;
            return this;
        }

        public Builder dataSource(DataSource dataSource) {
            this.dataSourceProperties.dataSource = dataSource;
            return this;
        }

        public DataSourceProperties build() {
            return dataSourceProperties;
        }
    }
}
