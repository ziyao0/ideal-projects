package com.ziyao.ideal.generator.config.querys;

import com.ziyao.ideal.generator.config.IDbQuery;
import com.ziyao.ideal.generator.mybatisplus.DbType;

import java.util.EnumMap;
import java.util.Map;

public class DbQueryRegistry {

    private final Map<DbType, IDbQuery> db_query_enum_map = new EnumMap<>(DbType.class);

    public DbQueryRegistry() {

        db_query_enum_map.put(DbType.MARIADB, new MariadbQuery());
        db_query_enum_map.put(DbType.SQLITE, new SqliteQuery());
        db_query_enum_map.put(DbType.MYSQL, new MySqlQuery());

    }

    public IDbQuery getDbQuery(DbType dbType) {
        return db_query_enum_map.get(dbType);
    }
}
