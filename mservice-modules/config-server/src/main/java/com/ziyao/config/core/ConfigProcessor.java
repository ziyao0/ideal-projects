package com.ziyao.config.core;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public interface ConfigProcessor<T, S> extends ConfigProcessorSupport {

    /**
     * 处理配置转换问题
     *
     * @param source source
     * @return 返回转换后的结果
     */
    T process(S source);
}
