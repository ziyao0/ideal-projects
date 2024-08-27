package com.ziyao.ideal.generator.type;

import com.ziyao.ideal.generator.config.GlobalConfig;
import com.ziyao.ideal.generator.config.po.TableField;
import com.ziyao.ideal.generator.config.rules.DateType;
import com.ziyao.ideal.generator.config.rules.DbColumnType;
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
        typeMap.put(Types.BINARY, DbColumnType.BYTE_ARRAY);
        typeMap.put(Types.BLOB, DbColumnType.BYTE_ARRAY);
        typeMap.put(Types.LONGVARBINARY, DbColumnType.BYTE_ARRAY);
        typeMap.put(Types.VARBINARY, DbColumnType.BYTE_ARRAY);
        //byte
        typeMap.put(Types.TINYINT, DbColumnType.BYTE);
        //long
        typeMap.put(Types.BIGINT, DbColumnType.LONG);
        //boolean
        typeMap.put(Types.BIT, DbColumnType.BOOLEAN);
        typeMap.put(Types.BOOLEAN, DbColumnType.BOOLEAN);
        //short
        typeMap.put(Types.SMALLINT, DbColumnType.SHORT);
        //string
        typeMap.put(Types.CHAR, DbColumnType.STRING);
        typeMap.put(Types.CLOB, DbColumnType.STRING);
        typeMap.put(Types.VARCHAR, DbColumnType.STRING);
        typeMap.put(Types.LONGVARCHAR, DbColumnType.STRING);
        typeMap.put(Types.LONGNVARCHAR, DbColumnType.STRING);
        typeMap.put(Types.NCHAR, DbColumnType.STRING);
        typeMap.put(Types.NCLOB, DbColumnType.STRING);
        typeMap.put(Types.NVARCHAR, DbColumnType.STRING);
        //date
        typeMap.put(Types.DATE, DbColumnType.DATE);
        //timestamp
        typeMap.put(Types.TIMESTAMP, DbColumnType.TIMESTAMP);
        typeMap.put(Types.TIMESTAMP_WITH_TIMEZONE, DbColumnType.TIMESTAMP);
        //double
        typeMap.put(Types.FLOAT, DbColumnType.DOUBLE);
        typeMap.put(Types.REAL, DbColumnType.DOUBLE);
        typeMap.put(Types.DOUBLE, DbColumnType.DOUBLE);
        //int
        typeMap.put(Types.INTEGER, DbColumnType.INTEGER);
        //bigDecimal
        typeMap.put(Types.NUMERIC, DbColumnType.BIG_DECIMAL);
        typeMap.put(Types.DECIMAL, DbColumnType.BIG_DECIMAL);
        //TODO 类型需要补充完整
    }

    public IColumnType getColumnType(TableField.MetaInfo metaInfo, DbColumnType defaultType) {
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
        return getColumnType(metaInfo, DbColumnType.OBJECT);
    }

    private IColumnType getBitType(TableField.MetaInfo metaInfo) {
        if (metaInfo.getLength() > 1) {
            return DbColumnType.BYTE_ARRAY;
        }
        return DbColumnType.BOOLEAN;
    }

    private IColumnType getNumber(TableField.MetaInfo metaInfo) {
        if (metaInfo.getScale() > 0 || metaInfo.getLength() > 18) {
            return typeMap.get(metaInfo.getJdbcType().TYPE_CODE);
        } else if (metaInfo.getLength() > 9) {
            return DbColumnType.LONG;
        } else if (metaInfo.getLength() > 4) {
            return DbColumnType.INTEGER;
        } else {
            return DbColumnType.SHORT;
        }
    }

    private IColumnType getDateType(TableField.MetaInfo metaInfo) {
        DbColumnType dbColumnType;
        DateType dateType = globalConfig.getDateType();
        dbColumnType = switch (dateType) {
            case SQL_PACK -> DbColumnType.DATE_SQL;
            case TIME_PACK -> DbColumnType.LOCAL_DATE;
            default -> DbColumnType.DATE;
        };
        return dbColumnType;
    }

    private IColumnType getTimeType(TableField.MetaInfo metaInfo) {
        DbColumnType dbColumnType;
        DateType dateType = globalConfig.getDateType();
        if (dateType == DateType.TIME_PACK) {
            dbColumnType = DbColumnType.LOCAL_TIME;
        } else {
            dbColumnType = DbColumnType.TIME;
        }
        return dbColumnType;
    }

    private IColumnType getTimestampType(TableField.MetaInfo metaInfo) {
        DbColumnType dbColumnType;
        DateType dateType = globalConfig.getDateType();
        if (dateType == DateType.TIME_PACK) {
            dbColumnType = DbColumnType.LOCAL_DATE_TIME;
        } else if (dateType == DateType.ONLY_DATE) {
            dbColumnType = DbColumnType.DATE;
        } else {
            dbColumnType = DbColumnType.TIMESTAMP;
        }
        return dbColumnType;
    }

}
