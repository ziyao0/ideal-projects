package com.ziyao.harbor.crypto.core;

import com.ziyao.crypto.Properties;
import com.ziyao.harbor.crypto.utils.ConstantPool;

/**
 * @author ziyao zhang
 */
public class DefaultProperties extends Properties<DefaultProperties> {
    @Override
    public String getPrefix() {
        return ConstantPool.default_prefix;
    }
}
