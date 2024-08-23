package com.ziyao.ideal.generator.mybatisplus;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public abstract class SqlCondition {
    /**
     * 等于
     */
    public static final String EQUAL = "%s=#{%s}";
    /**
     * 不等于
     */
    public static final String NOT_EQUAL = "%s&lt;&gt;#{%s}";
    /**
     * % 两边 %
     */
    public static final String LIKE = "%s LIKE CONCAT('%%',#{%s},'%%')";

    /**
     * % 两边 % [oracle使用]
     */
    public static final String ORACLE_LIKE = "%s LIKE CONCAT(CONCAT('%%',#{%s}),'%%')";
    /**
     * % 左
     */
    public static final String LIKE_LEFT = "%s LIKE CONCAT('%%',#{%s})";
    /**
     * 右 %
     */
    public static final String LIKE_RIGHT = "%s LIKE CONCAT(#{%s},'%%')";
}
