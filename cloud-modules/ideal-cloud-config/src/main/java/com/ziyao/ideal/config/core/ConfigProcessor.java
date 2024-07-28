package com.ziyao.ideal.config.core;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public interface ConfigProcessor<T> extends ConfigProcessorSupport {

    /**
     * 加载配置信息
     * <p>
     * 把string类型的配置信息加载成指定格式的配置信息
     *
     * @param source source
     * @return 返回指定类型的配置信息
     */
    T load(String source);

    /**
     * source to  string
     *
     * @param source 配置元数据
     * @return string类型的配置信息
     */
    String resolve(T source);
}
