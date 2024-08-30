package com.ziyao.ideal.generator.mysql.query;

import com.ziyao.ideal.generator.core.metadata.MetaInfo;

import java.util.List;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public interface MetadataQuery {

    List<MetaInfo> query();
}
