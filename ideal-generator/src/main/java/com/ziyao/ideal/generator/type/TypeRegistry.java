package com.ziyao.ideal.generator.type;

import com.ziyao.ideal.generator.config.GlobalConfig;
import com.ziyao.ideal.generator.config.po.TableField;
import com.ziyao.ideal.generator.config.rules.DateType;
import com.ziyao.ideal.generator.config.rules.JavaType;
import com.ziyao.ideal.generator.config.rules.IColumnType;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * 类型注册处理类
 */
public class TypeRegistry {

    private final GlobalConfig globalConfig;

    private final Map<Integer, IColumnType> typeMap = new HashMap<>();

    public TypeRegistry(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
        // byte[]
        typeMap.put(Types.BINARY, JavaType.BYTE_ARRAY);
        typeMap.put(Types.BLOB, JavaType.BYTE_ARRAY);
        typeMap.put(Types.LONGVARBINARY, JavaType.BYTE_ARRAY);
        typeMap.put(Types.VARBINARY, JavaType.BYTE_ARRAY);
        //byte
        typeMap.put(Types.TINYINT, JavaType.BYTE);
        //long
        typeMap.put(Types.BIGINT, JavaType.LONG);
        //boolean
        typeMap.put(Types.BIT, JavaType.BOOLEAN);
        typeMap.put(Types.BOOLEAN, JavaType.BOOLEAN);
        //short
        typeMap.put(Types.SMALLINT, JavaType.SHORT);
        //string
        typeMap.put(Types.CHAR, JavaType.STRING);
        typeMap.put(Types.CLOB, JavaType.STRING);
        typeMap.put(Types.VARCHAR, JavaType.STRING);
        typeMap.put(Types.LONGVARCHAR, JavaType.STRING);
        typeMap.put(Types.LONGNVARCHAR, JavaType.STRING);
        typeMap.put(Types.NCHAR, JavaType.STRING);
        typeMap.put(Types.NCLOB, JavaType.STRING);
        typeMap.put(Types.NVARCHAR, JavaType.STRING);
        //date
        typeMap.put(Types.DATE, JavaType.DATE);
        //timestamp
        typeMap.put(Types.TIMESTAMP, JavaType.TIMESTAMP);
        typeMap.put(Types.TIMESTAMP_WITH_TIMEZONE, JavaType.TIMESTAMP);
        //double
        typeMap.put(Types.FLOAT, JavaType.DOUBLE);
        typeMap.put(Types.REAL, JavaType.DOUBLE);
        typeMap.put(Types.DOUBLE, JavaType.DOUBLE);
        //int
        typeMap.put(Types.INTEGER, JavaType.INTEGER);
        //bigDecimal
        typeMap.put(Types.NUMERIC, JavaType.BIG_DECIMAL);
        typeMap.put(Types.DECIMAL, JavaType.BIG_DECIMAL);
        //TODO 类型需要补充完整
    }

    public IColumnType getColumnType(TableField.MetaInfo metaInfo, JavaType defaultType) {
        //TODO 是否用包装类??? 可以尝试判断字段是否允许为null来判断是否用包装类
        int typeCode = metaInfo.getJdbcType().TYPE_CODE;
        return switch (typeCode) {
            // TODO 需要增加类型处理，尚未补充完整
            case Types.BIT -> getBitType(metaInfo);
            case Types.DATE -> getDateType(metaInfo);
            case Types.TIME -> getTimeType(metaInfo);
            case Types.DECIMAL, Types.NUMERIC -> getNumber(metaInfo);
            case Types.TIMESTAMP -> getTimestampType(metaInfo);
            default -> typeMap.getOrDefault(typeCode, defaultType);
        };
    }

    public IColumnType getColumnType(TableField.MetaInfo metaInfo) {
        return getColumnType(metaInfo, JavaType.OBJECT);
    }

    private IColumnType getBitType(TableField.MetaInfo metaInfo) {
        if (metaInfo.getLength() > 1) {
            return JavaType.BYTE_ARRAY;
        }
        return JavaType.BOOLEAN;
    }

    private IColumnType getNumber(TableField.MetaInfo metaInfo) {
        if (metaInfo.getScale() > 0 || metaInfo.getLength() > 18) {
            return typeMap.get(metaInfo.getJdbcType().TYPE_CODE);
        } else if (metaInfo.getLength() > 9) {
            return JavaType.LONG;
        } else if (metaInfo.getLength() > 4) {
            return JavaType.INTEGER;
        } else {
            return JavaType.SHORT;
        }
    }

    private IColumnType getDateType(TableField.MetaInfo metaInfo) {
        JavaType javaType;
        DateType dateType = globalConfig.getDateType();
        javaType = switch (dateType) {
            case SQL_PACK -> JavaType.DATE_SQL;
            case TIME_PACK -> JavaType.LOCAL_DATE;
            default -> JavaType.DATE;
        };
        return javaType;
    }

    private IColumnType getTimeType(TableField.MetaInfo metaInfo) {
        JavaType javaType;
        DateType dateType = globalConfig.getDateType();
        if (dateType == DateType.TIME_PACK) {
            javaType = JavaType.LOCAL_TIME;
        } else {
            javaType = JavaType.TIME;
        }
        return javaType;
    }

    private IColumnType getTimestampType(TableField.MetaInfo metaInfo) {
        JavaType javaType;
        DateType dateType = globalConfig.getDateType();
        if (dateType == DateType.TIME_PACK) {
            javaType = JavaType.LOCAL_DATE_TIME;
        } else if (dateType == DateType.ONLY_DATE) {
            javaType = JavaType.DATE;
        } else {
            javaType = JavaType.TIMESTAMP;
        }
        return javaType;
    }

}
