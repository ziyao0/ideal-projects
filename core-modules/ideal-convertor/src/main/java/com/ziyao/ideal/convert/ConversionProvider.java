package com.ziyao.ideal.convert;

import com.ziyao.ideal.core.Assert;
import com.ziyao.ideal.core.Collections;

import java.util.*;

/**
 * @author ziyao
 * @link <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public interface ConversionProvider {


    <T> byte[] write(T source);


    <T> T read(Class<T> type, byte[] source);


    <T> T convert(Object source, Class<T> type);

    void initializeConverters();

    default <T> byte[][] write(Collection<T> sources) {
        Assert.notNull(sources, "sources must not be 'null' or empty");
        Assert.noNullElements(sources.toArray(), "sources must not contain 'null' value");

        byte[][] rawValues = new byte[sources.size()][];
        int i = 0;
        for (T source : sources) {
            rawValues[i++] = write(source);
        }
        return rawValues;
    }

    default <T> List<T> read(Class<T> type, List<byte[]> raws) {
        if (Collections.isEmpty(raws)) {
            return List.of();
        }
        List<T> ts = new ArrayList<>(raws.size());

        for (byte[] raw : raws) {
            ts.add(read(type, raw));
        }

        return ts;
    }

    default <T> Set<T> read(Class<T> type, Set<byte[]> raws) {
        if (Collections.isEmpty(raws)) {
            return Set.of();
        }
        Set<T> ts = new HashSet<>(raws.size());

        for (byte[] raw : raws) {
            ts.add(read(type, raw));
        }

        return ts;
    }

    static ConversionProvider getInstance() {
        return new ObjectConversionProvider();
    }
}
