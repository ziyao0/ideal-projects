package com.ziyao.ideal.generator;

import com.ziyao.ideal.generator.config.rules.DbColumnType;
import lombok.Getter;

import java.sql.Types;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public enum DataType {
    BINARY(Types.BINARY, DbColumnType.BYTE_ARRAY),
    BLOB(Types.BLOB, DbColumnType.BYTE_ARRAY),
    LONGVARBINARY(Types.LONGVARBINARY, DbColumnType.BYTE_ARRAY),
    VARBINARY(Types.VARBINARY, DbColumnType.BYTE_ARRAY),
    //byte
    TINYINT(Types.TINYINT, DbColumnType.BYTE),
    //long
    BIGINT(Types.BIGINT, DbColumnType.LONG),
    //boolean
    BIT(Types.BIT, DbColumnType.BOOLEAN),
    BOOLEAN(Types.BOOLEAN, DbColumnType.BOOLEAN),
    //short
    SMALLINT(Types.SMALLINT, DbColumnType.SHORT),
    //string
    CHAR(Types.CHAR, DbColumnType.STRING),
    CLOB(Types.CLOB, DbColumnType.STRING),
    VARCHAR(Types.VARCHAR, DbColumnType.STRING),
    LONGVARCHAR(Types.LONGVARCHAR, DbColumnType.STRING),
    LONGNVARCHAR(Types.LONGNVARCHAR, DbColumnType.STRING),
    NCHAR(Types.NCHAR, DbColumnType.STRING),
    NCLOB(Types.NCLOB, DbColumnType.STRING),
    NVARCHAR(Types.NVARCHAR, DbColumnType.STRING),
    //date
    DATE(Types.DATE, DbColumnType.DATE),
    //timestamp
    TIMESTAMP(Types.TIMESTAMP, DbColumnType.TIMESTAMP),
    TIMESTAMP_WITH_TIMEZONE(Types.TIMESTAMP_WITH_TIMEZONE, DbColumnType.TIMESTAMP),
    //double
    FLOAT(Types.FLOAT, DbColumnType.DOUBLE),
    REAL(Types.REAL, DbColumnType.DOUBLE),
    DOUBLE(Types.DOUBLE, DbColumnType.DOUBLE),
    //int
    INTEGER(Types.INTEGER, DbColumnType.INTEGER),
    //bigDecimal
    NUMERIC(Types.NUMERIC, DbColumnType.BIG_DECIMAL),
    DECIMAL(Types.DECIMAL, DbColumnType.BIG_DECIMAL),
    ;

    private final int jdbcType;
    private final DbColumnType javaType;

    DataType(int jdbcType, DbColumnType javaType) {
        this.jdbcType = jdbcType;
        this.javaType = javaType;
    }
}
