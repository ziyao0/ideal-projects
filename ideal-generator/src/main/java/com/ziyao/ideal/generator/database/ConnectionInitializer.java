package com.ziyao.ideal.generator.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */

public abstract class ConnectionInitializer {


    public static Connection initialize(String url, String user, String password) {
        synchronized (ConnectionInitializer.class) {
            try {
                Properties properties = new Properties();
                properties.put("user", user);
                properties.put("password", password);
                return DriverManager.getConnection(url, properties);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private ConnectionInitializer() {
    }
}
