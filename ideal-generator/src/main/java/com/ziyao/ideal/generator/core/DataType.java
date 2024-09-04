package com.ziyao.ideal.generator.core;

import lombok.Getter;

import java.sql.Types;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Getter
public enum DataType {
    BINARY(Types.BINARY, "byte[]", null),
    BLOB(Types.BLOB, "byte[]", null),
    LONG_VARBINARY(Types.LONGVARBINARY, "byte[]", null),
    VARBINARY(Types.VARBINARY, "byte[]", null),
    //byte
    TINYINT(Types.TINYINT, "Byte", null),
    //long
    BIGINT(Types.BIGINT, "Long", null),
    //boolean
    BIT(Types.BIT, "Boolean", null),
    BOOLEAN(Types.BOOLEAN, "Boolean", null),
    //short
    SMALLINT(Types.SMALLINT, "Short", null),
    //string
    CHAR(Types.CHAR, "String", null),
    CLOB(Types.CLOB, "String", null),
    VARCHAR(Types.VARCHAR, "String", null),
    LONG_VARCHAR(Types.LONGVARCHAR, "String", null),
    LONG_NVARCHAR(Types.LONGNVARCHAR, "String", null),
    NCHAR(Types.NCHAR, "String", null),
    NCLOB(Types.NCLOB, "String", null),
    NVARCHAR(Types.NVARCHAR, "String", null),
    //date
    DATE(Types.DATE, "Date", "java.util.Date"),
    //timestamp
//    TIMESTAMP(Types.TIMESTAMP, "Timestamp", "java.sql.Timestamp"),
    TIMESTAMP(Types.TIMESTAMP, "LocalDateTime", "java.time.LocalDateTime"),
    TIMESTAMP_WITH_TIMEZONE(Types.TIMESTAMP_WITH_TIMEZONE, "Timestamp", "java.sql.Timestamp"),
    //double
    FLOAT(Types.FLOAT, "Double", null),
    REAL(Types.REAL, "Double", null),
    DOUBLE(Types.DOUBLE, "Double", null),
    //int
    INTEGER(Types.INTEGER, "Integer", null),
    //bigDecimal
    NUMERIC(Types.NUMERIC, "BigDecimal", "java.math.BigDecimal"),
    DECIMAL(Types.DECIMAL, "BigDecimal", "java.math.BigDecimal"),
    OTHER(Types.OTHER, "String", null),
    ;

    private final int jdbcType;
    private final String javaType;
    private final String pkg;

    DataType(int jdbcType, String javaType, String pkg) {
        this.jdbcType = jdbcType;
        this.javaType = javaType;
        this.pkg = pkg;
    }

    private static final Map<Integer, DataType> TYPE_MAP;

    static {
        TYPE_MAP = Arrays.stream(values())
                .collect(Collectors.toMap(DataType::getJdbcType, Function.identity()));
    }

    public static DataType forCode(int jdbcType) {
        return TYPE_MAP.get(jdbcType);
    }
}
