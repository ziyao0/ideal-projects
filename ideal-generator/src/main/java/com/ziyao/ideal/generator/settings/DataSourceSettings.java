package com.ziyao.ideal.generator.settings;

import com.ziyao.ideal.generator.ConfigurationProperties;
import lombok.Getter;

import javax.sql.DataSource;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
@ConfigurationProperties(prefix = "db")
public class DataSourceSettings {
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
        private final DataSourceSettings dataSourceSettings;

        public Builder() {
            this.dataSourceSettings = new DataSourceSettings();
        }

        public Builder(DataSourceSettings dataSourceSettings) {
            this.dataSourceSettings = dataSourceSettings;
        }

        public Builder(String url, String username, String password) {
            this();
            this.dataSourceSettings.url = url;
            this.dataSourceSettings.username = username;
            this.dataSourceSettings.password = password;
        }

        public Builder url(String url) {
            this.dataSourceSettings.url = url;
            return this;
        }

        public Builder username(String username) {
            this.dataSourceSettings.username = username;
            return this;
        }

        public Builder password(String password) {
            this.dataSourceSettings.password = password;
            return this;
        }

        public Builder schemaName(String schemaName) {
            this.dataSourceSettings.schemaName = schemaName;
            return this;
        }

        public Builder dataSource(DataSource dataSource) {
            this.dataSourceSettings.dataSource = dataSource;
            return this;
        }

        public DataSourceSettings build() {
            return dataSourceSettings;
        }
    }
}
