package com.ziyao.ideal.generator.util;//package com.ziyao.ideal.generator.util;
//
//import com.ziyao.ideal.core.Strings;
//import com.ziyao.ideal.generator.core.Constants;
//import org.apache.ibatis.type.JdbcType;
//import org.apache.ibatis.type.TypeHandler;
//
///**
// * @author ziyao
// * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
// */
//public abstract class SqlScriptUtils implements Constants {
//
//    /**
//     * <p>
//     * 获取 带 if 标签的脚本
//     * </p>
//     *
//     * @param sqlScript sql 脚本片段
//     * @return if 脚本
//     */
//    public static String convertIf(final String sqlScript, final String ifTest, boolean newLine) {
//        String newSqlScript = sqlScript;
//        if (newLine) {
//            newSqlScript = NEWLINE + newSqlScript + NEWLINE;
//        }
//        return String.format("<if test=\"%s\">%s</if>", ifTest, newSqlScript);
//    }
//
//    /**
//     * <p>
//     * 获取 带 trim 标签的脚本
//     * </p>
//     *
//     * @param sqlScript       sql 脚本片段
//     * @param prefix          以...开头
//     * @param suffix          以...结尾
//     * @param prefixOverrides 干掉最前一个...
//     * @param suffixOverrides 干掉最后一个...
//     * @return trim 脚本
//     */
//    public static String convertTrim(final String sqlScript, final String prefix, final String suffix,
//                                     final String prefixOverrides, final String suffixOverrides) {
//        StringBuilder sb = new StringBuilder("<trim");
//        if (Strings.hasLength(prefix)) {
//            sb.append(" prefix=\"").append(prefix).append(QUOTE);
//        }
//        if (Strings.hasLength(suffix)) {
//            sb.append(" suffix=\"").append(suffix).append(QUOTE);
//        }
//        if (Strings.hasLength(prefixOverrides)) {
//            sb.append(" prefixOverrides=\"").append(prefixOverrides).append(QUOTE);
//        }
//        if (Strings.hasLength(suffixOverrides)) {
//            sb.append(" suffixOverrides=\"").append(suffixOverrides).append(QUOTE);
//        }
//        return sb.append(RIGHT_CHEV).append(NEWLINE).append(sqlScript).append(NEWLINE).append("</trim>").toString();
//    }
//
//    /**
//     * <p>
//     * 生成 choose 标签的脚本
//     * </p>
//     *
//     * @param whenTest  when 内 test 的内容
//     * @param otherwise otherwise 内容
//     * @return choose 脚本
//     */
//    public static String convertChoose(final String whenTest, final String whenSqlScript, final String otherwise) {
//        return "<choose>" + NEWLINE
//                + "<when test=\"" + whenTest + QUOTE + RIGHT_CHEV + NEWLINE
//                + whenSqlScript + NEWLINE + "</when>" + NEWLINE
//                + "<otherwise>" + otherwise + "</otherwise>" + NEWLINE
//                + "</choose>";
//    }
//
//    /**
//     * <p>
//     * 生成 foreach 标签的脚本
//     * </p>
//     *
//     * @param sqlScript  foreach 内部的 sql 脚本
//     * @param collection collection
//     * @param index      index
//     * @param item       item
//     * @param separator  separator
//     * @return foreach 脚本
//     */
//    public static String convertForeach(final String sqlScript, final String collection, final String index,
//                                        final String item, final String separator) {
//        StringBuilder sb = new StringBuilder("<foreach");
//        if (Strings.hasLength(collection)) {
//            sb.append(" collection=\"").append(collection).append(QUOTE);
//        }
//        if (Strings.hasLength(index)) {
//            sb.append(" index=\"").append(index).append(QUOTE);
//        }
//        if (Strings.hasLength(item)) {
//            sb.append(" item=\"").append(item).append(QUOTE);
//        }
//        if (Strings.hasLength(separator)) {
//            sb.append(" separator=\"").append(separator).append(QUOTE);
//        }
//        return sb.append(RIGHT_CHEV).append(NEWLINE).append(sqlScript).append(NEWLINE).append("</foreach>").toString();
//    }
//
//    /**
//     * <p>
//     * 生成 where 标签的脚本
//     * </p>
//     *
//     * @param sqlScript where 内部的 sql 脚本
//     * @return where 脚本
//     */
//    public static String convertWhere(final String sqlScript) {
//        return "<where>" + NEWLINE + sqlScript + NEWLINE + "</where>";
//    }
//
//    /**
//     * <p>
//     * 生成 set 标签的脚本
//     * </p>
//     *
//     * @param sqlScript set 内部的 sql 脚本
//     * @return set 脚本
//     */
//    public static String convertSet(final String sqlScript) {
//        return "<set>" + NEWLINE + sqlScript + NEWLINE + "</set>";
//    }
//
//    /**
//     * <p>
//     * 安全入参:  #{入参}
//     * </p>
//     *
//     * @param param 入参
//     * @return 脚本
//     */
//    public static String safeParam(final String param) {
//        return safeParam(param, null);
//    }
//
//    /**
//     * <p>
//     * 安全入参:  #{入参,mapping}
//     * </p>
//     *
//     * @param param   入参
//     * @param mapping 映射
//     * @return 脚本
//     */
//    public static String safeParam(final String param, final String mapping) {
//        String target = HASH_LEFT_BRACE + param;
//        if (Strings.isEmpty(mapping)) {
//            return target + RIGHT_BRACE;
//        }
//        return target + COMMA + mapping + RIGHT_BRACE;
//    }
//
//    /**
//     * <p>
//     * 非安全入参:  ${入参}
//     * </p>
//     *
//     * @param param 入参
//     * @return 脚本
//     */
//    public static String unSafeParam(final String param) {
//        return DOLLAR_LEFT_BRACE + param + RIGHT_BRACE;
//    }
//
//    public static String mappingTypeHandler(Class<? extends TypeHandler<?>> typeHandler) {
//        if (typeHandler != null) {
//            return "typeHandler=" + typeHandler.getName();
//        }
//        return null;
//    }
//
//    public static String mappingJdbcType(JdbcType jdbcType) {
//        if (jdbcType != null) {
//            return "jdbcType=" + jdbcType.name();
//        }
//        return null;
//    }
//
//    public static String mappingNumericScale(Integer numericScale) {
//        if (numericScale != null) {
//            return "numericScale=" + numericScale;
//        }
//        return null;
//    }
//
//    public static String convertParamMapping(Class<? extends TypeHandler<?>> typeHandler, JdbcType jdbcType, Integer numericScale) {
//        if (typeHandler == null && jdbcType == null && numericScale == null) {
//            return null;
//        }
//        String mapping = null;
//        if (typeHandler != null) {
//            mapping = mappingTypeHandler(typeHandler);
//        }
//        if (jdbcType != null) {
//            mapping = appendMapping(mapping, mappingJdbcType(jdbcType));
//        }
//        if (numericScale != null) {
//            mapping = appendMapping(mapping, mappingNumericScale(numericScale));
//        }
//        return mapping;
//    }
//
//    private static String appendMapping(String mapping, String other) {
//        if (mapping != null) {
//            return mapping + Strings.COMMA + other;
//        }
//        return other;
//    }
//}
