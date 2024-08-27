package com.ziyao.ideal.generator.config.converts;

import com.ziyao.ideal.generator.config.ITypeConvert;
import com.ziyao.ideal.generator.config.converts.select.BranchBuilder;
import com.ziyao.ideal.generator.config.converts.select.Selector;
import com.ziyao.ideal.generator.config.rules.IColumnType;
import com.ziyao.ideal.generator.mybatisplus.DbType;

/**
 * 该注册器负责注册并查询类型注册器
 */
public class TypeConverts {

    /**
     * 查询数据库类型对应的类型转换器
     *
     * @param dbType 数据库类型
     * @return 返回转换器
     */
    public static ITypeConvert getTypeConvert(DbType dbType) {
        return switch (dbType) {
            case MYSQL, MARIADB -> MySqlTypeConvert.INSTANCE;
            case SQLITE -> SqliteTypeConvert.INSTANCE;
            default -> null;
        };
    }

    /**
     * 使用指定参数构建一个选择器
     *
     * @param param 参数
     * @return 返回选择器
     */
    static Selector<String, IColumnType> use(String param) {
        return new Selector<>(param.toLowerCase());
    }

    /**
     * 这个分支构建器用于构建用于支持 {@link String#contains(CharSequence)} 的分支
     *
     * @param value 分支的值
     * @return 返回分支构建器
     * @see #containsAny(CharSequence...)
     */
    static BranchBuilder<String, IColumnType> contains(CharSequence value) {
        return BranchBuilder.of(s -> s.contains(value));
    }

    /**
     * @see #contains(CharSequence)
     */
    static BranchBuilder<String, IColumnType> containsAny(CharSequence... values) {
        return BranchBuilder.of(s -> {
            for (CharSequence value : values) {
                if (s.contains(value)) {
                    return true;
                }
            }
            return false;
        });
    }
}
