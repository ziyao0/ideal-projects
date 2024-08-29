package com.ziyao.ideal.generator.config.converts;

import com.ziyao.ideal.generator.config.GlobalConfig;
import com.ziyao.ideal.generator.config.ITypeConvert;
import com.ziyao.ideal.generator.config.rules.JavaType;
import com.ziyao.ideal.generator.config.rules.IColumnType;

import static com.ziyao.ideal.generator.config.converts.TypeConverts.contains;
import static com.ziyao.ideal.generator.config.converts.TypeConverts.containsAny;
import static com.ziyao.ideal.generator.config.rules.JavaType.*;

/**
 * MYSQL 数据库字段类型转换
 * bit类型数据转换 bit(1) -> Boolean类型  bit(2->64)  -> Byte类型
 */
public class MySqlTypeConvert implements ITypeConvert {
    public static final MySqlTypeConvert INSTANCE = new MySqlTypeConvert();

    /**
     * {@inheritDoc}
     */
    @Override
    public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
        return TypeConverts.use(fieldType)
                .test(contains("point").then(BYTE_ARRAY))
                .test(containsAny("char", "text", "json", "enum").then(STRING))
                .test(contains("bigint").then(LONG))
                .test(containsAny("tinyint(1)", "bit(1)").then(BOOLEAN))
                .test(contains("bit").then(BYTE))
                .test(contains("int").then(INTEGER))
                .test(contains("decimal").then(BIG_DECIMAL))
                .test(contains("clob").then(CLOB))
                .test(contains("blob").then(BLOB))
                .test(contains("binary").then(BYTE_ARRAY))
                .test(contains("float").then(FLOAT))
                .test(contains("double").then(DOUBLE))
                .test(containsAny("date", "time", "year").then(t -> toDateType(config, t)))
                .or(STRING);
    }

    /**
     * 转换为日期类型
     *
     * @param config 配置信息
     * @param type   类型
     * @return 返回对应的列类型
     */
    public static IColumnType toDateType(GlobalConfig config, String type) {
        String dateType = type.replaceAll("\\(\\d+\\)", "");
        return switch (config.getDateType()) {
            case ONLY_DATE -> JavaType.DATE;
            case SQL_PACK -> switch (dateType) {
                case "date", "year" -> JavaType.DATE_SQL;
                case "time" -> JavaType.TIME;
                default -> JavaType.TIMESTAMP;
            };
            case TIME_PACK -> switch (dateType) {
                case "date" -> JavaType.LOCAL_DATE;
                case "time" -> JavaType.LOCAL_TIME;
                case "year" -> JavaType.YEAR;
                default -> JavaType.LOCAL_DATE_TIME;
            };
        };
    }

}
