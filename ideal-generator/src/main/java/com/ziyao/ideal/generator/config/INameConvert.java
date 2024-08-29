package com.ziyao.ideal.generator.config;

import com.ziyao.ideal.core.Strings;
import com.ziyao.ideal.core.lang.NonNull;
import com.ziyao.ideal.generator.config.po.TableField;
import com.ziyao.ideal.generator.config.po.TableInfo;
import com.ziyao.ideal.generator.config.rules.NamingStrategy;

import java.util.Set;

/**
 * 名称转换接口类
 */
public interface INameConvert {

    /**
     * 执行实体名称转换
     *
     * @param tableInfo 表信息对象
     */
    @NonNull
    String entityNameConvert(@NonNull TableInfo tableInfo);

    /**
     * 执行属性名称转换
     *
     * @param field 表字段对象，如果属性表字段命名不一致注意 convert 属性的设置
     */
    @NonNull
    String  propertyNameConvert(@NonNull TableField field);

    /**
     * 默认名称转换接口类
     * <p>
     * 2020/9/20.
     */
    class DefaultNameConvert implements INameConvert {

        private final StrategyConfig strategyConfig;

        public DefaultNameConvert(StrategyConfig strategyConfig) {
            this.strategyConfig = strategyConfig;
        }

        @Override
        public @NonNull String entityNameConvert(@NonNull TableInfo tableInfo) {
            return NamingStrategy.capitalFirst(processName(tableInfo.getName(), strategyConfig.entity().getNaming(), strategyConfig.getTablePrefix(), strategyConfig.getTableSuffix()));
        }

        @Override
        public @NonNull String propertyNameConvert(@NonNull TableField field) {
            return processName(field.getName(), strategyConfig.entity().getColumnNaming(), strategyConfig.getFieldPrefix(), strategyConfig.getFieldSuffix());
        }

        private String processName(String name, NamingStrategy strategy, Set<String> prefix, Set<String> suffix) {
            String propertyName = name;
            // 删除前缀
            if (!prefix.isEmpty()) {
                propertyName = NamingStrategy.removePrefix(propertyName, prefix);
            }
            // 删除后缀
            if (!suffix.isEmpty()) {
                propertyName = NamingStrategy.removeSuffix(propertyName, suffix);
            }
            if (Strings.isEmpty(propertyName)) {
                throw new RuntimeException(String.format("%s 的名称转换结果为空，请检查是否配置问题", name));
            }
            // 下划线转驼峰
            if (NamingStrategy.underline_to_camel.equals(strategy)) {
                return NamingStrategy.underlineToCamel(propertyName);
            }
            return propertyName;
        }
    }
}
