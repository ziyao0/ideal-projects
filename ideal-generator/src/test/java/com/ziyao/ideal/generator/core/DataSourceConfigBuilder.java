package com.ziyao.ideal.generator.core;



import com.ziyao.ideal.generator.config.CodeGenConfig;
import com.ziyao.ideal.generator.config.DataSourceConfig;
import com.ziyao.ideal.generator.config.rules.DbColumnType;

import java.sql.Types;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class DataSourceConfigBuilder {

    public static void datasourceConfig(
            DataSourceConfig.Builder builder, CodeGenConfig codeGenConfig) {

        builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
            int typeCode = metaInfo.getJdbcType().TYPE_CODE;
            if (typeCode == Types.SMALLINT) {
                // 自定义类型转换
                return DbColumnType.INTEGER;
            }
            return typeRegistry.getColumnType(metaInfo);

        });
    }
}
