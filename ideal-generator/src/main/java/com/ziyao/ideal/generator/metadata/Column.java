package com.ziyao.ideal.generator.metadata;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public record Column(boolean primary, String name, String type, String comment) {

    public static Column ofNotPK(String name, String type, String comment) {
        return new Column(false, name, type, comment);
    }
}
