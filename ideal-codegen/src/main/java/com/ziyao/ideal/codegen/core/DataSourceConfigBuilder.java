package com.ziyao.ideal.codegen.core;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.ziyao.ideal.codegen.config.CodeGenConfig;

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
