package com.ziyao.ideal.generator.config.converts;

import com.ziyao.ideal.generator.config.GlobalConfig;
import com.ziyao.ideal.generator.config.ITypeConvert;
import com.ziyao.ideal.generator.config.rules.JavaType;
import com.ziyao.ideal.generator.config.rules.IColumnType;

import static com.ziyao.ideal.generator.config.converts.MySqlTypeConvert.toDateType;
import static com.ziyao.ideal.generator.config.converts.TypeConverts.contains;
import static com.ziyao.ideal.generator.config.converts.TypeConverts.containsAny;
import static com.ziyao.ideal.generator.config.rules.JavaType.*;

/**
 * SQLite 字段类型转换
 */
public class SqliteTypeConvert implements ITypeConvert {
    public static final SqliteTypeConvert INSTANCE = new SqliteTypeConvert();

    /**
     * {@inheritDoc}
     *
     * @see MySqlTypeConvert#toDateType(GlobalConfig, String)
     */
    @Override
    public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
        return TypeConverts.use(fieldType)
                .test(contains("bigint").then(LONG))
                .test(containsAny("tinyint(1)", "boolean").then(BOOLEAN))
                .test(contains("int").then(INTEGER))
                .test(containsAny("text", "char", "enum").then(STRING))
                .test(containsAny("decimal", "numeric").then(BIG_DECIMAL))
                .test(contains("clob").then(CLOB))
                .test(contains("blob").then(BLOB))
                .test(contains("float").then(FLOAT))
                .test(contains("double").then(DOUBLE))
                .test(containsAny("date", "time", "year").then(t -> toDateType(config, t)))
                .or(JavaType.STRING);
    }

}
