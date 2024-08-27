package com.ziyao.ideal.generator.config.po;

import com.ziyao.ideal.generator.mybatisplus.SqlLike;

/**
 * 表名拼接
 */
public class LikeTable {

    private final String value;

    private SqlLike like = SqlLike.DEFAULT;

    public LikeTable(String value) {
        this.value = value;
    }

    public LikeTable(String value, SqlLike like) {
        this.value = value;
        this.like = like;
    }

    @Override
    public String toString() {
        return getValue();
    }

    public String getValue() {
        return concatLike(this.value, like);
    }


    /**
     * 用%连接like
     *
     * @param str 原字符串
     * @return like 的值
     */
    public static String concatLike(Object str, SqlLike type) {
        return switch (type) {
            case LEFT -> "%" + str;
            case RIGHT -> str + "%";
            default -> "%" + str + "%";
        };
    }
}
